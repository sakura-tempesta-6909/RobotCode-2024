package frc.robot.components.shooter;

import frc.robot.components.Service;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.repository.ShooterRepository;

public class ShooterService implements Service {
    ShooterRepository repository;
    public ShooterService(ShooterRepository repository) {
        this.repository = repository;
    }
    @Override
    public void applyModel() {
        switch (ShooterModel.shooterMode) {
            case s_noteIntake:
                repository.noteIntake(ShooterParameter.Speed.ShooterIntakeSpeed, ShooterParameter.Speed.PusherSpeed);
                break;
            case s_noteOuttake:
                repository.noteOuttake(-ShooterParameter.Speed.ShooterIntakeSpeed, -ShooterParameter.Speed.PusherSpeed);
                break;
            case s_noteShootSpeaker:
                repository.noteShootSpeaker(ShooterParameter.Speed.ShooterTargetSpeed);
                break;
            case s_noteShootAmp:
                repository.noteShootAmp(ShooterParameter.Speed.ShooterIntakeSpeed, -ShooterParameter.Speed.PusherSpeed);
                break;
        }
    }

    @Override
    public void readSensors() {

        repository.readSensors();
    }

    @Override
    public void resetModel() {
        ShooterModel.reset();
    }
}
