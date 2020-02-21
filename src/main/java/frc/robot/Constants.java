/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Motor Speeds
    public static double DRIVETRAINSPEED = 0.55;
    public static double AUTONOMOUSSPEED = 0.3;
    public static double INTAKESPEED = 0.5;
    public static double SHOOTERSPEED = 0.65;
    public static double SPINNERSPEED = 0.25;
    public static double LIFTUP = 0.3;
    public static double LIFTDROP = 0.2;
    public static double WINCHSPEED = 0.5;
    //Autononmous Variables
    // DriveTrain SetPoint Distance
    public static double SETPOINT_FORWARD_CLOSE = 1.3;
    public static double SETPOINT_FORWARD_FAR = 1.85;
    // Auto joystick override tolerance
    public static double JOYSTICK_OVERRIDE_TOLERANCE = 0.1;
    public static double PRELOAD_TIME = 2.5;
    public static double SHOOT_TIME = 5.0;
    // Joystick Number
    public static int JOYSTICK_NUMBER = 0;
    // Motor PWM Numbers
    public static int LEFT_FRONT_MOTOR = 0;
    public static int LEFT_BACK_MOTOR = 1;
    public static int RIGHT_FRONT_MOTOR = 2;
    public static int RIGHT_BACK_MOTOR = 3;
    //Shoot and Intake PWM Numbers
    public static int SHOOT_MOTOR = 5;
    //public static int INTAKE_MOTOR = 6;
    public static int GATE_PWM = 7;
    //Lazy Susan PMW Numbers
    public static int LAZY_SPINNER_MOTOR = 8;
    public static int LAZY_LIFT_MOTOR = 9;
    //Climb PMW Numbers
    public static int WINCH_MOTOR = 4;    
    // CAN Numbers's
    public static int INTAKE_CAN = 6;
    public static int WINCH_CAN = 4;
    public static int LAZY_LIFT_CAN = 9;
    //Axis Numbers
    public static int XBOX_LEFT_X_AXIS = 0;
    public static int XBOX_LEFT_Y_AXIS = 1;
    public static int XBOX_RIGHT_Y_AXIS = 5;
}
