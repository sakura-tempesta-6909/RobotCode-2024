package frc.robot.components.led.infrastructure;

import frc.robot.components.led.LEDParameter;
import frc.robot.domain.repository.LEDRepository;

public class LED implements LEDRepository {
    public LED() {
        LEDParameter.ConstInit();
    }
    @Override
    public void changeLight(int[] LEDRGBSequence, LEDFlashes pattern) {

    }

    @Override
    public void readSensors() {

    }
}
