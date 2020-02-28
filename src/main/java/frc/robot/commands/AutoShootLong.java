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
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.Intake;

public class AutoShootLong extends CommandBase {
  private final BallShooter ballShooter;
  private final Intake intake;
  private boolean finish = false;
  /**
   * Creates a new AutoShootLong.
   */
  public AutoShootLong(BallShooter bs, Intake i) {

    // Use addRequirements() here to declare subsystem dependencies.
    ballShooter = bs;
    intake = i;
    addRequirements(ballShooter);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Timer timer = new Timer();
    timer.reset();
    timer.start();
    while(timer.get() < Constants.SHOOT_TIME)
    {
        ballShooter.shootBall(Constants.SHOOTERSPEEDLONG);
        if (timer.get() > Constants.PRELOAD_TIME)
        {
        ballShooter.revolverSpin(Constants.REVOLVERSPEED);
        ballShooter.openGate();
        }
        if (timer.get() > Constants.AUTO_INTAKE_DELAY)
        {
          intake.runIntake();
        }
    }
    timer.stop();
    finish = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ballShooter.stop();
    intake.stop();
    ballShooter.closeGate(); 
    ballShooter.stopRevolver();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
