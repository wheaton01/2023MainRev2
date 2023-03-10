// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommand;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm;



public class toHumanFeed extends CommandBase {
  /** Creates a new HumanFeed. */

  arm vArm;

  public toHumanFeed(arm param_Arm) {
    // Use addRequirements() here to declare subsystem dependencies.
    vArm = param_Arm;
    addRequirements(vArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    vArm.armUp();
    vArm.intakeON(155);
    vArm.SetPosition(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
