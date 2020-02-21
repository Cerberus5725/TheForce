/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallShooter;

public class AutoShoot extends CommandBase {
  private final BallShooter ballShooter;
  private boolean finish = false;
  /**
   * Creates a new AutoShoot.
   */
  public AutoShoot(BallShooter bs) {
    // Use addRequirements() here to declare subsystem dependencies.
    ballShooter = bs;
    addRequirements(ballShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finish = ballShooter.autoShoot();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  ballShooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
