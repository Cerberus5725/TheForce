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
    public static double DRIVETRAINSPEED = 0.45;
    public static double AUTONOMOUSSPEED = 0.3;
    public static double VEER_SLOW = 0.2;
    public static double VEER_FAST = 0.35;
    public static double INTAKESPEED = 0.5;
    public static double AUTO_INTAKE_SPEED = 0.25;
    public static double SHOOTERSPEED = 0.65;
    public static double SHOOTERSPEEDLONG = 0.8;
    public static double SPINNERSPEED = 0.75;
    public static double SPINNERSPEEDCOLOR = 0.2;
    public static double LIFTSPEED = 0.1;
    public static double WINCHSPEED = 0.8;
    public static double REVOLVERSPEED = 0.5;
    //Autononmous Variables
    // DriveTrain SetPoint Distance
    public static double SETPOINT_FORWARD_CLOSE = 0.31;
    public static double SETPOINT_FORWARD_FAR = 0.95;
    public static double DRIVE_FORWARD_TIME = 4.0;
    public static double DRIVE_BACKWARD_TIME = 1.0;
    public static double TURN_TIME_45 = 0.8;
    public static double TURN_TIME_90 = 1.5;
    // Auto joystick override tolerance
    public static double JOYSTICK_OVERRIDE_TOLERANCE = 0.1;
    public static double PRELOAD_TIME = 1.0;
    public static double AUTO_INTAKE_DELAY = 4;
    public static double SHOOT_TIME = 8.0;
    // Joystick Number
    public static int JOYSTICK_NUMBER = 0;
        //Axis Numbers
        public static int XBOX_LEFT_X_AXIS = 0;
        public static int XBOX_LEFT_Y_AXIS = 1;
        public static int XBOX_RIGHT_Y_AXIS = 5;
        public static int XBOX_RIGHT_X_AXIS = 4;
    // Motor PWM Numbers
    public static int LEFT_FRONT_MOTOR = 0;
    public static int LEFT_BACK_MOTOR = 1;
    public static int RIGHT_FRONT_MOTOR = 2;
    public static int RIGHT_BACK_MOTOR = 3;
    public static int REVOLVER_MOTOR_LEFT = 4;
    public static int SHOOT_MOTOR = 5;
    public static int REVOLVER_MOTOR_RIGHT = 6;
    public static int GATE_PWM = 7;
    //Lazy Susan PMW Numbers
    public static int LAZY_SPINNER_MOTOR = 8;
    //Climb PMW Numbers
    //public static int WINCH_MOTOR = 4;    
    // CAN Numbers's
    public static int INTAKE_CAN = 6;
    public static int WINCH_CAN = 4;
    public static int LAZY_LIFT_CAN = 9;

    // Vision Tracking
    //Camera Res
    public static int CAMERA_RES_X = 320;
    public static int CAMERA_RES_Y = 240;
    public static double TARGET = CAMERA_RES_X/2;
	public static double PRECISION = 15.0;
    public static double DELAY_TIME = 0.25;

}
