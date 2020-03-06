/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ShooterSub;
import frc.robot.subsystems.DrivetrainSub;
import frc.robot.subsystems.LimeSub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Translation2d;

public class LimelightShooter extends Command {
  
  public LimelightShooter() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(ShooterSub.getInstance());
    requires(DrivetrainSub.getInstance());
    requires(LimeSub.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");

    //read values periodically
    double rotation = 0;
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double target = tv.getDouble(0.0);
    double targetArea = 0.5; //play
    double distance = area/targetArea;
    double xGive = 0.05;  //play
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
                    //play
    if (target == 1.0 && x <= xGive) {
      ShooterSub.getInstance().setSpeed(0.1*distance);
    } else if (target == 1.0) {
      rotation = x*0.5; //play
      DrivetrainSub.getInstance().drive(new Translation2d(0, 0), rotation, true);
    } else {
      rotation = 0.3; //play
      DrivetrainSub.getInstance().drive(new Translation2d(0,0), rotation, true);
    }
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
