package frc.robot.domain.repository;

import frc.robot.domain.model.LEDModel.LEDFlashes;

public interface LEDRepository {

    /**
     * LEDを変えたい色に変える
     * LEDを点滅させる
     * この状態の場合はこの色という情報はchangeModelでやる
     * @param red
     * @param green
     * @param blue
     */
    void changeLight(int red, int green, int blue);

    /**
     * LEDを変えたい色に変える
     * LEDを点灯させる
     * この状態の場合はこの色という情報はchangeModelでやる
     * @param red
     * @param green
     * @param blue
     */

    void flashLight(int red, int green, int blue);

    /**
     * センサーを読む
     */
    void readSensors();
}
