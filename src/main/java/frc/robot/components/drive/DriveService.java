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
            /*case s_fastDrive:
                repository.
                break;*/
            /** ロボットの速度を中くらいにする */
            /*case s_midDrive:
                repository. 
                break;*/
            /** ロボットの速度を遅くする */
            /*case s_slowDrive:
                repository.
                break;*/
            /** ロボットの速度を0にする */
            /*case s_stopDrive:
                repository.
                break;*/
            /** ロボットの回転を右回転にする */
            /*case s_rightTurn:
                repository.
                break;*/
            /** ロボットの回転を左回転にする */
            /*case s_leftTurn:
                repository.
                break;*/
            /** ロボットの方向をフィールドに対して前を向く */
            /*case s_fowardTurn:
                repository.
                break;*/
        }
        switch (DriveModel.driveOrientedY) {
            /** Field Oriented でまっすぐ前に進む */
            case s_fieldOrientedY:
                repository.robotOriented(0, 0, 0);;
                break;
            /** Robot Oriented で動く */
            case s_robotOrientedY:
                repository.fieldOriented(0, 0, 0);
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