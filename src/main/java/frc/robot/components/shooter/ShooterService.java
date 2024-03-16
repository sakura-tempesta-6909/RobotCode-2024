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
