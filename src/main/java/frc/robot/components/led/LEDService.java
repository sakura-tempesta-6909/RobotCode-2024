package frc.robot.components.led;

import frc.robot.components.Service;
import frc.robot.components.drive.DriveParameter;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LEDModel;
import frc.robot.domain.repository.LEDRepository;

public class LEDService implements Service {
    LEDRepository repository;

    public LEDService(LEDRepository repository) {
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
        LEDModel.reset();
    }
}
