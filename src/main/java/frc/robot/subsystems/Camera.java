/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
// Ready to drop in the subsystems folder
import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
//import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// Added imports
import  frc.robot.GripPipeline;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Link for simpler: https://www.chiefdelphi.com/t/using-grips-generated-code-for-vision-tracking/340669/4

public class Camera extends SubsystemBase {
  public Object imgLock = new Object();
	private Thread visionThread;
	// Changed from gear to grip
	// private GearPipeline pipeline;
	private GripPipeline pipeline;
	private boolean runProcessing = false;
	private double centerXLarge = 0.0;
	
	double lastSide = 0.0;
	double lastCenterX = 0.0;
	
	public static final int cameraResX = 320;
	public static final int cameraResY = 240;
  /**
   * Creates a new Camera.
   */
  public Camera() {
    enableVisionThread();
  }
  public void enableVisionThread() {
		//pipeline = new GearPipeline();
		pipeline = new GripPipeline();
		//AxisCamera camera = CameraServer.getInstance().addAxisCamera("10.3.3.8");
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(cameraResX, cameraResY);

		CvSink cvSink = CameraServer.getInstance().getVideo(); //capture mats from camera
		CvSource outputStream = CameraServer.getInstance().putVideo("Stream", cameraResX, cameraResY); //send steam to CameraServer
		Mat mat = new Mat(); //define mat in order to reuse it

		runProcessing = true;

		visionThread = new Thread(() -> {

			while(!Thread.interrupted()) { //this should only be false when thread is disabled

				if(cvSink.grabFrame(mat)==0) { //fill mat with image from camera)
					outputStream.notifyError(cvSink.getError()); //send an error instead of the mat
					SmartDashboard.putString("Vision State", "Acquisition Error");
					continue; //skip to the next iteration of the thread
				}

				if(runProcessing) 
				{		
					pipeline.process(mat); //process the mat (this does not change the mat, and has an internal output to pipeline)
					int contoursFound = pipeline.filterContoursOutput().size();
					SmartDashboard.putString("More Vision State","Saw "+contoursFound+" Contours");
                    //System.out.println("Contours Found " + countoursFound);
                    ArrayList<Rect> foundRectangles= new ArrayList<Rect>();
                    // Set the largest rectangle to the first contour to start then compare it against all other found contours.
                    if(contoursFound > 0)
                    {
                    	Rect largestRect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                    	for(int i = 0;i < contoursFound;i++)
                    	{
                    		foundRectangles.add(Imgproc.boundingRect(pipeline.filterContoursOutput().get(i)));
                    		// Consider finding tallest rectangle instead of greatest area.
                    		//if(foundRectangles.get(i).height > largestRect.height)
                    		if(foundRectangles.get(i).area() > largestRect.area())
                    		{
                    			largestRect = foundRectangles.get(i);
                    		}
                    	}
                    	centerXLarge = largestRect.x + (largestRect.width/2);
                    	// System.out.println("Center X: " + centerXLarge);
                    	//draws the rectangles onto the camera image sent to the dashboard
                    	Imgproc.rectangle(mat, new Point(largestRect.x, largestRect.y), new Point(largestRect.x + largestRect.width, largestRect.y + largestRect.height), new Scalar(0, 0, 255), 2); 
                    	SmartDashboard.putString("Vision State", "Executed overlay!");
                    	SmartDashboard.putNumber("Center X", centerXLarge);
                    	
                    	lastSide = (centerXLarge - cameraResX)*2 / Math.abs(centerXLarge);
                    }
                    else
                    {
                    // If no contours found
                    //	System.out.print("Last Side X = " + lastSide);
                    	centerXLarge = 0.0;
                    }
                    outputStream.putFrame(mat); //give stream (and CameraServer) a new frame
				} 
				else 
				{
					outputStream.putFrame(mat); //give stream (and CameraServer) a new frame
				}
				//Timer.delay(0.09);
			}

		});	
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	
	public double getCenterXLarge() {
		return centerXLarge;
	}

	public void disableProcessing() {
		runProcessing = false;
	}

	public void enableProcessing() {
		runProcessing = true;
	}
	
	public double getLastSide() {
		return lastSide;
		
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
