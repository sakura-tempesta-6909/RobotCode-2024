package frc.robot.domain.repository;


public interface LEDRepository {
    public enum LEDFlashes {
        //LEDの点滅のパターン
        patterntheLEDFlashes;
    }
    /**
     * LEDを変えたい色に変える
     *
     * @param LEDREGSequence    LEDの色の配列
     * @param patterntheLED  LEDの点滅の仕方
     * 
     * この状態の場合はこの色という情報はchangeModelでやる
     */
    void changeLight(int[] LEDREGSequence, LEDFlashes pattern);

}
