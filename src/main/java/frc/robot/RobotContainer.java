/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.AutonomousOne;
import frc.robot.commands.AutonomousTwo;
import frc.robot.commands.DriveWithJoySticks;
import frc.robot.commands.InvertDriveTrain;
import frc.robot.commands.IntakeSystem;
import frc.robot.commands.ShootBall;
import frc.robot.commands.ShootReturner;
import frc.robot.commands.SpinWheelLeft;
import frc.robot.commands.SpinWheelRight;
import frc.robot.commands.SusanLift;
import frc.robot.commands.SusanLower;
import frc.robot.commands.TurnLeftTimed;
import frc.robot.commands.TurnRightTimed;
import frc.robot.commands.Vision;
import frc.robot.commands.WinchStick;
import frc.robot.commands.DriveForward;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveBackward;
import frc.robot.commands.DriveBackwardTimed;
import frc.robot.commands.DriveLeft;
import frc.robot.commands.DriveRight;
import frc.robot.commands.DriveToDistanceClose;
import frc.robot.commands.DriveToDistanceFar;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LazySusan;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Camera;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //Drivetrain
  private final DriveTrain driveTrain;
  private final DriveWithJoySticks joystickDrive;
  private final DriveForward driveForward;
  private final DriveBackward driveBackward;
  private final DriveLeft driveLeft;
  private final DriveRight driveRight;
  private final DriveToDistanceClose driveToDistanceClose;
  private final DriveToDistanceFar driveToDistanceFar;
  private final DriveForwardTimed driveForwardTimed;
  private final DriveBackwardTimed driveBackwardTimed;
  private final TurnRightTimed turnRightTimed;
  private final TurnLeftTimed turnLeftTimed;

  //Joystick
  public static XboxController driverJoystick;
  private final InvertDriveTrain joystickInvert;

  //Intake and Shooter
  private final BallShooter shooter;
  private final ShootBall shootBall;
  private final AutoShoot autoShoot;

  private final Intake intake;
  private final IntakeSystem intakeSystem;


  // Lazy Susan
   private final LazySusan lazySusan;
   private final SpinWheelLeft spinWheelLeft;
   private final SpinWheelRight spinWheelRight;
   private final SusanLift susanLift;
   private final SusanLower susanLower;

   //winch
   private final Climb climb;
   private final WinchStick winchStick;

  //camera
  private final Camera camera;
  private final Vision vision;

   // Autonomous
   private final AutonomousOne autoOne;
   private final AutonomousTwo autoTwo;


   // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    //Drivetrain Joystick
    driveTrain = new DriveTrain();
    joystickDrive = new DriveWithJoySticks(driveTrain);
    joystickDrive.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(joystickDrive);

    driverJoystick = new XboxController(Constants.JOYSTICK_NUMBER);
    joystickInvert = new InvertDriveTrain(driveTrain);
    joystickInvert.addRequirements(driveTrain);

    driverJoystick = new XboxController(Constants.JOYSTICK_NUMBER);
    //Intake and Shooter
    intake = new Intake();
    intakeSystem = new IntakeSystem(intake);
    intakeSystem.addRequirements(intake);
    intake.setDefaultCommand(intakeSystem);
    // Ball Shooter
    shooter = new BallShooter();
    shootBall = new ShootBall(shooter);
    shootBall.addRequirements(shooter);
    autoShoot = new AutoShoot(shooter, intake);
    autoShoot.addRequirements(shooter,intake);

    //Drive with DPad
    driveForward = new DriveForward(driveTrain);
    driveForward.addRequirements(driveTrain);
    driveBackward = new DriveBackward(driveTrain);
    driveBackward.addRequirements(driveTrain);    
    driveLeft = new DriveLeft(driveTrain);
    driveLeft.addRequirements(driveTrain);
    driveRight = new DriveRight(driveTrain);
    driveRight.addRequirements(driveTrain);
    // Autonomous DriveTrain Commands
    driveToDistanceClose = new DriveToDistanceClose(driveTrain);
    driveToDistanceClose.addRequirements(driveTrain);
    driveToDistanceFar = new DriveToDistanceFar(driveTrain);
    driveToDistanceFar.addRequirements(driveTrain);
    driveForwardTimed = new DriveForwardTimed(driveTrain);
    driveForwardTimed.addRequirements(driveTrain);
    driveBackwardTimed = new DriveBackwardTimed(driveTrain);
    driveBackwardTimed.addRequirements(driveTrain);
    turnRightTimed = new TurnRightTimed(driveTrain);
    turnRightTimed.addRequirements(driveTrain);
    turnLeftTimed = new TurnLeftTimed(driveTrain);
    turnLeftTimed.addRequirements(driveTrain);



    //Lazy Susan
    lazySusan = new LazySusan();
    spinWheelLeft = new SpinWheelLeft(lazySusan);
    spinWheelLeft.addRequirements(lazySusan);
    spinWheelRight = new SpinWheelRight(lazySusan);
    spinWheelRight.addRequirements(lazySusan);
    susanLower = new SusanLower(lazySusan);
    susanLower.addRequirements(lazySusan);
    susanLift = new SusanLift(lazySusan);
    susanLift.addRequirements(lazySusan);

    //Winch
    climb = new Climb();
    winchStick = new WinchStick(climb);
    winchStick.addRequirements(climb);
    climb.setDefaultCommand(winchStick);

    //Camera
    camera = new Camera();
    vision = new Vision(driveTrain,camera);
    vision.addRequirements(driveTrain,camera);


    // Initialize autonomous commands here as they need the depend subsystems and commands initialized first.
    autoOne = new AutonomousOne(driveTrain, shooter, intake);
    autoTwo = new AutonomousTwo(driveTrain, shooter, intake, camera);

    m_chooser.addOption("Auto One", autoOne);
    m_chooser.addOption("Auto Two", autoTwo);
    m_chooser.setDefaultOption("Auto One", autoOne);

    // Put the chooser on the shuffle board dashboard and in smartdashboard
    //Shuffleboard.getTab("Autonomous").add(m_chooser);
    //SmartDashboard.putString("AUTO CHOOSER", "TRUE");
    SmartDashboard.putData("Autonomous", m_chooser);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Joystick Buttons
    JoystickButton shootButton = new JoystickButton(driverJoystick,XboxController.Button.kBumperRight.value);
    shootButton.whileHeld(new ShootBall(shooter));

    JoystickButton shootReturnButton = new JoystickButton(driverJoystick,XboxController.Button.kBumperLeft.value);
    shootReturnButton.whileHeld(new ShootReturner(shooter));

    JoystickButton invertButton = new JoystickButton(driverJoystick, XboxController.Button.kStickLeft.value);
    invertButton.whenPressed(new InvertDriveTrain(driveTrain));

    JoystickButton autoButtonRight = new JoystickButton(driverJoystick, XboxController.Button.kStart.value);
    autoButtonRight.whenPressed(new AutonomousOne(driveTrain, shooter, intake));

    JoystickButton autoButtonLeft = new JoystickButton(driverJoystick, XboxController.Button.kBack.value);
    autoButtonLeft.whenPressed(new AutonomousTwo(driveTrain, shooter, intake, camera));

    //Pov or Dpad Buttons
    POVButton driveForwardButton = new POVButton(driverJoystick, 0);
    driveForwardButton.whileHeld(new DriveForward(driveTrain));

    POVButton driveBackwardButton = new POVButton(driverJoystick, 180);
    driveBackwardButton.whileHeld(new DriveBackward(driveTrain));

    POVButton driveLeftButton = new POVButton(driverJoystick, 270);
    driveLeftButton.whileHeld(new DriveLeft(driveTrain));

    POVButton driveRightButton = new POVButton(driverJoystick, 90);
    driveRightButton.whileHeld(new DriveRight(driveTrain));

    //Letter Buttons
    JoystickButton spinLeftButton = new JoystickButton(driverJoystick, XboxController.Button.kX.value);
    spinLeftButton.whileHeld(new SpinWheelLeft(lazySusan));

    JoystickButton spinRightButton = new JoystickButton(driverJoystick, XboxController.Button.kB.value);
    spinRightButton.whileHeld(new SpinWheelRight(lazySusan));

    JoystickButton susanUpButton = new JoystickButton(driverJoystick, XboxController.Button.kY.value);
    susanUpButton.whileHeld(new SusanLift(lazySusan));

    JoystickButton susanDownButton = new JoystickButton(driverJoystick, XboxController.Button.kA.value);
    susanDownButton.whileHeld(new SusanLower(lazySusan));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
