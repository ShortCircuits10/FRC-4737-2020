/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.LiftSub;

/**
 * Add your docs here.
 */
public class LiftPneumatics extends InstantCommand {
  /**
   * Add your docs here.
   */
  private boolean extended;

  public LiftPneumatics(boolean extended) {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(LiftSub.getInstance());
    
    this.extended = extended;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

    if (extended) {
      LiftSub.getInstance().extendExtender();
    } else {
      LiftSub.getInstance().retractExtender();
    
  }


  }

}
