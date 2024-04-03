package frc.robot.components.drive.infrastructure;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.components.drive.DriveConst.DriveConstants;
import frc.robot.components.drive.DriveParameter;
import frc.robot.domain.measure.DriveMeasuredState;
import frc.robot.domain.repository.DriveRepository;

public class Drive implements DriveRepository {
    SwerveSubsystem driveSubsystem;
    PIDController pid;

    public Drive(){
        driveSubsystem = new SwerveSubsystem();
        // Creates a PIDController with gains kP, kI, and kD
        pid = new PIDController(DriveParameter.Speeds.kP, DriveParameter.Speeds.kI, DriveParameter.Speeds.kD);
        // Enables continuous input on a range from -180 to 180
        pid.enableContinuousInput(-180, 180);
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
    public void readSensors() {
        SmartDashboard.putNumber("Robot Heading", driveSubsystem.getHeading());
        driveSubsystem.periodic();

        DriveMeasuredState.currentAngle = driveSubsystem.getHeading();
    }
}
