// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotMap;
import frc.robot.subsystems.AdjustShootersub;
//import edu.wpi.first.hal.sim.EncoderSim;


/** Add your docs here. */
public class GetAdjusterHeight extends InstantCommand {
  /** Add your docs here. */
  public double CurrentHeight;
  public static Encoder EncoderMotor;
  

  public GetAdjusterHeight() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(AdjustShootersub.getInstance());
    EncoderMotor = Encoder(RobotMap.SHOOTER_MOTOR3);
  }

 


  private Encoder Encoder(int shooterMotor3) {
    return EncoderMotor;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    CurrentHeight = EncoderMotor.getDistance();
    System.out.println(CurrentHeight);
  }
}
