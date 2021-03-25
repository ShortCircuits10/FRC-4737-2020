// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
//import frc.robot.RobotMap;
import frc.robot.commands.ResetEncoder;

/** Add your docs here. */
public class EncoderSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Encoder encoder = new Encoder(0, 1);

public void getDistance(){
  encoder.getDistance();
}

public void resetEncoder(){
    encoder.reset();
}

public void setDistance(double distancePerPulse){
  encoder.setDistancePerPulse(distancePerPulse);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new ResetEncoder());
  }
}
