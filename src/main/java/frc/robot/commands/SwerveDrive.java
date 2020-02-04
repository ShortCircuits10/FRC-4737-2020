/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;



import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.geometry.Translation2d;
//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.drive.Vector2d;
//import edu.wpi.first.wpilibj.Utilities;
import org.frcteam2910.common.robot.Utilities;






/**
 * An example command.  You can replace me with your own command.
 */
public class SwerveDrive extends Command {
  public SwerveDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Drivetrain.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double forward = -Robot.getOi().getPrimaryJoystick().getRawAxis(1);
    forward = Utilities.deadband(forward);
    // Square the forward stick
    forward = Math.copySign(Math.pow(forward, 2.0), forward);

    double strafe = -Robot.getOi().getPrimaryJoystick().getRawAxis(0);
  strafe = Utilities.deadband(strafe);
    // Square the strafe stick
    strafe = Math.copySign(Math.pow(strafe, 2.0), strafe);

    double rotation = -Robot.getOi().getPrimaryJoystick().getRawAxis(4);
   rotation = Utilities.deadband(rotation);
    // Square the rotation stick
    rotation = Math.copySign(Math.pow(rotation, 2.0), rotation);

    Drivetrain.getInstance().drive(new Translation2d(forward, strafe), rotation, true);

  }
  	
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
