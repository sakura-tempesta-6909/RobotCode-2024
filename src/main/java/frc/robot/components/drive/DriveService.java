package frc.robot.components.drive;

import frc.robot.components.Service;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.repository.DriveRepository;

public class DriveService implements Service {
    DriveRepository repository;

    public DriveService(DriveRepository repository){
        this.repository = repository;
    }

    @Override
    public void applyModel(){
        switch (DriveModel.driveMovement) {
            /** ロボットの速度を速くする */
            case s_fastDrive:
                DriveModel.driveXSpeed *= DriveParameter.Speeds.FastDrive;
                DriveModel.driveYSpeed *= DriveParameter.Speeds.FastDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.FastDrive;
                break;

            /** ロボットの速度を中くらいにする */
            case s_midDrive:
                DriveModel.driveXSpeed *= DriveParameter.Speeds.MidDrive;
                DriveModel.driveYSpeed *= DriveParameter.Speeds.MidDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.MidDrive;
                break;

            /** ロボットの速度を遅くする */
            case s_slowDrive:
                DriveModel.driveXSpeed *= DriveParameter.Speeds.SlowDrive;
                DriveModel.driveYSpeed *= DriveParameter.Speeds.SlowDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.SlowDrive;
                break;

            /** ロボットの速度を0にする */
            case s_stopDrive:
                DriveModel.driveXSpeed *= DriveParameter.Speeds.Neutral;
                DriveModel.driveYSpeed *= DriveParameter.Speeds.Neutral;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.Neutral;
                break;
        }

        switch (DriveModel.driveOriented) {
            /** Field Oriented でまっすぐ前に進む */
            case s_fieldOriented:
                repository.robotOriented(DriveModel.driveXSpeed, DriveModel.driveYSpeed, DriveModel.driveThetaSpeed);;
                break;
            /** Robot Oriented で動く */
            case s_robotOriented:
                repository.fieldOriented(DriveModel.driveXSpeed, DriveModel.driveYSpeed, DriveModel.driveThetaSpeed);
                break;
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