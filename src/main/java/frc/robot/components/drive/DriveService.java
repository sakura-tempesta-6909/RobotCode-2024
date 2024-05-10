package frc.robot.components.drive;

import frc.robot.components.Service;
import frc.robot.components.drive.infrastructure.SwerveSubsystem;
import frc.robot.domain.measure.DriveMeasuredState;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.repository.DriveRepository;

public class DriveService implements Service {
    DriveRepository repository;

    public DriveService(DriveRepository repository){
        this.repository = repository;
    }

    @Override
    public void applyModel(){
        if(DriveModel.resetForAutonomous) {
            repository.resetForAutonomous();
        }
        switch (DriveModel.driveMovement) {
            /** ロボットの速度を速くする */
            case s_fastDrive:
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.FastDrive;
                DriveModel.driveForwardSpeed *= DriveParameter.Speeds.FastDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.FastThetaDrive;
                break;

            /** ロボットの速度を中くらいにする */
            case s_midDrive:
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.MidDrive;
                DriveModel.driveForwardSpeed *= DriveParameter.Speeds.MidDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.MidThetaDrive;
                break;

            /** ロボットの速度を遅くする */
            case s_slowDrive:
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.SlowDrive;
                DriveModel.driveForwardSpeed *= DriveParameter.Speeds.SlowDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.SlowThetaDrive;
                break;

            /** ロボットの速度を0にする */
            case s_stopDrive:
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.Neutral;
                DriveModel.driveForwardSpeed *= DriveParameter.Speeds.Neutral;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.Neutral;
                break;
        }

        if(DriveModel.driveAngle){
            DriveModel.driveThetaSpeed = repository.setAngle(DriveModel.setAngle);
        }

        switch (DriveModel.driveOriented) {
            /** Field Oriented でまっすぐ前に進む */
            case s_fieldOriented:
                repository.fieldOriented(DriveModel.driveSideSpeed, DriveModel.driveForwardSpeed, DriveModel.driveThetaSpeed);
                break;
            /** Robot Oriented で動く */
            case s_robotOriented:
                repository.robotOriented(DriveModel.driveSideSpeed, DriveModel.driveForwardSpeed, DriveModel.driveThetaSpeed);
                break;
            case s_autonomous:
                repository.autonomousDrive();
                break;
        }

        if(DriveModel.resetGyroSensor) {
            repository.resetGyroSensor();
        }

        if(DriveModel.rewriteGyroSensorOrNot){
           repository.rewriteGyroSensor(DriveModel.offset);
        }

        if(DriveModel.resetGyroSensor) {
            repository.resetGyroSensor();
        }

        if(DriveModel.rewriteGyroSensorOrNot){
           repository.rewriteGyroSensor(DriveModel.offset);
        }
    }
    
    @Override
    public void readSensors(){
        repository.readSensors();
    }

    @Override
    public void resetModel(){
        DriveModel.reset();
    }
}