package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;



public class arm extends SubsystemBase {
    
    TalonSRX extension1;
    TalonSRX extension2;
    Solenoid elevatorLift;
    public double e1DesiredPos;
    public double e2DesiredPos;
    
    //creates a new arm
    public arm() {
        extension1 = new TalonSRX(Constants.firstStageMotor);
        extension2 = new TalonSRX(Constants.secondStageMotor);
        elevatorLift = new Solenoid(PneumaticsModuleType.REVPH, Constants.armSolenoid);
        extension1.setNeutralMode(NeutralMode.Brake);
        extension2.setNeutralMode(NeutralMode.Brake);

      }

      public void periodic(){//TODO: add proper hexshaft encoder integration
        SmartDashboard.putNumber("Extension 1 Location", 0);
        SmartDashboard.putNumber("Extension 2 Location", 0);
        SmartDashboard.putBoolean("Arm Up/Down", elevatorLift.get());
      }
      //Arm Raise/lower commands call this depending on scoring and whatever
      public void armDown(){
        elevatorLift.set(false);
        SmartDashboard.putString("armPos","ArmDOWN");
      }
      public void armUp(){
        elevatorLift.set(true);
        SmartDashboard.putString("armPos","ArmUP");
      }
      
      //Motor Positions

      public void SetPosition(double extension1pos, double extension2pos){
       e1DesiredPos = extension1pos;
       e2DesiredPos = extension2pos;
        
      }

      public void ArmPID(){
        //extension1.set(extensionPID.calculate);
         // extension1.set//TODO:PICKUP WHERE I LEFT OFF

      }

    

}