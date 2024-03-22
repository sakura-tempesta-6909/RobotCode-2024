package frc.robot.components.led.infrastructure;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.components.led.LEDConst;
import frc.robot.components.led.LEDParameter;
import frc.robot.domain.measure.LEDMeasuredState;
import frc.robot.domain.model.LEDModel.LEDFlashes;
import frc.robot.domain.repository.LEDRepository;

public class LED implements LEDRepository {
    final AddressableLED led;
    final AddressableLEDBuffer ledBuffer;
    final Timer timer;
    int rainbowFirstPixelHue;
    public LED() {
        led = new AddressableLED(LEDConst.Ports.LED);
        ledBuffer = new AddressableLEDBuffer(LEDConst.Ports.LEDBuffer);
        led.setLength(ledBuffer.getLength());
        led.setData(ledBuffer);
        led.start();
        timer = new Timer();
        LEDParameter.ConstInit();
    }
    @Override
    public void readSensors() {

    }
    @Override
    public void changeLight() {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            LEDMeasuredState.Hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
            ledBuffer.setHSV(i, LEDMeasuredState.Hue, 128, 128);
        }
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
        led.setData(ledBuffer);
    }
    @Override
    public void flashLight() {
        if (timer.get() < 1) {
            ledBuffer.setRGB(1, 255, 120, 60);
        } else if (timer.get() < 2) {
            ledBuffer.setRGB(1, 0 , 0, 0);
        } else {
            timer.restart();
        }
    }
}
