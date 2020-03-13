/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LazySusan extends SubsystemBase {
  /**
   * Creates a new LazySusan.
   */
  private VictorSP susanSpinner;
  public double spinnerSpeed = Constants.SPINNERSPEED;


  public LazySusan() 
  {
    susanSpinner = new VictorSP(Constants.LAZY_SPINNER_MOTOR);
    addChild("SusanSpinner", susanSpinner);
    susanSpinner.setInverted(false);

    //susanLift = new VictorSP(Constants.LAZY_LIFT_MOTOR);
    //addChild("SusanLift", susanLift);
    //susanLift.setInverted(true);
  }


  public void susanLeftRight(XboxController controller)
  {
    double joystickX = -controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS);
    susanSpinner.set(joystickX*spinnerSpeed);
  }

  public void spinBlue()
  {
   // Program to spin to blue
  }

  public void spinRed()
  {
   // Program to spin to red
  }

  public void spinYellow()
  {
    // Program to spin to yellow
  }

  public void spinGreen()
 {

 }

  public void stopSpinner()
  {
    susanSpinner.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
