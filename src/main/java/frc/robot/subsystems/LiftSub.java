/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import frc.robot.commands.DisableLift;
/**
 * Add your docs here.
 */
public class LiftSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static LiftSub instance;
  public WPI_TalonSRX LiftMotor;
  public DoubleSolenoid LeftLiftSolenoid;
  public DoubleSolenoid RightLiftSolenoid;

  public LiftSub() {

    LiftMotor = new WPI_TalonSRX(RobotMap.LIFT_MOTOR);
    LeftLiftSolenoid = new DoubleSolenoid(RobotMap.LEFTLIFT_SINGLESOLENOID_FORWARDCHANNEL, RobotMap.LEFTLIFT_SINGLESOLENOID_REVERSECHANNEL);
    RightLiftSolenoid = new DoubleSolenoid(RobotMap.RIGHTLIFT_SINGLESOLENOID_FORWARDCHANNEL, RobotMap.RIGHTLIFT_SINGLESOLENOID_REVERSECHANNEL);
  }
  public void extendExtender() {
    LeftLiftSolenoid.set(Value.kForward);
    RightLiftSolenoid.set(Value.kForward);
  }

  public void retractExtender() {
    LeftLiftSolenoid.set(Value.kReverse);
    RightLiftSolenoid.set(Value.kReverse);
  }

  public void disablePneumatics() {
    LeftLiftSolenoid.set(Value.kOff);
    RightLiftSolenoid.set(Value.kOff);
  }

  public static LiftSub getInstance() {
    
    if (instance == null) {
        instance = new LiftSub();
    }
    return instance;
   
  }

  public void setSpeed (double speed) {
    LiftMotor.set(speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   setDefaultCommand(new DisableLift());
  }
}
