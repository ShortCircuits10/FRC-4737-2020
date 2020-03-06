/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DisableIntake;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import frc.robot.Robot;


/**
 * Add your docs here.
 */

public class IntakeSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static IntakeSub instance;
  public WPI_TalonSRX Intakemotor;
  public WPI_TalonSRX Intakebeltmotor;
  public WPI_TalonSRX Ballelevatormotor;


  public IntakeSub() {
    Intakemotor = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR1);
    Intakebeltmotor = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR2);
    Ballelevatormotor = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR3);
  }

  public static IntakeSub getInstance() {
    if (instance == null) {
        instance = new IntakeSub();
    }

    return instance;
}
public void setIntakeSpeed(double speed) {
  Intakemotor.set(speed);
}
public void setIntakeBeltSpeed(double speed) {
  Intakebeltmotor.set(speed);
}
public void setBallElevatorSpeed(double speed) {
  Ballelevatormotor.set(speed);
}
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DisableIntake());
  }
}
