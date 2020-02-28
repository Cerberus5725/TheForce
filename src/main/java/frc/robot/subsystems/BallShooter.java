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
import edu.wpi.first.wpilibj.Servo;

public class BallShooter extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private Spark shootRoller;
  private Spark revolver;

  private Servo gate;
  public BallShooter() {
    shootRoller = new Spark(Constants.SHOOT_MOTOR);
    addChild("ShootRoller", shootRoller);
    shootRoller.setInverted(false);

    gate = new Servo(Constants.GATE_PWM);
    gate.setAngle(90);

    revolver = new Spark(Constants.REVOLVER_MOTOR);
    addChild("Revolver", revolver);
    revolver.setInverted(false);
  }

  public void revolverSpin(double speed)
  {
    revolver.set(speed);
  }

  public void shootBall(double speed)
  {
   shootRoller.set(speed);
  }

  public void shootReturner(double speed)
  {
   shootRoller.set(-speed);
  }

  public void openGate()
  {
    gate.setAngle(180);
  }
 
  public void closeGate()
  {
    gate.setAngle(90);
  }

  public void reverseGate()
  {
    gate.setAngle(0);
  }
 
  public void stop()
  {
   shootRoller.set(0);
  }

  public void stopRevolver()
  {
    revolver.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}