package frc.robot.components.led.infrastructure;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.components.led.LEDConst;
import frc.robot.components.led.LEDParameter;
import frc.robot.domain.measure.LEDMeasuredState;
import frc.robot.domain.model.LEDModel.LEDFlashes;
import frc.robot.domain.repository.LEDRepository;

public class LED implements LEDRepository {
    final AddressableLED led;
    final AddressableLEDBuffer ledBuffer;
    int rainbowFirstPixelHue;
    public LED() {
        led = new AddressableLED(LEDConst.Ports.LED);
        ledBuffer = new AddressableLEDBuffer(LEDConst.Ports.LEDBuffer);
        led.setLength(ledBuffer.getLength());
        led.setData(ledBuffer);
        led.start();
        LEDParameter.ConstInit();
    }
    @Override
    public void readSensors() {

    }
    @Override
    public void changeLight(int[] LEDRGBSequence, LEDFlashes pattern) {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            LEDMeasuredState.Hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
            ledBuffer.setHSV(i, LEDMeasuredState.Hue, LEDRGBSequence[0], LEDRGBSequence[1]);
        }
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
        led.setData(ledBuffer);
    }
}
