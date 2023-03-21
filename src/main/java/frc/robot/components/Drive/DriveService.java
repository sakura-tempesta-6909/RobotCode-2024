package frc.robot.components.Drive;

import frc.robot.components.Service;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.repository.DriveRepository;

public class DriveService implements Service {
    DriveRepository repository;

    public DriveService(DriveRepository repository) {
        this.repository = repository;
    }

    @Override
    public void applyModel() {
        if(DriveModel.resetStraightPID) {
            repository.resetStraightPIDController();
        }
        if(DriveModel.resetRotationPID) {
            repository.resetRotationPIDController();
        }

        switch (DriveModel.driveBaseMode) {
            case s_fastDrive:
                repository.arcadeDrive(Const.Speeds.FastDrive * DriveModel.driveXSpeed, Const.Speeds.FastDrive * DriveModel.driveZRotation);
                break;
            case s_midDrive:
                repository.arcadeDrive(Const.Speeds.MidDrive * DriveModel.driveXSpeed, Const.Speeds.MidDrive * DriveModel.driveZRotation);
                break;
            case s_slowDrive:
                repository.arcadeDrive(Const.Speeds.SlowDrive * DriveModel.driveXSpeed, Const.Speeds.SlowDrive * DriveModel.driveZRotation);
                break;
            case s_stopDrive:
                repository.arcadeDrive(Const.Speeds.Neutral * DriveModel.driveXSpeed, Const.Speeds.Neutral * DriveModel.driveZRotation);
                break;
            case s_pidStraight:
                repository.straightPID(DriveModel.straightPIDTarget);
                break;
            case s_pidTurn:
                repository.rotationPID(DriveModel.rotationPIDTarget);
                break;
        }
    }

    @Override
    public void readSensors() {
        repository.readSensors();
    }

    @Override
    public void resetModel() {
        DriveModel.reset();
    }
}
