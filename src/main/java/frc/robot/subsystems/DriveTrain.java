/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveForward;


public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  double motorLeft;
  double motorRight;
  public boolean inverted = false;
  private final AnalogInput rangeFinderLeft = new AnalogInput(0);
  private final AnalogInput rangeFinderRight = new AnalogInput(1);

  private double dSpeed = Constants.DRIVETRAINSPEED;
  private double aSpeed = Constants.AUTONOMOUSSPEED;
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

  public void driveWithJoysticks(XboxController controller)
	{
    double joystickX = controller.getRawAxis(Constants.XBOX_LEFT_X_AXIS);
    double joystickY = -controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS);

      if(!inverted)
      {
        motorLeft = (joystickX + joystickY) *dSpeed;
        motorRight = (-joystickX + joystickY) *dSpeed;
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
        motorLeft = (joystickX - joystickY) *dSpeed;
        motorRight = (-joystickX - joystickY) *dSpeed;
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
       motorLeft = aSpeed;
       motorRight = -aSpeed;
       motorLeft = aSpeed;
       motorRight = -aSpeed;

      leftMotorFront.set(motorLeft);
      rightMotorFront.set(motorRight);
      leftMotorBack.set(motorLeft);
      rightMotorBack.set(motorRight);
    }

    public void driveRight()
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

    public double getAverageDistanceToObject()
    {
    	//Rangefinder log code
    	//System.out.println("Range Finder: " + String.valueOf(rangeFinder.getAverageVoltage()));
      double rangeFinderAverage = (rangeFinderLeft.getAverageVoltage()+rangeFinderRight.getAverageVoltage())/2;
      return rangeFinderAverage;	
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
