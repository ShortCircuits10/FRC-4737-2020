// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DisableEncoder;
//import frc.robot.commands.DisableShooter;
//import frc.robot.commands.Shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.*;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

//import com.ctre.phoenix.motorcontrol.can.*;

/** Add your docs here. */
public class EncoderSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX Shooter_Motor3;
  private static EncoderSub instance;
  public int level = 0;
  //private static final double GetShooterHeight = 0;

public EncoderSub(){
    Shooter_Motor3 = new WPI_TalonSRX(RobotMap.SHOOTER_MOT0R3);

    //Shooter_Motor3.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

}

public void setEncoderSpeed (double speed) {
  Shooter_Motor3.set(speed);
}




public static EncoderSub getInstance() {
  if (instance == null) {
      instance = new EncoderSub();
  }

  return instance;
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new DisableEncoder());
  }
}
