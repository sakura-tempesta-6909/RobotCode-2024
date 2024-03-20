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
                repository.arcadeDrive(DriveParameter.Speeds.FastDrive * DriveModel.driveXSpeed, DriveParameter.Speeds.FastDrive * DriveModel.driveThetaSpeed);
                break;
            /** ロボットの速度を中くらいにする */
            case s_midDrive:
                repository.arcadeDrive(DriveParameter.Speeds.MidDrive * DriveModel.driveXSpeed, DriveParameter.Speeds.MidDrive * DriveModel.driveThetaSpeed);
                break;
            /** ロボットの速度を遅くする */
            case s_slowDrive:
                repository.arcadeDrive(DriveParameter.Speeds.SlowDrive * DriveModel.driveXSpeed, DriveParameter.Speeds.SlowDrive * DriveModel.driveThetaSpeed);
                break;
            /** ロボットの速度を0にする */
            case s_stopDrive:
                repository.arcadeDrive(DriveParameter.Speeds.Neutral * DriveModel.driveXSpeed, DriveParameter.Speeds.Neutral * DriveModel.driveThetaSpeed);
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