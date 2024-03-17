package frc.robot.components.shooter;

import frc.robot.components.Service;
import frc.robot.components.link.LinkParameter;
import frc.robot.domain.model.LinkModel;
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
            case s_intake:
                repository.noteIntake();
                break;
            case s_shootSpeaker:
                repository.noteShootSpeaker();
                break;
            case s_shootAmp:
                repository.noteShootAmp();
                break;
            case s_outtake:
                repository.noteOuttake();
                break;
            case s_stopIntake:
                repository.stopIntake();
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