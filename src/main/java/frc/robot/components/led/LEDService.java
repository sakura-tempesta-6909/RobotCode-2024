package frc.robot.components.led;

import frc.robot.components.Service;
import frc.robot.domain.model.LEDModel;
import frc.robot.domain.repository.LEDRepository;

public class LEDService implements Service {
    LEDRepository repository;

    public LEDService(LEDRepository repository) {
        this.repository = repository;
    }
    @Override
    public void applyModel() {
        switch (LEDModel.pattern) {
            case AlwaysOn:
                repository.changeLight(255, 0, 255);
                break;
            case NOTEGet:
                repository.flashLight(255, 50, 0);
                break;
            case ShooterSpeed:
                repository.flashLight(0, 255, 0);
                break;
            case UnderStage:
                repository.flashLight(0, 0, 255);
                break;
            case ClimbSuccess:
                repository.flashLight(255, 0, 0);
            case AlwaysOff:
                break;
            default:
                break;
        }
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
