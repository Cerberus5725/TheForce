/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class LazySusan extends SubsystemBase {
  /**
   * Creates a new LazySusan.
   */
  private VictorSP susanSpinner;
  public double spinnerSpeed = Constants.SPINNERSPEED;
  //private VictorSP susanLift;
  private TalonFX susanLift;
  public double liftUp = Constants.LIFTUP;
  public double liftDrop = Constants.LIFTDROP;

  public LazySusan() 
  {
    susanSpinner = new VictorSP(Constants.LAZY_SPINNER_MOTOR);
    addChild("SusanSpinner", susanSpinner);
    susanSpinner.setInverted(false);

    //susanLift = new VictorSP(Constants.LAZY_LIFT_MOTOR);
    susanLift = new TalonFX(Constants.LAZY_LIFT_CAN);
    //addChild("SusanLift", susanLift);
    //susanLift.setInverted(true);
  }

  public void spinWheelRight()
  {
    susanSpinner.set(spinnerSpeed);
  }

  public void spinWheelLeft()
  {
    susanSpinner.set(-spinnerSpeed);
  }

  public void liftSusan()
  {
    susanLift.set(ControlMode.PercentOutput,-liftUp);
  }

  public void lowerSusan()
  {
    susanLift.set(ControlMode.PercentOutput, liftDrop);
  }

  public void stopSpinner()
  {
    susanSpinner.set(0);
  }
  
  public void stopLift()
  {
    susanLift.set(ControlMode.PercentOutput,0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
