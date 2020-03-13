/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LazySusanLift extends SubsystemBase {
  /**
   * Creates a new LazySusanLift.
   */
  private TalonFX susanLift;
  public double liftSpeed = Constants.LIFTSPEED;

  public LazySusanLift() {
    susanLift = new TalonFX(Constants.LAZY_LIFT_CAN);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void susanUpDown(XboxController controller)
	{
    double joystickY = -controller.getRawAxis(Constants.XBOX_RIGHT_Y_AXIS);
    susanLift.set(ControlMode.PercentOutput, joystickY*liftSpeed);
  }
 
  public void stopLift()
  {
    susanLift.set(ControlMode.PercentOutput,0);
  }

}
