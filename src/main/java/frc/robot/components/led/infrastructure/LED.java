package frc.robot.components.led.infrastructure;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.components.led.LEDConst;
import frc.robot.components.led.LEDParameter;
import frc.robot.domain.measure.LEDMeasuredState;
import frc.robot.domain.repository.LEDRepository;

public class LED implements LEDRepository {
    final AddressableLED led;
    final AddressableLEDBuffer ledBuffer;
    final Timer timer;
    int rainbowFirstPixelHue;
    int brightnessIncrement = 5; // 明るさを増やす量
    int maxBrightness = 255; // 最大の明るさ
    int currentBrightness = 0;
    double brightnessChangeRate = 0.01; // 明るさの変化率
    double waveExponent = 0.4;
 
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
        // For every pixel
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
        led.setData(ledBuffer);
    }

    @Override
    public void increaseBrightness() {
        for (int i = 0; i < LEDConst.Ports.LEDBuffer; i++) {
            currentBrightness += (int) (maxBrightness * brightnessChangeRate);
            // 現在の明るさが最大値を超える場合、最大値に設定し変化の方向を反転する
            if (currentBrightness >= maxBrightness) {
                currentBrightness = maxBrightness;
                brightnessChangeRate *= -1;
            }
            // 現在の明るさが最小値を下回る場合、最小値に設定し変化の方向を反転する
            if (currentBrightness <= 0) {
                currentBrightness = 0;
                brightnessChangeRate *= -1;
            }
            ledBuffer.setRGB(i, currentBrightness, 0, 0);
        }
            led.setData(ledBuffer);
    }

    public void wave(Color c1, Color c2, double cycleLength, double duration) {
        double x = (1 - ((Timer.getFPGATimestamp() % duration) / duration)) * 2.0 * Math.PI;
        double xDiffPerLed = (2.0 * Math.PI) / cycleLength;
        for (int i = 0; i < LEDConst.Ports.LEDBuffer; i++) {
            x += xDiffPerLed;
            double ratio = (Math.pow(Math.sin(x), waveExponent) + 1.0) / 2.0;
            if (Double.isNaN(ratio)) {
                ratio = (-Math.pow(Math.sin(x + Math.PI), waveExponent) + 1.0) / 2.0;
            }
            if (Double.isNaN(ratio)) {
                ratio = 0.5;
            }
            double red = (c1.red * (1 - ratio)) + (c2.red * ratio);
            double green = (c1.green * (1 - ratio)) + (c2.green * ratio);
            double blue = (c1.blue * (1 - ratio)) + (c2.blue * ratio);
            ledBuffer.setLED(i, new Color(red, green, blue));
        }
    }


}