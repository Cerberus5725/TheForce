/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  //private Spark intakeRoller;
  
  private static final int deviceID = Constants.INTAKE_CAN;
  private CANSparkMax intakeRoller;
  
  public double intakeSpeed = Constants.INTAKESPEED;
  public double autoIntakeSpeed = Constants.AUTO_INTAKE_SPEED;
  private double shootTime = Constants.SHOOT_TIME;
  private double preloadTime = Constants.PRELOAD_TIME;
  public Intake() {
    /*intakeRoller = new Spark(Constants.INTAKE_MOTOR);
    addChild("IntakeRoller", intakeRoller);
    intakeRoller.setInverted(false);
    */
    intakeRoller = new CANSparkMax(deviceID, MotorType.kBrushless);
  }

 public void intakeSystem(XboxController controller)
 {
  double leftTrigger = controller.getTriggerAxis(Hand.kLeft);
  double rightTrigger = controller.getTriggerAxis(Hand.kRight);
  double intakeValue = (rightTrigger - leftTrigger) * Constants.INTAKESPEED;
  //System.out.println("Intake: " + intakeValue);
  intakeRoller.set(intakeValue);
 }

 public void runIntake()
 {
      intakeRoller.set(autoIntakeSpeed);
 }

  public void stop()
  {
   intakeRoller.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
