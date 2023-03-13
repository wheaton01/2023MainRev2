package frc.robot;

import java.sql.Driver;
import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AutoConstants;
import frc.robot.autos.exampleAuto;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.ArmCommand.holdPos;
import frc.robot.commands.ArmCommand.toHome;
import frc.robot.commands.ArmCommand.toHumanFeed;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.arm;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final XboxController driver = new XboxController(0);
    private final XboxController Operator = new XboxController(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    /* Operator Buttons */
    private final  JoystickButton opHumanFeed = new JoystickButton(driver, XboxController.Button.kX.value);
    private final  JoystickButton opHome = new JoystickButton(driver, XboxController.Button.kA.value);


    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();

    private final arm vArm = new arm();


//OtherDevices



    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );


        vArm.setDefaultCommand(new holdPos(vArm));
        try ( PneumaticHub m_ph = new PneumaticHub(30)) {}
   // Configure the button bindings
        configureButtonBindings();
    }
    

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        //Operator Controlls
        opHumanFeed.whileTrue(new toHome(vArm));        
        opHome.whileTrue(new toHumanFeed(vArm));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);
                Trajectory AutonTest1 = TrajectoryGenerator.generateTrajectory(new Pose2d(0,0, new Rotation2d(0)),
                List.of(new Translation2d(1,0),
                        new Translation2d(2,1),//Points for auton to follow
                        new Translation2d(3,0),
                        new Translation2d(0,1)
                
                
                ),
                 new Pose2d(0,0, Rotation2d.fromDegrees(180)),
                config);
            PIDController xController = new PIDController(AutoConstants.kPXController, 0, 0);
            PIDController yController = new PIDController(AutoConstants.kPXController, 0, 0);;
            ProfiledPIDController thetaController = new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(AutonTest1,
                                                                s_Swerve::getPose,
                                                                Constants.Swerve.swerveKinematics,
                                                                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                                                                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                                                                thetaController,
                                                                s_Swerve::setModuleStates,
                                                                s_Swerve);
        // An ExampleCommand will run in autonomous
        return new SequentialCommandGroup(
                new InstantCommand(() -> s_Swerve.resetOdometry(AutonTest1.getInitialPose())),
                swerveControllerCommand,
                new InstantCommand(() -> s_Swerve.stopModules()));
    }
}
