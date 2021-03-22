// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.AdjustShootersub;
import edu.wpi.first.hal.sim.EncoderSim;


/** Add your docs here. */
public class GetAdjusterHeight extends InstantCommand {
  /** Add your docs here. */
  public int CurrentHeight;
  public static EncoderSim EncoderMotor;

  public GetAdjusterHeight() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(AdjustShootersub.getInstance());
    EncoderMotor = new EncoderSim(13);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    CurrentHeight = EncoderMotor.getCount();
    System.out.println(CurrentHeight);
  }
}
