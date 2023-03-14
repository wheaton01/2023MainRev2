package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSFalconSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.1;


        /*Subsystem Constants */
        public static final int firstStageMotor = 25;
        public static final int secondStageMotor = 26;
        public static final int intakeMotor = 27;//TODO: LIKELY WILL CHANGE
        public static final int armSolenoid = 0;

        /*Extension Position Constants : p stands for position!*/
        public static final int pHumanFeed = 0;
        public static final int pCone = 0;
        public static final int pCube = 0;

        /*PID Configuration For Arm Extension */
        public static final Double extensionP = .001;//TODO: PID TUNING HERE
        public static final Double extensionI = .00;
        public static final Double extensionD = .00;


        public static final class Swerve {
            //ORIGpublic static final int pigeonID = 1;
            public static final boolean invertGyro = true; // Always ensure Gyro is CCW+ CW-
    
            public static final COTSFalconSwerveConstants chosenModule =  //Changed To FIt RObot
                COTSFalconSwerveConstants.SDSMK4(COTSFalconSwerveConstants.driveGearRatios.SDSMK4_L1);
    
            /* Drivetrain Constants */
            public static final double trackWidth = Units.inchesToMeters(21.65); //TODO: CHANGED This must be tuned to specific robot
            public static final double wheelBase = Units.inchesToMeters(21.65); //TODO: CHANGED This must be tuned to specific robot
            public static final double wheelCircumference = chosenModule.wheelCircumference;
            /* Swerve Kinematics 
             * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
             public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
                new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
                new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
                new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
                new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));
    
            /* Module Gear Ratios */
            public static final double driveGearRatio = chosenModule.driveGearRatio;
            public static final double angleGearRatio = chosenModule.angleGearRatio;
    
            /* Motor Inverts */
            public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
            public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;
    
            /* Angle Encoder Invert */
            public static final boolean canCoderInvert = chosenModule.canCoderInvert;
    
            /* Swerve Current Limiting */
            public static final int angleContinuousCurrentLimit = 25;
            public static final int anglePeakCurrentLimit = 40;
            public static final double anglePeakCurrentDuration = 0.1;
            public static final boolean angleEnableCurrentLimit = true;
    
            public static final int driveContinuousCurrentLimit = 35;
            public static final int drivePeakCurrentLimit = 60;
            public static final double drivePeakCurrentDuration = 0.1;
            public static final boolean driveEnableCurrentLimit = true;
    
            /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
             * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
            public static final double openLoopRamp = 0.25;
            public static final double closedLoopRamp = 0.0;
    
            /* Angle Motor PID Values */
            public static final double angleKP = chosenModule.angleKP;
            public static final double angleKI = chosenModule.angleKI;
            public static final double angleKD = chosenModule.angleKD;
            public static final double angleKF = chosenModule.angleKF;
    
            /* Drive Motor PID Values */
            public static final double driveKP = 0.05; //TODO: TEMP OK This must be tuned to specific robot
            public static final double driveKI = 0.0;
            public static final double driveKD = 0.0;
            public static final double driveKF = 0.0;
    
            /* Drive Motor Characterization Values 
             * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
            public static final double driveKS = (0.32 / 12); //TODO: TEMP OK This must be tuned to specific robot
            public static final double driveKV = (1.51 / 12);
            public static final double driveKA = (0.27 / 12);
    
            /* Swerve Profiling Values */
            /** Meters per Second */
            public static final double maxSpeed = 4.5; //TODO: TEMP OK This must be tuned to specific robot
            /** Radians per Second */
            public static final double maxAngularVelocity = 10.0; //TODO: TEMPOK This must be tuned to specific robot
    
            /* Neutral Modes */
            public static final NeutralMode angleNeutralMode = NeutralMode.Coast;
            public static final NeutralMode driveNeutralMode = NeutralMode.Brake;
            public static final int OverallOffset=0;
            /* Module Specific Constants */
            /* Front Left Module - Module 0 */
            public static final class Mod0 { //TODO: CHANGED This must be tuned to specific robot
                public static final int driveMotorID = 5;
                public static final int angleMotorID = 6;
                public static final int canCoderID = 13;
                public static final Rotation2d angleOffset = Rotation2d.fromDegrees(270.0-2.19);
                public static final SwerveModuleConstants constants = 
                    new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
            }
    
            /* Front Right Module - Module 1 */
            public static final class Mod1 { //TODO: CHANGED This must be tuned to specific robot
                public static final int driveMotorID = 3;
                public static final int angleMotorID = 4;
                public static final int canCoderID = 11;
                public static final Rotation2d angleOffset = Rotation2d.fromDegrees(-35.0+2.5405);
                public static final SwerveModuleConstants constants = 
                    new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
            }
            
            /* Back Left Module - Module 2 */
            public static final class Mod2 { //TODO: CHANGED his must be tuned to specific robot
                public static final int driveMotorID = 7;
                public static final int angleMotorID = 8;
                public static final int canCoderID = 15;
                public static final Rotation2d angleOffset = Rotation2d.fromDegrees(-35.0-2.344);
                public static final SwerveModuleConstants constants = 
                    new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
            }
    
            /* Back Right Module - Module 3 */
            public static final class Mod3 { //TODO: CHANGED This must be tuned to specific robot
                public static final int driveMotorID = 1;
                public static final int angleMotorID = 2;
                public static final int canCoderID = 9;
                public static final Rotation2d angleOffset = Rotation2d.fromDegrees(75.0+3.0);
                public static final SwerveModuleConstants constants = 
                    new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
            }
        }
    
        public static final class AutoConstants { //TODO: TEMPOK The below constants are used in the example auto, and must be tuned to specific robot
            public static final double kMaxSpeedMetersPerSecond = 3;
            public static final double kMaxAccelerationMetersPerSecondSquared = 3;
            public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
            public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
        
            public static final double kPXController = .01;
            public static final double kPYController = .01;
            public static final double kPThetaController = .01;
        
            /* Constraint for the motion profilied robot angle controller */
            public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
                new TrapezoidProfile.Constraints(
                    kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
        }
    }
    
