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
    public static double INTAKESPEED = 1.00;
    public static double SHOOTERSPEED = 0.5;
    // Joystick Number
    public static int JOYSTICK_NUMBER = 0;
    // Motor PWM Numbers
    public static int LEFT_FRONT_MOTOR = 0;
    public static int LEFT_BACK_MOTOR = 1;
    public static int RIGHT_FRONT_MOTOR = 2;
    public static int RIGHT_BACK_MOTOR = 3;
    public static int SHOOT_MOTOR_LEFT = 4;
    public static int SHOOT_MOTOR_RIGHT = 5;
    public static int INTAKE_MOTOR = 6;
    public static int XBOX_LEFT_X_AXIS = 0;
    public static int XBOX_LEFT_Y_AXIS = 1;
}
