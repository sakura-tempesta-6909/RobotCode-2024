package frc.robot.components.drive.infrastructure;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.components.drive.DriveConst.DriveConstants;
import frc.robot.components.drive.DriveConst.ModuleConstants;
import frc.robot.components.drive.DriveParameter;
import frc.robot.domain.repository.DriveRepository;

public class Drive implements DriveRepository {
    SwerveSubsystem driveSubsystem;

    public Drive(){
        driveSubsystem = new SwerveSubsystem();
    }

    @Override
    public void robotOriented(double sideSpeed, double fowardSpeed, double thetaSpeed) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(fowardSpeed, -sideSpeed, thetaSpeed);
        
        //5. Convert chassis speeds to individual module states [14:37]
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        
        //6. Output each module states to wheels[14:49]
        driveSubsystem.setModuleStates(moduleStates);

        
    }

    @Override
    public void fieldOriented(double sideSpeed, double fowardSpeed, double thetaSpeed) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            fowardSpeed, -sideSpeed, thetaSpeed, driveSubsystem.getRotation2d());
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        driveSubsystem.setModuleStates(moduleStates);

    }

    @Override
    public void readSensors() {
        
    }
}
