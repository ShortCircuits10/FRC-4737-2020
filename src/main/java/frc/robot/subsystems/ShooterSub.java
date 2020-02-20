/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.DisableShooter;
/**
 * Add your docs here.
 */
public class ShooterSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static ShooterSub instance;
  public WPI_TalonSRX Shooter;

  public ShooterSub() {

    Shooter = new WPI_TalonSRX(RobotMap.SHOOTER);
  
  }

  public void setSpeed (double speed) {
    Shooter.set(speed);
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
