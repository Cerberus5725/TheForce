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
import frc.robot.commands.AutoShootLong;
import frc.robot.commands.RangeUniversal;
import frc.robot.commands.RightPositionSeek;
import frc.robot.commands.VisionUniversal;
import frc.robot.commands.WinchDown;
import frc.robot.commands.WinchUp;
import frc.robot.commands.DriveWithJoySticks;
import frc.robot.commands.InvertDriveTrain;
import frc.robot.commands.LeftPositionSeek;
import frc.robot.commands.IntakeSystem;
import frc.robot.commands.ShootBall;
import frc.robot.commands.ShootLongDrive;
import frc.robot.commands.ShootReturner;
import frc.robot.commands.SpinBlue;
import frc.robot.commands.SpinGreen;
import frc.robot.commands.SpinRed;
import frc.robot.commands.SpinYellow;
import frc.robot.commands.SusanLeftRight;
import frc.robot.commands.SusanUpDown;
import frc.robot.commands.TurnLeftTimed;
import frc.robot.commands.TurnRightTimed;
import frc.robot.commands.Vision;
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
import frc.robot.subsystems.LazySusanLift;
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
  private final AutoShootLong autoShootLong;

  private final Intake intake;
  private final IntakeSystem intakeSystem;


  // Lazy Susan
   private final LazySusan lazySusan;
   private final SusanLeftRight susanLeftRight;
   private final SpinBlue spinBlue;
   private final SpinRed spinRed;
   private final SpinYellow spinYellow;
   private final SpinGreen spinGreen;

  // Lazy Susan Lift
   private final LazySusanLift lazySusanLift;
   private final SusanUpDown susanUpDown;
   //winch
   private final Climb climb;
   private final WinchUp winchUp;
   private final WinchDown winchDown;

  //camera
  private final Camera camera;
  private final Vision vision;

   // Autonomous
   private final RangeUniversal rangeUniversal;
   private final VisionUniversal visionUniversal;
   private final ShootLongDrive shootLongDrive;
   private final LeftPositionSeek leftPositionSeek;
   private final RightPositionSeek rightPositionSeek;


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
    autoShootLong = new AutoShootLong(shooter, intake);
    autoShootLong.addRequirements(shooter,intake);

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
    susanLeftRight = new SusanLeftRight(lazySusan);
    susanLeftRight.addRequirements(lazySusan);
    lazySusan.setDefaultCommand(susanLeftRight);
    spinBlue = new SpinBlue(lazySusan);
    spinBlue.addRequirements(lazySusan);
    spinRed = new SpinRed(lazySusan);
    spinRed.addRequirements(lazySusan);
    spinYellow = new SpinYellow(lazySusan);
    spinYellow.addRequirements(lazySusan);
    spinGreen = new SpinGreen(lazySusan);
    spinGreen.addRequirements(lazySusan);

    

    //Lazy Susan Lift
    lazySusanLift = new LazySusanLift();
    susanUpDown = new SusanUpDown(lazySusanLift);
    susanUpDown.addRequirements(lazySusanLift);
    lazySusanLift.setDefaultCommand(susanUpDown);

    //Winch
    climb = new Climb();
    winchUp = new WinchUp(climb);
    winchUp.addRequirements(climb);
    winchDown = new WinchDown(climb);
    winchDown.addRequirements(climb);

    //Camera
    camera = new Camera();
    vision = new Vision(driveTrain,camera);
    vision.addRequirements(driveTrain,camera);


    // Initialize autonomous commands groups
    rangeUniversal = new RangeUniversal(driveTrain, shooter, intake);
    visionUniversal = new VisionUniversal(driveTrain, shooter, intake, camera);
    shootLongDrive = new ShootLongDrive(driveTrain, shooter, intake);
    leftPositionSeek = new LeftPositionSeek(camera, driveTrain, shooter, intake);
    rightPositionSeek = new RightPositionSeek(camera, driveTrain, shooter, intake);

   // Chooser options in Smart Dashboard
    m_chooser.addOption("Range Finder Universal", rangeUniversal);
    m_chooser.addOption("Shoot Long Drive", shootLongDrive);
    m_chooser.addOption("Left Position Seek", leftPositionSeek);
    m_chooser.addOption("Right Position Seek", rightPositionSeek);
    // Default option
    m_chooser.setDefaultOption("Vision Universal", visionUniversal);

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

    JoystickButton winchUpButton = new JoystickButton(driverJoystick, XboxController.Button.kStart.value);
    winchUpButton.whileHeld(new WinchUp(climb));

    JoystickButton winchDownButton = new JoystickButton(driverJoystick, XboxController.Button.kBack.value);
    winchDownButton.whileHeld(new WinchDown(climb));

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
    JoystickButton blueButton = new JoystickButton(driverJoystick, XboxController.Button.kX.value);
    blueButton.whenPressed(new SpinBlue(lazySusan));

    JoystickButton redButton = new JoystickButton(driverJoystick, XboxController.Button.kB.value);
    redButton.whenPressed(new SpinRed(lazySusan));

    JoystickButton yellowButton = new JoystickButton(driverJoystick, XboxController.Button.kY.value);
    yellowButton.whenPressed(new SpinYellow(lazySusan));

    JoystickButton greenButton = new JoystickButton(driverJoystick, XboxController.Button.kA.value);
    greenButton.whenPressed(new SpinGreen(lazySusan));

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
