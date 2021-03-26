// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.ShooterAdjusterSub;

/** Add your docs here. */
public class ResetEncoder extends InstantCommand {
  /** Add your docs here. */
  public ResetEncoder() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(ShooterAdjusterSub.getInstance());
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    ShooterAdjusterSub.getInstance().reset();
  }
}
