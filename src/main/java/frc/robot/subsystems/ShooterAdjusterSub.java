/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.hal.sim.EncoderSim;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DisableShooterAdjuster;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * Add your docs here.
 */

public class ShooterAdjusterSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static ShooterAdjusterSub instance;
  public WPI_TalonSRX Shooter_Motor3;
  public double currentHeight;



  //Encoder enc;
// public int level = 0;

public ShooterAdjusterSub() {
  Shooter_Motor3 = new WPI_TalonSRX(RobotMap.SHOOTER_MOTOR3);
  Shooter_Motor3.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  Shooter_Motor3.setSensorPhase(false);// Set this to true if we get negatie values
  
 // double currentHeight = Shooter_Motor3.getSensorCollection().getPulseWidthPosition();

}

public void setAdjusterSpeed(double speed) {
  Shooter_Motor3.set(speed);
  }

/*
  public double getHeight() {
    return currentHeight; // To do when the elevator encoder is added
  } */

  public void getHeight(){
      Shooter_Motor3.getSensorCollection().getPulseWidthPosition();
  }




  public static ShooterAdjusterSub getInstance() {
    if (instance == null) {
        instance = new ShooterAdjusterSub();
    }

    return instance;
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DisableShooterAdjuster());
  }


}
