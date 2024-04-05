package frc.robot.components.drive.infrastructure;

import java.util.List;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.components.drive.DriveConst.AutoConstants;
import frc.robot.components.drive.DriveConst.DriveConstants;
import frc.robot.components.drive.DriveConst;
import frc.robot.components.drive.DriveParameter;
import frc.robot.domain.measure.DriveMeasuredState;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.repository.DriveRepository;

public class Drive implements DriveRepository {
    SwerveSubsystem driveSubsystem;
    PIDController pid;
    TrajectoryConfig trajectoryConfig;
    Trajectory trajectory;
    PIDController xController, yController;
    ProfiledPIDController thetaController;
    HolonomicDriveController holonomicDriveController;
    SwerveControllerCommand SwerveControllerCommand;

    public Drive(){
        driveSubsystem = new SwerveSubsystem();
        // Creates a PIDController with gains kP, kI, and kD
        pid = new PIDController(DriveParameter.Speeds.kP, DriveParameter.Speeds.kI, DriveParameter.Speeds.kD);
        // Enables continuous input on a range from -180 to 180
        pid.enableContinuousInput(-180, 180);

        //1. Create trajectory settings #6 [20:46]
        trajectoryConfig = new TrajectoryConfig(
            DriveConst.AutoConstants.kMaxSpeedMetersPerSecond,
            DriveConst.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                    .setKinematics(DriveConstants.kDriveKinematics);

        //2. Generate trajectory[21:13]
        trajectory = TrajectoryGenerator.generateTrajectory(
            new Pose2d(0,0, new Rotation2d(0)), //starting pose //最初は0度
            List.of(
                new Translation2d(1, 0), //interior waypoints
                new Translation2d(1, -1)
            ),
            new Pose2d(2, -1, Rotation2d.fromDegrees(180)), //ending pose //最後は180度→終了までに180度回転するように指示している
            trajectoryConfig
        );

        //3. Define PID controllers for tracking trajectory
        xController = new PIDController(DriveConst.AutoConstants.kPXController, 0, 0);
        yController = new PIDController(DriveConst.AutoConstants.kPYController, 0, 0);
        thetaController = new ProfiledPIDController(
            DriveConst.AutoConstants.kPThetaController, 0, 0, DriveConst.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        holonomicDriveController = new HolonomicDriveController(xController, yController, thetaController);
        
        //4. Construct command to follow trajectory
        /*SwerveControllerCommand = new SwerveControllerCommand(
            trajectory, 
            SwerveSubsystem::getPose, 
            DriveConst.DriveConstants.kDriveKinematics, 
            xController, 
            yController, 
            thetaController,
            SwerveSubsystem::setModuleStates,
            SwerveSubsystem);*/
    }

    @Override
    public void robotOriented(double sideSpeed, double forwardSpeed, double thetaSpeed) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(forwardSpeed, -sideSpeed, thetaSpeed);
        //5. Convert chassis speeds to individual module states [14:37]
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        //6. Output each module states to wheels[14:49]
        driveSubsystem.setModuleStates(moduleStates);        
    }

    @Override
    public void fieldOriented(double sideSpeed, double forwardSpeed, double thetaSpeed) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            forwardSpeed, -sideSpeed, thetaSpeed, driveSubsystem.getRotation2d());
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        driveSubsystem.setModuleStates(moduleStates);
    }

    @Override
    public void setPosition(Pose2d setPositionPose2d, Rotation2d setAngleRotation2d, double time) {
        holonomicDriveController.calculate(DriveMeasuredState.drivePosition, trajectory.sample(time), setAngleRotation2d);
        //5. add some init and wrap-up, and return everything
        /*return new SequentialCommandGroup(
            new InstantCommand(() -> driveSubsystem.resetOdometry(trajectory.getInitialPose())),
            SwerveControllerCommand,
            new InstantCommand(() -> driveSubsystem.stopModules())
        );*/
    }

    @Override
    public double setAngle(double setPoint) {
        // Calculates the output of the PID algorithm based on the sensor reading
        double thetaSpeedToSetAngle = pid.calculate(DriveMeasuredState.currentAngle, setPoint);
        return thetaSpeedToSetAngle;
    }

    @Override
    public void resetGyroSensor() {
        driveSubsystem.zeroHeading();
    }

    @Override
    public void rewriteGyroSensor(){
        if(DriveModel.rewriteGyroSensorOrNot)DriveMeasuredState.currentAngle = DriveModel.rewriteGyroSensor;
    }

    @Override
    public void readSensors() {
        SmartDashboard.putNumber("Robot Heading", driveSubsystem.getHeading());
        driveSubsystem.periodic();

        DriveMeasuredState.currentAngle = driveSubsystem.getHeading();
    }
}
