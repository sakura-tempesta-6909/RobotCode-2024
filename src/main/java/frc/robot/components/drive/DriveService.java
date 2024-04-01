package frc.robot.components.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.Service;
import frc.robot.domain.measure.DriveMeasuredState;
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
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.FastDrive;
                DriveModel.driveFowardSpeed *= DriveParameter.Speeds.FastDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.FastThetaDrive;
                break;

            /** ロボットの速度を中くらいにする */
            case s_midDrive:
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.MidDrive;
                DriveModel.driveFowardSpeed *= DriveParameter.Speeds.MidDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.MidThetaDrive;
                break;

            /** ロボットの速度を遅くする */
            case s_slowDrive:
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.SlowDrive;
                DriveModel.driveFowardSpeed *= DriveParameter.Speeds.SlowDrive;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.SlowThetaDrive;
                break;

            /** ロボットの速度を0にする */
            case s_stopDrive:
                DriveModel.driveSideSpeed *= DriveParameter.Speeds.Neutral;
                DriveModel.driveFowardSpeed *= DriveParameter.Speeds.Neutral;
                DriveModel.driveThetaSpeed *= DriveParameter.Speeds.Neutral;
                break;
        }

        if(DriveModel.driveAngle){
            DriveModel.driveThetaSpeed = repository.setAngle(DriveModel.setAngle);
        }

        switch (DriveModel.driveOriented) {
            /** Field Oriented でまっすぐ前に進む */
            case s_fieldOriented:
                repository.fieldOriented(DriveModel.driveSideSpeed, DriveModel.driveFowardSpeed, DriveModel.driveThetaSpeed);
                break;
            /** Robot Oriented で動く */
            case s_robotOriented:
                repository.robotOriented(DriveModel.driveSideSpeed, DriveModel.driveFowardSpeed, DriveModel.driveThetaSpeed);
                break;
        }

        if(DriveModel.resetGyroSensor) {
            repository.resetGyroSensor();
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