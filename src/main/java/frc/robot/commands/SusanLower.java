/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LazySusan;

public class SusanLower extends CommandBase {
  LazySusan susan;
  /**
   * Creates a new SusanLower.
   */
    public SusanLower(LazySusan L) {
    // Use addRequirements() here to declare subsystem dependencies.
    susan = L;
    addRequirements(susan);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    susan.lowerSusan();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    susan.stopLift();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
