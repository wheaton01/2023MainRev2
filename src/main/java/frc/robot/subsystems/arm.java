package frc.robot.subsystems;

import frc.robot.SwerveModule;
import frc.robot.Constants;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;



public class arm extends SubsystemBase {
    
    TalonSRX extension1;
    TalonSRX extension2;
    Solenoid elevatorLift;


    //creates a new arm
    public arm() {
        extension1 = new TalonSRX(25);
        extension2 = new TalonSRX(26);
        elevatorLift = new Solenoid(PneumaticsModuleType.REVPH, 0);
        extension1.setNeutralMode(NeutralMode.Brake);
        extension2.setNeutralMode(NeutralMode.Brake);
      }

      public void periodic(){//TODO: add proper hexshaft encoder integration
        SmartDashboard.putNumber("Extension 1 Location", 0);
        SmartDashboard.putNumber("Extension 2 Location", 0);
        SmartDashboard.putBoolean("Arm Up/Down", elevatorLift.get());
      }

      public void humanFeed(){
        elevatorLift.set(true);

      }
    

}