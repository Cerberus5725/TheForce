/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private Spark intakeRoller;
  
  public double intakeSpeed = Constants.INTAKESPEED;
  public Intake() {
    intakeRoller = new Spark(Constants.INTAKE_MOTOR);
    addChild("IntakeRoller", intakeRoller);
    intakeRoller.setInverted(false);
  }

 public void intakeSystem(XboxController controller)
 {
  double leftTrigger = controller.getTriggerAxis(Hand.kLeft);
  double rightTrigger = controller.getTriggerAxis(Hand.kRight);
  double intakeValue = (rightTrigger - leftTrigger) * Constants.INTAKESPEED;
  //System.out.println("Intake: " + intakeValue);
  intakeRoller.set(intakeValue);
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
