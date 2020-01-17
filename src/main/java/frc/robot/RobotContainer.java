/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.DriveWithJoySticks;
import frc.robot.commands.EjectBallOut;
import frc.robot.commands.InvertDriveTrain;
import frc.robot.commands.TakeBallIn;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain;
  private final DriveWithJoySticks joystickDrive;
  private final InvertDriveTrain joystickInvert;
  public static XboxController driverJoystick;

  private final Intake intake;
  private final AutonomousCommand auto;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    driveTrain = new DriveTrain();
    joystickDrive = new DriveWithJoySticks(driveTrain);
    joystickDrive.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(joystickDrive);
    driverJoystick = new XboxController(Constants.JOYSTICK_NUMBER);
    joystickInvert = new InvertDriveTrain(driveTrain);
    joystickInvert.addRequirements(driveTrain);


    intake = new Intake();
    auto = new AutonomousCommand();

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton intakeButton = new JoystickButton(driverJoystick, XboxController.Button.kBumperRight.value);
    intakeButton.whileHeld(new TakeBallIn(intake));

    JoystickButton ejectButton = new JoystickButton(driverJoystick, XboxController.Button.kBumperLeft.value);
    ejectButton.whileHeld(new EjectBallOut(intake));

    JoystickButton invertButton = new JoystickButton(driverJoystick, XboxController.Button.kX.value);
    invertButton.whenPressed(new InvertDriveTrain(driveTrain));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return auto;
  }
}
