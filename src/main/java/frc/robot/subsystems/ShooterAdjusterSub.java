/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.hal.sim.EncoderSim;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DisableShooterAdjuster;
//import frc.robot.commands.GetAdjusterHeight;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/*
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.can.*;
*/
//import frc.robot.Robot;

/**
 * Add your docs here.
 */

public class ShooterAdjusterSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static ShooterAdjusterSub instance;
  public WPI_TalonSRX Shooter_Motor3;
  public static Encoder EncoderMotor;
  public int shooterMotor3Encoder = 13;


  //Encoder enc;
// public int level = 0;

public ShooterAdjusterSub() {
  Shooter_Motor3 = new WPI_TalonSRX(RobotMap.SHOOTER_MOTOR3);
  //EncoderMotor = Encoder(RobotMap.SHOOTER_MOTOR3_ENCODER);
}

// int absolutePosition =
// Shooter_Motor3.getSensorCollection().getPulseWidthPosition();


public void setHeight(double DistancePerPulse) {
  EncoderMotor.setDistancePerPulse(DistancePerPulse);
}


public void setAdjusterSpeed(double speed) {
  Shooter_Motor3.set(speed);
  //GetAdjusterHeight = GetAdjusterHeight + 1.0;
  }





  public static ShooterAdjusterSub getInstance() {
    if (instance == null) {
        instance = new ShooterAdjusterSub();
    }

    return instance;
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DisableShooterAdjuster());
  }


}
