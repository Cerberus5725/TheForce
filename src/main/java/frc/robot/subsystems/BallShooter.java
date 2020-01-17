/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallShooter extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private Spark shootRollerLeft;
  private Spark shootRollerRight;
  public double shootSpeed = Constants.SHOOTERSPEED;
  public BallShooter() {
    shootRollerLeft = new Spark(Constants.SHOOT_MOTOR_LEFT);
    addChild("ShootRollerLeft", shootRollerLeft);
    shootRollerLeft.setInverted(false);
    
    shootRollerRight = new Spark(Constants.SHOOT_MOTOR_RIGHT);
    addChild("ShootRollerRight", shootRollerRight);
    shootRollerRight.setInverted(false);
  }

  public void shootBall()
  {
   shootRollerLeft.set(shootSpeed);
   shootRollerRight.set(shootSpeed);
  }

  public void shootReturner()
  {
   shootRollerLeft.set(-shootSpeed);
   shootRollerRight.set(-shootSpeed);
  }

  public void stop()
  {
   shootRollerLeft.set(0);
   shootRollerRight.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}