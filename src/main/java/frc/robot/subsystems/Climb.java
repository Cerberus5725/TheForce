/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {
  /**
   * Creates a new Climb.
   */
  public double winchSpeed = Constants.WINCHSPEED;
  private Spark winchMotor;
  
  public Climb() {
    winchMotor = new Spark(Constants.WINCH_MOTOR);
    addChild("WinchMotor", winchMotor);
    winchMotor.setInverted(false);
  }

  
  public void winchStick(XboxController controller)
	{
    double joystickY = -controller.getRawAxis(Constants.XBOX_RIGHT_Y_AXIS);
    winchMotor.set(winchSpeed * joystickY);
  }
  public void stop()
  {
    winchMotor.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
