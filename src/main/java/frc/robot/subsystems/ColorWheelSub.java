/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */

public class ColorWheelSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static ColorWheelSub instance;
  public WPI_TalonSRX ColorWheel;
  public DoubleSolenoid ColorSolenoid;
  //private Solenoid Colorsolenoid;
  public ColorWheelSub() {

    ColorWheel = new WPI_TalonSRX(RobotMap.COLOR_WHEEL_MOTOR);
    //ColorSolenoid = new Solenoid(RobotMap.COLORWHEEL_SINGLESOLENOID_FORWARDCHANNEL, RobotMap.COLORWHEEL_SINGLESOLENOID_REVERSECHANNEL);
    ColorSolenoid = new DoubleSolenoid(RobotMap.COLORWHEEL_SINGLESOLENOID_FORWARDCHANNEL, RobotMap.COLORWHEEL_SINGLESOLENOID_REVERSECHANNEL);
  }
 
  public void extendExtender() {
    ColorSolenoid.set(Value.kForward);
  }

  public void retractExtender() {
    ColorSolenoid.set(Value.kReverse);
  }

  public void disablePneumatics() {
    ColorSolenoid.set(Value.kOff);
  }

  public static ColorWheelSub getInstance() {
    
    if (instance == null) {
        instance = new ColorWheelSub();
    }
    return instance;
   
  }

  public void setSpeed (double speed) {
    ColorWheel.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new Di);
  }
}
