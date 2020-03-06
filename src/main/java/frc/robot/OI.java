/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DrivetrainSub;
import frc.robot.subsystems.IntakeSub;
//import frc.robot.subsystems.ShooterSub;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.libs.XboxController;

import frc.robot.commands.Shooter;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeBelt;
import frc.robot.commands.LiftPneumatics;
//import frc.robot.commands.ReverseIntakeBelt;
import frc.robot.commands.BallElevator;
import frc.robot.commands.ColorWheelPneumatics;
import frc.robot.commands.ColorWheel;
import frc.robot.commands.DisableColorWheel;
//import frc.robot.commands.LiftPneumatics;
import frc.robot.commands.LiftMotorDown;
import frc.robot.commands.LiftMotorUp;
import frc.robot.commands.ReverseShooter;
import frc.robot.commands.DisableShooter;
import frc.robot.commands.DisableIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  private final Joystick primaryJoystick = new Joystick(0);
  public XboxController driver;
  public XboxController operator;
  public boolean ColorWheelStatus = false;
  public boolean ColorWheelPneumaticsStatus = false;
  public boolean LiftStatus = false;
  public boolean ShooterStatus = false;

  public OI() {
    driver = new XboxController(0);
    operator = new XboxController(1);

    //operator.B.whileHeld(new Shooter());
    //operator.A.whileHeld(new ReverseShooter());
    if (ShooterStatus == false) {
      operator.A.whenPressed(new Shooter());

    } /* else if (ShooterStatus == true) {
      operator.B.whenPressed(new DisableShooter());
      ShooterStatus = false;
    }
    */
    /*operator.DPAD.RIGHT.whileHeld(new IntakeBelt());
    operator.DPAD.RIGHT.whenReleased(new DisableIntake());
    operator.DPAD.LEFT.whileActive(new BallElevator());
    operator.DPAD.DOWN.whileHeld(new Intake());
    operator.DPAD.DOWN.whileHeld(new IntakeBelt());
    operator.DPAD.DOWN.whileHeld(new BallElevator());
    operator.DPAD.DOWN.whenReleased(new DisableIntake());
    */
    operator.X.whenPressed(new DisableShooter());
    operator.X.whenPressed(new DisableIntake());
    //operator.DPAD.RIGHT.whileHeld(new Intake());
    //operator.DPAD.RIGHT.whileHeld(new IntakeBelt());
   // operator.DPAD.RIGHT.whileHeld(new BallElevator()); 
    
   // operator.DPAD.LEFT.whileHeld(new ReverseIntakeBelt());
    

    if (LiftStatus == false) {
        operator.Y.whenPressed(new LiftPneumatics(true));
    }  else if (LiftStatus == true) {
        operator.Y.whenPressed(new LiftPneumatics(false));
    } 

    operator.DPAD.DOWN.whenPressed(new LiftMotorDown());
    operator.DPAD.UP.whenPressed(new LiftMotorUp());
    
    if (ColorWheelPneumaticsStatus == false) {
        operator.START.whenPressed(new ColorWheelPneumatics(true));
    }  else if (ColorWheelPneumaticsStatus == true) {
        operator.START.whenPressed(new ColorWheelPneumatics(false));
    }

    if (ColorWheelStatus == false) {
        operator.SELECT.toggleWhenPressed(new ColorWheel());
        ColorWheelStatus = true;
    }  else if (ColorWheelStatus == true) {
        operator.SELECT.toggleWhenPressed(new DisableColorWheel());
        ColorWheelStatus = false;
    }

    new Trigger() {
        public boolean get() {
          if (IntakeSub.getInstance() == null)
            return false;
          return (operator.RT.get() != 0 || operator.LT.get() != 0);
        }
      }.whileActive(new Intake());
          
    new Trigger() {
        public boolean get() {
          if (IntakeSub.getInstance() == null)
            return false;
          return (-operator.LS.Y.get() != 0);
          }
        }.whileActive(new IntakeBelt());

    new Trigger() {
        public boolean get() {
          if (IntakeSub.getInstance() == null)
            return false;
          return (-operator.RS.Y.get() != 0);
          }
        }.whileActive(new BallElevator());


        // Back button zeroes the drivetrain
    new JoystickButton(primaryJoystick, 7).whenPressed(
        new InstantCommand(() -> DrivetrainSub.getInstance().resetGyroscope())
      );
    }

    public Joystick getPrimaryJoystick() {
        return primaryJoystick;
    }
}
