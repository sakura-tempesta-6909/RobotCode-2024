package frc.robot.domain.repository;


public interface LEDRepository {
    public enum LEDFlashes {
        //LEDの点滅のパターン
        
        //ずっとついている
        AlwaysOn,

        //１秒ごとに点滅を繰り返す
        BlinkingPerSec,

        //ずっと消えている
        AlwaysOff;
        
    }
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
