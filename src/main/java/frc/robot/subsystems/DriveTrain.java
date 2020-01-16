/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public double dSpeed = Constants.DRIVETRAINSPEED;
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
    rightMotorFront.setInverted(false);
    
    leftMotorBack = new Spark(Constants.LEFT_BACK_MOTOR);
    addChild("LeftMotorBack",leftMotorBack);
    leftMotorBack.setInverted(false);
            
    rightMotorBack = new Spark(Constants.RIGHT_BACK_MOTOR);
    addChild("RightMotorBack",rightMotorBack);
    rightMotorBack.setInverted(false);
  }

  public void driveWithJoysticks(XboxController controller)
	{
        double joystickX = controller.getX();
        double joystickY = controller.getY();
        double motorLeft;
        double motorRight;
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
        System.out.println("Motor Left: " + motorLeft);
        System.out.println("Motor Right: " + motorRight);
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
