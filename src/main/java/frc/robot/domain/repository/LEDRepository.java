package frc.robot.domain.repository;

import edu.wpi.first.wpilibj.util.Color;

public interface LEDRepository {
    
    /**
     * LEDを変えたい色に変える
     * この状態の場合はこの色という情報はchangeModelでやる
     */
    void changeLight(int red, int green, int blue);

    void flashLight(int red, int green, int blue);

    void rainbow(int h, int s, int v);
    void increaseBrightness();
    void wave(Color c1, Color c2, double cycleLength, double duration);
    /**
     * センサーを読む
     */
    void readSensors();
}
