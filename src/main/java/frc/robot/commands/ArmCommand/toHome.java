// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommand;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm;

public class toHome extends CommandBase {
  /** Creates a new Home. */
  arm vArm;
  public toHome(arm param_Arm) {
    vArm = param_Arm;
    
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(vArm);//DO NOT FORGET THIS WITHOUT THIS YOUR CODE WILL NOT INTERRUPT OTHER COMMANDS
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    vArm.armDown();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
