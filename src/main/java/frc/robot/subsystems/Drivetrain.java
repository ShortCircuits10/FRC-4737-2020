/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.frcteam2910.common.drivers.Gyroscope;
import org.frcteam2910.common.drivers.SwerveModule;
import org.frcteam2910.common.math.Vector2;
import org.frcteam2910.common.robot.drivers.NavX;
import frc.robot.Mk2SwerveModuleBuilder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.SwerveDrive;

/**
 * An example subsystem. You can replace with me with your own subsystem.
 */
public class Drivetrain extends Subsystem {
    private static final double TRACKWIDTH = 19.5;
    private static final double WHEELBASE = 23.5;
   // public static final double SpeedController = 1;

    private static final double FRONT_LEFT_ANGLE_OFFSET = -Math.toRadians(0.0);
    private static final double FRONT_RIGHT_ANGLE_OFFSET = -Math.toRadians(0.0);
    private static final double BACK_LEFT_ANGLE_OFFSET = -Math.toRadians(0.0);
    private static final double BACK_RIGHT_ANGLE_OFFSET = -Math.toRadians(0.0);

  //  private WPI_TalonSRX Talon = new WPI_TalonSRX(1);
    private static Drivetrain instance;

    private final SwerveModule frontLeftModule = new Mk2SwerveModuleBuilder(
            new Vector2(TRACKWIDTH / 2.0, WHEELBASE / 2.0))
                    .angleEncoder(new AnalogInput(0), FRONT_LEFT_ANGLE_OFFSET)
                    .angleMotor( new WPI_TalonSRX(2), Mk2SwerveModuleBuilder.MotorType.MINI_CIM)
                    .driveMotor( new WPI_TalonSRX(1), Mk2SwerveModuleBuilder.MotorType.FALCON_500)
                    .build();

    private final SwerveModule frontRightModule = new Mk2SwerveModuleBuilder(
            new Vector2(TRACKWIDTH / 2.0, -WHEELBASE / 2.0))
                    .angleEncoder(new AnalogInput(0),  FRONT_RIGHT_ANGLE_OFFSET)
                    .angleMotor( new WPI_TalonSRX(4), Mk2SwerveModuleBuilder.MotorType.MINI_CIM)
                    .driveMotor( new WPI_TalonSRX(3), Mk2SwerveModuleBuilder.MotorType.FALCON_500)
                    .build();
    private final SwerveModule backLeftModule = new Mk2SwerveModuleBuilder(
            new Vector2(-TRACKWIDTH / 2.0, WHEELBASE / 2.0))
                    .angleEncoder(new AnalogInput(RobotMap.DRIVETRAIN_BACK_LEFT_ANGLE_ENCODER), BACK_LEFT_ANGLE_OFFSET)
                    .angleMotor(new WPI_TalonSRX(6), Mk2SwerveModuleBuilder.MotorType.MINI_CIM)
                    .driveMotor( new WPI_TalonSRX(5), Mk2SwerveModuleBuilder.MotorType.FALCON_500)
                    .build();
    private final SwerveModule backRightModule = new Mk2SwerveModuleBuilder(
            new Vector2(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0))
                .angleEncoder(new AnalogInput(RobotMap.DRIVETRAIN_BACK_RIGHT_ANGLE_ENCODER), BACK_RIGHT_ANGLE_OFFSET)
                .angleMotor( new WPI_TalonSRX(8), Mk2SwerveModuleBuilder.MotorType.MINI_CIM)
                .driveMotor( new WPI_TalonSRX(7), Mk2SwerveModuleBuilder.MotorType.FALCON_500)
                .build();
    
        private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
                new Translation2d(TRACKWIDTH / 2.0, WHEELBASE / 2.0),
                new Translation2d(TRACKWIDTH / 2.0, -WHEELBASE / 2.0),
                new Translation2d(-TRACKWIDTH / 2.0, WHEELBASE / 2.0),
                new Translation2d(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0)
        );
    
        private final Gyroscope gyroscope = new NavX(SPI.Port.kMXP);
    
        public Drivetrain() {
            gyroscope.calibrate();
            gyroscope.setInverted(true); // You might not need to invert the gyro
    
            frontLeftModule.setName("Front Left");
            frontRightModule.setName("Front Right");
            backLeftModule.setName("Back Left");
            backRightModule.setName("Back Right");
        }
    
        public static Drivetrain getInstance() {
            if (instance == null) {
                instance = new Drivetrain();
            }
    
            return instance;
        }
    
        @Override
        public void periodic() {
            frontLeftModule.updateSensors();
            frontRightModule.updateSensors();
            backLeftModule.updateSensors();
            backRightModule.updateSensors();
    
            SmartDashboard.putNumber("Front Left Module Angle", Math.toDegrees(frontLeftModule.getCurrentAngle()));
            SmartDashboard.putNumber("Front Right Module Angle", Math.toDegrees(frontRightModule.getCurrentAngle()));
            SmartDashboard.putNumber("Back Left Module Angle", Math.toDegrees(backLeftModule.getCurrentAngle()));
            SmartDashboard.putNumber("Back Right Module Angle", Math.toDegrees(backRightModule.getCurrentAngle()));
    
            SmartDashboard.putNumber("Gyroscope Angle", gyroscope.getAngle().toDegrees());
    
            frontLeftModule.updateState(TimedRobot.kDefaultPeriod);
            frontRightModule.updateState(TimedRobot.kDefaultPeriod);
            backLeftModule.updateState(TimedRobot.kDefaultPeriod);
            backRightModule.updateState(TimedRobot.kDefaultPeriod);
        }
    
        public void drive(final Translation2d translation, double rotation, final boolean fieldOriented) {
            rotation *= 2.0 / Math.hypot(WHEELBASE, TRACKWIDTH);
            ChassisSpeeds speeds;
            if (fieldOriented) {
                speeds = ChassisSpeeds.fromFieldRelativeSpeeds(translation.getX(), translation.getY(), rotation,
                        Rotation2d.fromDegrees(gyroscope.getAngle().toDegrees()));
            } else {
                speeds = new ChassisSpeeds(translation.getX(), translation.getY(), rotation);
            }
    
            final SwerveModuleState[] states = kinematics.toSwerveModuleStates(speeds);
            frontLeftModule.setTargetVelocity(states[0].speedMetersPerSecond, states[0].angle.getRadians());
            frontRightModule.setTargetVelocity(states[1].speedMetersPerSecond, states[1].angle.getRadians());
            backLeftModule.setTargetVelocity(states[2].speedMetersPerSecond, states[2].angle.getRadians());
            backRightModule.setTargetVelocity(states[3].speedMetersPerSecond, states[3].angle.getRadians());
        }
    
        public void resetGyroscope() {
            gyroscope.setAdjustmentAngle(gyroscope.getUnadjustedAngle());
        }
    
        @Override
        protected void initDefaultCommand() {
            setDefaultCommand(new SwerveDrive());
        }
    }