// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.hal.sim.EncoderSim;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DisableShooter;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;



/** Add your docs here. */
public class AdjustShootersub extends Subsystem {
  
  public WPI_TalonSRX Shooter_Motor3;
  public static AdjustShootersub instance;
  public static EncoderSim EncoderMotor;


 
  public AdjustShootersub() {
    Shooter_Motor3 = new WPI_TalonSRX(RobotMap.SHOOTER_MOTOR3);
    EncoderMotor = new EncoderSim(13);
  }
  


  public void setAdjusterSpeed (double speed) {
    Shooter_Motor3.set(speed);
  }

  public void getCount() {
    EncoderMotor.getCount();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new DisableShooter());
  }

  public static AdjustShootersub getInstance() {
    if (instance == null) {
        instance = new AdjustShootersub();
    }

    return instance;
}

}
