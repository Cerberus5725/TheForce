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
import frc.robot.Constants;
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
	private double widthLarge = 0.0;
	private double heightLarge = 0.0;
	private double areaLarge = 0.0;
	
	// Likely important values for get center as it should be half of these.
	private static final int cameraResX = Constants.CAMERA_RES_X;
	private static final int cameraResY = Constants.CAMERA_RES_Y;
  /**
   * Creates a new Camera.
   */
  public Camera() {
	  // It is important this is initialized with creation of the class so the camera server
	  // starts delivering video and starts reading contours for when they are needed.
    enableVisionThread();
  }
  public void enableVisionThread() {
		pipeline = new GripPipeline();
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
                    // Set the widest rectangle to the first contour to start then compare it against all other found contours.
					// With the layout of vision targets for this years targets the widest rectangle should be the best choice.
					if(contoursFound > 0)
                    {
                    	Rect widestRect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                    	for(int i = 0;i < contoursFound;i++)
                    	{
                    		foundRectangles.add(Imgproc.boundingRect(pipeline.filterContoursOutput().get(i)));
							// Option: compare height and or area instead of width
							if(foundRectangles.get(i).width > widestRect.width)
                    		{
                    			widestRect = foundRectangles.get(i);
                    		}
                    	}
						centerXLarge = widestRect.x + (widestRect.width/2);
						heightLarge = widestRect.height;
						widthLarge = widestRect.width;
						areaLarge = widestRect.area();
                    	// System.out.println("Center X: " + centerXLarge);
                    	//draws the rectangles onto the camera image sent to the dashboard
                    	Imgproc.rectangle(mat, new Point(widestRect.x, widestRect.y), new Point(widestRect.x + widestRect.width, widestRect.y + widestRect.height), new Scalar(255, 0, 0), 2); 
                    	SmartDashboard.putString("Vision State", "Executed overlay!");
						SmartDashboard.putNumber("Center X", centerXLarge);
						SmartDashboard.putNumber("Width X", widthLarge);
                    	
                    }
                    else
                    {
                    // If no contours found, not sure if setting to zero is wise
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
	
	// These methods just allow access to values that will constantly be updated in the above function.
	public double getCenterXLarge() {
		return centerXLarge;
	}

	public double getWidthLarge() {
		return widthLarge;
	}

	public double getHeightLarge() {
		return heightLarge;
	}

	public double getAreaLarge() {
		return areaLarge;
	}

	public void disableProcessing() {
		runProcessing = false;
	}

	public void enableProcessing() {
		runProcessing = true;
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
