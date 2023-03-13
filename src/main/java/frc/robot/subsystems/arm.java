package frc.robot.subsystems;

import javax.xml.namespace.QName;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;



public class arm extends SubsystemBase {
    
    TalonSRX extension1;
    TalonSRX extension2;
    TalonSRX intake;
    Encoder e1Encoder;
    Encoder e2Encoder; 
    Solenoid elevatorLift;
    public double e1DesiredPos;
    public double e2DesiredPos;
    
    //creates a new arm
    public arm() {
        extension1 =   new TalonSRX(Constants.firstStageMotor);
        extension2 =   new TalonSRX(Constants.secondStageMotor);
        intake =       new TalonSRX(Constants.intakeMotor);

        e1Encoder =    new Encoder(0,1 );//TODO: THESE ENCODER PORTS WILL NEED TO CHANGE DEPENDANT ON BUILD
        e2Encoder =    new Encoder(2,3);

        elevatorLift = new Solenoid(PneumaticsModuleType.REVPH, Constants.armSolenoid);

        extension1.setNeutralMode(NeutralMode.Brake);
        extension2.setNeutralMode(NeutralMode.Brake);
        intake.setNeutralMode(NeutralMode.Brake);
       
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
      
      //INTAKE WHEELS
       public void intakeON(int speed){
       // intake.set(2, speed);
      }
       public void intakeOFF(int speed){
              
       }



      //Motor Positions & Controlls

      public void SetPosition(double extension1pos, double extension2pos){
       e1DesiredPos = extension1pos;
       e2DesiredPos = extension2pos;
        
      }



      public void ArmPID(){
        //extension1.set(extensionPID.calculate);
         // extension1.set//TODO:PICKUP WHERE I LEFT OFF

      }

    

}