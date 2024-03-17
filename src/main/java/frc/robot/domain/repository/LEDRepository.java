package frc.robot.domain.repository;

import frc.robot.domain.model.LEDModel.LEDFlashes;

public interface LEDRepository {
    
    /**
     * LEDを変えたい色に変える
     *
     * @param LEDRGBSequence    LEDの色の配列
     * @param patterntheLED  LEDの点滅の仕方
     * 
     * この状態の場合はこの色という情報はchangeModelでやる
     */
    void changeLight(int[] LEDRGBSequence, LEDFlashes pattern);

    /**
     * センサーを読む
     */
    void readSensors();
}
