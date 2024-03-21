package frc.robot.components.led;

import frc.robot.components.Service;
import frc.robot.components.drive.DriveParameter;
import frc.robot.components.led.infrastructure.LED;
import frc.robot.domain.measure.LEDMeasuredState;
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
        repository.changeLight(LEDModel.sequence, LEDModel.pattern);
        switch (LEDModel.pattern) {
            case AlwaysOn:
                LEDModel.sequence = new int[]{128, 128};
                break;
            case BlinkingPerSec:
                LEDModel.sequence = new int[]{255, 120};
                break;
            case AlwaysOff:
                LEDModel.sequence = new int[]{0, 0};
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
