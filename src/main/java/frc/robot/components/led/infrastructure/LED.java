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
            ledBuffer.setRGB(i, 255, 125, 255);
        }
    }
    @Override
    public void flashLight() {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            if (timer.get() < 1) {
                ledBuffer.setRGB(i, 255, 120, 60);
            } else if (timer.get() < 2) {
                led.stop();
            } else {
                timer.restart();
            }
        }
    }
}
