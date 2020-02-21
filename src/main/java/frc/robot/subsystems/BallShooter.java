/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;

public class BallShooter extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private Spark shootRoller;
  private double shootSpeed = Constants.SHOOTERSPEED;
  private double preloadTime = Constants.PRELOAD_TIME;
  private double shootTime = Constants.SHOOT_TIME;
  
  private Servo gate;
  public BallShooter() {
    shootRoller = new Spark(Constants.SHOOT_MOTOR);
    addChild("ShootRoller", shootRoller);
    shootRoller.setInverted(false);
    gate = new Servo(Constants.GATE_PWM);
    gate.setAngle(90);
  }

  public void shootBall()
  {
   shootRoller.set(shootSpeed);
  }

  public void shootReturner()
  {
   shootRoller.set(-shootSpeed);
  }

  public void openGate()
  {
    gate.setAngle(180);
  }
 
  public void closeGate()
  {
    gate.setAngle(90);
  }

  public boolean autoShoot()
  {
    Timer timer = new Timer();
    timer.reset();
    timer.start();
    while(timer.get() < shootTime)
    {
        shootBall();
        if (timer.get() > preloadTime)
        {
        openGate();
        }
    }
    timer.stop();
    closeGate(); 
    return true;
  }

  public void reverseGate()
  {
    gate.setAngle(0);
  }
 

  public void stop()
  {
   shootRoller.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}