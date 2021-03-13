/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DrivetrainSub;
//import frc.robot.subsystems.IntakeSub;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.libs.XboxController;
import frc.robot.commands.Shooter;
import frc.robot.commands.ShooterAdjuster;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeBelt;
import frc.robot.commands.BallElevator;
import frc.robot.commands.DisableIntake;
import frc.robot.commands.DisableShooter;
//import frc.robot.commands.ColorWheelPneumatics;
//import frc.robot.commands.ColorWheel;
//import frc.robot.commands.DisableColorWheel;
//import frc.robot.Robot;
//import frc.robot.commands.GetShooterHeight;

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
  //public boolean ColorWheelStatus = false;
  //public boolean ColorWheelPneumaticsStatus = false;
    public OI() {
        driver = new XboxController(0);
        operator = new XboxController(1);

    operator.A.whenPressed(new Shooter());
    operator.X.whenPressed(new DisableShooter());
    operator.X.whenPressed(new DisableIntake());
   // driver.B.whenPressed(new GetShooterHeight());
    //operator.A.whenPressed(new ColorWheelPneumatics(true));
    //operator.Y.whenPressed(new ColorWheelPneumatics(false));
    
    /*  
    if (ColorWheelPneumaticsStatus == false) {
        operator.A.whenPressed(new ColorWheelPneumatics(true));
    }  else if (ColorWheelPneumaticsStatus == true) {
        operator.A.whenPressed(new ColorWheelPneumatics(false));
    }
*/
  /*  if (ColorWheelStatus == false) {
        operator.X.toggleWhenPressed(new ColorWheel());
        ColorWheelStatus = true;
    }  else if (ColorWheelStatus == true) {
        operator.X.toggleWhenPressed(new DisableColorWheel());
        ColorWheelStatus = false;
    }
*/

        new Trigger() {
          public boolean get() {
            if (Robot.getInstance() == null)
              return false;
            return (driver.RT.get() != 0 || driver.LT.get() != 0);
          }
        }.whileActive(new ShooterAdjuster());

        new Trigger() {
            public boolean get() {
              if (Robot.getInstance() == null)
                return false;
              return (operator.RS.Y.get() != 0);
            }
          }.whileActive(new BallElevator());


        new Trigger() {
            public boolean get() {
              if (Robot.getInstance() == null)
                return false;
              return (operator.LS.Y.get() != 0);
            }
          }.whileActive(new IntakeBelt());


        new Trigger() {
            public boolean get() {
              if (Robot.getInstance() == null)
                return false;
              return (operator.RT.get() != 0 || operator.LT.get() != 0);
            }
          }.whileActive(new Intake());

        


        // Back button zeroes the drivetrain
        new JoystickButton(primaryJoystick, 7).whenPressed(
                new InstantCommand(() -> DrivetrainSub.getInstance().resetGyroscope())
        );
    }






    public Joystick getPrimaryJoystick() {
        return primaryJoystick;
    }
}
