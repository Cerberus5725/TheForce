/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

public class Vision extends CommandBase {
  private boolean finish = false;
	//Get Variables form driveTrain
	private double precision = Constants.PRECISION;
	private double target = Constants.TARGET;
  private double delayTime = Constants.DELAY_TIME;
  private final DriveTrain drivetrain;
  private final Camera camera;
  /**
   * Creates a new Vision.
   */
  public Vision(DriveTrain dt, Camera c) {
    // Use addRequirements() here to declare subsystem dependencies.
    camera = c;
    drivetrain = dt;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    while (drivetrain.getAverageDistanceToObject() > Constants.SETPOINT_FORWARD_CLOSE && !drivetrain.joystickOverRide(RobotContainer.driverJoystick))
    		{
    	    	double center = camera.getCenterXLarge();
    	    	boolean cameraReading = center != 0.0 ? true: false;
    	    	if(cameraReading)
    	    	{
    	    	double distanceToTarget = target - center; 
    			//Logs
    			System.out.println("Set Target: " + String.valueOf(target));
    			System.out.println("Center of Large Object: " + String.valueOf(center));
    			System.out.println("Distance to Target: " + String.valueOf(distanceToTarget));
     			System.out.println("Range Finder: " + String.valueOf(drivetrain.getAverageDistanceToObject()));
    	    	// If not aiming at the target 
    	    	  	    	 
    			if(Math.abs(distanceToTarget) > precision)
    			{
    	    		if(distanceToTarget > 0)
    	    		{
    	    			// TurnLeft
						drivetrain.driveLeft();
						//drivetrain.veerLeft();
    	    			
    	    		}
    	    		else if(distanceToTarget < 0)
    	    		{
    	    			// TurnRight
						drivetrain.driveRight();
						//drivetrain.veerRight();
    	    		
    	    		}
    			}	
    			else
    			{
    			drivetrain.driveForward();
    			}
    			//Delays the look
    			Timer.delay(delayTime);
    			}
    	    	else
    	    	{
    	    		// If camera is not reading
    	    		drivetrain.stop();
    	    	}
    		}
    	   // Finish loop when rangefinders reach target distance
    		finish = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
