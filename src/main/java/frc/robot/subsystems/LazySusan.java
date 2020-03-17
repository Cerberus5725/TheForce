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
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class LazySusan extends SubsystemBase {
  // Public varibles
  private VictorSP susanSpinner;

  public double spinnerSpeed = Constants.SPINNERSPEED;
  public double spinnerSpeedColor = Constants.SPINNERSPEEDCOLOR;
  private double joystickOverrideTolerance = Constants.JOYSTICK_OVERRIDE_TOLERANCE;

  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


  public LazySusan() 
  {
    susanSpinner = new VictorSP(Constants.LAZY_SPINNER_MOTOR);
    addChild("SusanSpinner", susanSpinner);
    susanSpinner.setInverted(false);
    // Color Matcher
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
  }


  public void susanLeftRight(XboxController controller)
  {
    double joystickX = -controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS);
    susanSpinner.set(joystickX*spinnerSpeed);
  }

  public boolean spinToColor(String color, XboxController controller)
  {
    String colorString = "Unknown";

    // While Colors don't match and no joystick override
    while(!color.equals(colorString) && !joystickOverRide(controller))
    {
    Color detectedColor;
    ColorMatchResult match;
    detectedColor = m_colorSensor.getColor();
    match = m_colorMatcher.matchClosestColor(detectedColor);
      if (match.color == kBlueTarget) {
      colorString = "Blue";
      } else if (match.color == kRedTarget) {
      colorString = "Red";
      } else if (match.color == kGreenTarget) {
      colorString = "Green";
      } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
      } else {
      colorString = "Unknown";
      }
    // Display the Color and the Confidence
    //System.out.println(colorString);
    //SmartDashboard.putNumber("Red", detectedColor.red);
    //SmartDashboard.putNumber("Green", detectedColor.green);
    //SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    // Run the motor
    driveForward();
    }
    return true;  
  }

  public void driveForward()
  {
    susanSpinner.set(spinnerSpeedColor);
  }

  public boolean joystickOverRide(XboxController controller)
  {
    if(Math.abs(controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS)) < joystickOverrideTolerance)
    {
      return false;
    }
    else
    {
      return true;
    }
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
