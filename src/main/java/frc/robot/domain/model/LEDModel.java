package frc.robot.domain.model;

import frc.robot.domain.repository.LEDRepository.LEDFlashes;

public class LEDModel {

    /**
     * sequenceはLEDの点滅の色を表しているintの配列
     * patternはLEDの点滅の仕方を表すenum型です
     */
    public static int[] sequence;
    public static LEDFlashes pattern;

    public static void reset() {
        
    }

    public LEDModel() {
        reset();
    }

}





