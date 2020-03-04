/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  double motorLeft;
  double motorRight;
  public boolean inverted = false;
  private final AnalogInput rangeFinderLeft = new AnalogInput(0);
  private final AnalogInput rangeFinderRight = new AnalogInput(1);

  private double aSpeed = Constants.AUTONOMOUSSPEED;
  private double veerSlow = Constants.VEER_SLOW;
  private double veerFast = Constants.VEER_FAST;
  private double joystickOverrideTolerance = Constants.JOYSTICK_OVERRIDE_TOLERANCE;
  private double setPointForwardClose = Constants.SETPOINT_FORWARD_CLOSE;
  private double setPointForwardFar = Constants.SETPOINT_FORWARD_FAR;

  public static Spark leftMotorFront;
  public static Spark rightMotorFront;
  public static Spark leftMotorBack;
  public static Spark rightMotorBack;
  
  
  public DriveTrain() {
    leftMotorFront = new Spark(Constants.LEFT_FRONT_MOTOR);
    addChild("LeftMotorFront",leftMotorFront);
    leftMotorFront.setInverted(false);
            
    rightMotorFront = new Spark(Constants.RIGHT_FRONT_MOTOR);
    addChild("RightMotorFront",rightMotorFront);
    rightMotorFront.setInverted(true);
    
    leftMotorBack = new Spark(Constants.LEFT_BACK_MOTOR);
    addChild("LeftMotorBack",leftMotorBack);
    leftMotorBack.setInverted(false);
            
    rightMotorBack = new Spark(Constants.RIGHT_BACK_MOTOR);
    addChild("RightMotorBack",rightMotorBack);
    rightMotorBack.setInverted(true);
  }

  public void driveWithJoysticks(XboxController controller, double speed)
	{
    double joystickX = controller.getRawAxis(Constants.XBOX_LEFT_X_AXIS);
    double joystickY = -controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS);

      if(!inverted)
      {
        motorLeft = (joystickX + joystickY) *speed;
        motorRight = (-joystickX + joystickY) *speed;
        if(motorLeft >= 1.0)
        {
            motorLeft = 1.0;
        }
        if(motorRight >= 1.0)
        {
            motorRight = 1.0;
        }
        leftMotorFront.set(motorLeft);
        rightMotorFront.set(motorRight);
        leftMotorBack.set(motorLeft);
        rightMotorBack.set(motorRight);
        //System.out.println("Motor Left: " + motorLeft);
        //System.out.println("Motor Right: " + motorRight);
      }
      else
      {
        motorLeft = (joystickX - joystickY) *speed;
        motorRight = (-joystickX - joystickY) *speed;
        if(motorLeft >= 1.0)
        {
            motorLeft = 1.0;
        }
        if(motorRight >= 1.0)
        {
            motorRight = 1.0;
        }
        leftMotorFront.set(motorLeft);
        rightMotorFront.set(motorRight);
        leftMotorBack.set(motorLeft);
        rightMotorBack.set(motorRight);
        //System.out.println("Motor Left Inverted: " + motorLeft);
        //System.out.println("Motor Right Inverted: " + motorRight);
      }
    }

    public void switchDirection()
    {
    	if(!inverted)
    	{
    		inverted = true;
    	}
    	else
    	{
    		inverted = false;
    	}
    }

    public void driveForward()
    {
      if(!inverted)
      {
       motorLeft = aSpeed;
       motorRight = aSpeed;
      }
      else
      {
        motorLeft = -aSpeed;
        motorRight = -aSpeed;
      }

       leftMotorFront.set(motorLeft);
       rightMotorFront.set(motorRight);
       leftMotorBack.set(motorLeft);
       rightMotorBack.set(motorRight);
    }

    public void driveBackward()
    {
      if(!inverted)
      {
       motorLeft = -aSpeed;
       motorRight = -aSpeed;
      }
      else
      {
        motorLeft = aSpeed;
        motorRight = aSpeed;
      }
       leftMotorFront.set(motorLeft);
       rightMotorFront.set(motorRight);
       leftMotorBack.set(motorLeft);
       rightMotorBack.set(motorRight);
    }

    public void driveLeft()
    {
      motorLeft = -aSpeed;
      motorRight = aSpeed;
      motorLeft = -aSpeed;
      motorRight = aSpeed;    
     leftMotorFront.set(motorLeft);
     rightMotorFront.set(motorRight);
     leftMotorBack.set(motorLeft);
     rightMotorBack.set(motorRight);
    }

    public void driveRight()
    {
      motorLeft = aSpeed;
      motorRight = -aSpeed;
      motorLeft = aSpeed;
      motorRight = -aSpeed;

     leftMotorFront.set(motorLeft);
     rightMotorFront.set(motorRight);
     leftMotorBack.set(motorLeft);
     rightMotorBack.set(motorRight);
    }

    public void veerLeft()
    {
      motorLeft = veerSlow;
      motorRight = veerFast;
      motorLeft = veerSlow;
      motorRight = veerFast;    
     leftMotorFront.set(motorLeft);
     rightMotorFront.set(motorRight);
     leftMotorBack.set(motorLeft);
     rightMotorBack.set(motorRight);
    }

    public void veerRight()
    {
      motorLeft = veerFast;
      motorRight = veerSlow;
      motorLeft = veerFast;
      motorRight = veerSlow;

     leftMotorFront.set(motorLeft);
     rightMotorFront.set(motorRight);
     leftMotorBack.set(motorLeft);
     rightMotorBack.set(motorRight);
    }

    public double getAverageDistanceToObject()
    { 
      /* This code has been improved to get the median value rather than the average.
         The median will help eliminate any single misreads to improve accuracy.
         The length of the ranges array can be changed to tune but should be given
         an even number to ensure an equal number of readings from each rangefinder.
      */
      double rangeFinderAverage = 0.0;
      double ranges[] = new double[10]; 
      for(int i = 0; i < ranges.length;i++)
      {
        // Alternate getting ranges from both ranges and storing them in a set.
        if(i%2 != 0)
        {
          ranges[i] = rangeFinderLeft.getAverageVoltage();
        }
        else
        {
          ranges[i] = rangeFinderRight.getAverageVoltage();
        }
      }
      Arrays.sort(ranges);
      System.out.printf("Sorted Array: %s", Arrays.toString(ranges));
      //Get the average of the 2 middle values if even or just the middle value if odd numbered array length
      if(ranges.length%2 == 0.0)
      {
      rangeFinderAverage = ((ranges[ranges.length/2] + ranges[(ranges.length/2)-1])/2);
      }
      else
      {
      rangeFinderAverage = ranges[ranges.length/2];
      }
      // Simple get and divide both ranges
      //double rangeFinderAverage = (rangeFinderLeft.getAverageVoltage()+rangeFinderRight.getAverageVoltage())/2;
      //System.out.println("Range Finder: " + rangeFinderAverage);
      SmartDashboard.putNumber("Range Finder Distance", rangeFinderAverage);
      return rangeFinderAverage;	
    }

    public boolean driveToDistanceClose(XboxController controller)
    {
      while(getAverageDistanceToObject() > setPointForwardClose && !joystickOverRide(controller))
      {
      driveForward();
      }
      return true;
    }

    public boolean driveToDistanceFar(XboxController controller)
    {
      while(getAverageDistanceToObject() > setPointForwardFar && !joystickOverRide(controller))
      {
      driveForward();
      }
      return true;
    }

    public boolean joystickOverRide(XboxController controller)
    {
      if(Math.abs(controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)) < joystickOverrideTolerance &&
      Math.abs(controller.getRawAxis(Constants.XBOX_LEFT_X_AXIS)) < joystickOverrideTolerance)
      {
        return false;
      }
      else
      {
        return true;
      }
    }

    public void stop()
    {
      leftMotorFront.set(0);
      rightMotorFront.set(0);
      leftMotorBack.set(0);
      rightMotorBack.set(0);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
