/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
//import frc.robot.subsystems.ColorWheelSub;

/**
 * Add your docs here.
 */
public class ColorWheelPneumatics extends InstantCommand {
  /**
   * Add your docs here.
   */
  private boolean extended;

  public ColorWheelPneumatics(boolean extend) {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(Robot.COLORWHEELSUB);
    
    this.extended = extended;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

    if (extended) {
      Robot.COLORWHEELSUB.extendExtender();
    } else {
      Robot.COLORWHEELSUB.retractExtender();
    
  }


  }

}
