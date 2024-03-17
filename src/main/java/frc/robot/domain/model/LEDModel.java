package frc.robot.domain.model;

public class LEDModel {

    /** LEDの点滅のパターン */
    public enum LEDFlashes {
        /** ずっとついている */
        AlwaysOn,
        /** １秒ごとに点滅を繰り返す */
        BlinkingPerSec,
        /** ずっと消えている */
        AlwaysOff;
        
    }

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





