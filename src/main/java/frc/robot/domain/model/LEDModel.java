package frc.robot.domain.model;

import frc.robot.domain.repository.LEDRepository.LEDFlashes;

public class LEDModel {

    /** LEDの点滅の色を表しているintの配列 */
    public static int[] sequence;
    /** patternはLEDの点滅の仕方を表すenum型です */
    public static LEDFlashes pattern;

    public static void reset() {
        
    }

    public LEDModel() {
        reset();
    }

}





