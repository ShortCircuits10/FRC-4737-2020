// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import edu.wpi.first.hal.sim.EncoderSim;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DisableShooter;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;



/** Add your docs here. */
public class AdjustShootersub extends Subsystem {
  
  public WPI_TalonSRX Shooter_Motor3;
  public static AdjustShootersub instance;
  public static Encoder EncoderMotor;
 
  public AdjustShootersub() {
    Shooter_Motor3 = new WPI_TalonSRX(RobotMap.SHOOTER_MOTOR3);
    EncoderMotor = Encoder(RobotMap.SHOOTER_MOTOR3);
  }
  




  private Encoder Encoder(int shooterMotor3) {
    return EncoderMotor;
  }

  public void setAdjusterSpeed(double speed) {
    Shooter_Motor3.set(speed);
  }

  public void setDistance(double distancePerPulse) {
    EncoderMotor.setDistancePerPulse(distancePerPulse);
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
