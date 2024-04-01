package frc.robot.components.led.infrastructure;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        timer.start();
        LEDParameter.ConstInit();
    }

    @Override
    public void readSensors() {
        SmartDashboard.putNumber("Timer", timer.get());
    }

    @Override
    public void changeLight(int red, int green, int blue) {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, red, green, blue);
        }
        led.setData(ledBuffer);
    }

    @Override
    public void flashLight(int red, int green, int blue) {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            if (timer.get() < 0.1) {
                ledBuffer.setRGB(i, red, green, blue);
            } else if (timer.get() < 0.2) {
                ledBuffer.setRGB(i, 0, 0, 0);
            } else {
                led.start();
                timer.restart();
            }
        }
        led.setData(ledBuffer);
    }
     @Override
    public void rainbow(int h, int s, int v) {
         for (var i = 0; i < ledBuffer.getLength(); i++) {
             // Calculate the hue - hue is easier for rainbows because the color
             // shape is a circle so only one value needs to precess
             LEDMeasuredState.Hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
             // Set the value
             ledBuffer.setHSV(i, LEDMeasuredState.Hue, s, v);
         }
         // Increase by to make the rainbow "move"
         rainbowFirstPixelHue += 3;
         // Check bounds
         rainbowFirstPixelHue %= 180;
     }
}