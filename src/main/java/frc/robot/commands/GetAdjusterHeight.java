// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ShooterAdjusterSub;

public class GetAdjusterHeight extends Command {
  public int currentHeight;
  public WPI_TalonSRX Shooter_Motor3;

  public GetAdjusterHeight() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(ShooterAdjusterSub.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      currentHeight = Shooter_Motor3.getSelectedSensorPosition();
      System.out.println(currentHeight);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
