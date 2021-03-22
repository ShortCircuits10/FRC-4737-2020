/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DisableShooter;

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

public class ShooterSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static ShooterSub instance;
  public WPI_TalonSRX Shooter_Motor1;
  public WPI_TalonSRX Shooter_Motor2;


  public ShooterSub() {

    Shooter_Motor1 = new WPI_TalonSRX(RobotMap.SHOOTER_MOTOR1);
    Shooter_Motor2 = new WPI_TalonSRX(RobotMap.SHOOTER_MOTOR2);
    
  }

  public void setSpeed (double speed) {
    Shooter_Motor1.set(speed);
    Shooter_Motor2.set(speed);
  }



  public static ShooterSub getInstance() {
    if (instance == null) {
        instance = new ShooterSub();
    }

    return instance;
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DisableShooter());
  }
}
