package frc.robot.domain.model;

public class LEDModel {
    /** LEDの点滅のパターン */
    public enum LEDFlashes {
        /** ずっとついている */
        AlwaysOn,
        /** Noteを持っているか */
        NOTEGet,
        /** ロボットの高さが720mm以下か　*/
        Under720mm,
        /** ずっと消えている */
        AlwaysOff;
        
    }

    /** LEDの点滅の色を表しているintの配列 */
    public static int[] sequence;
    /** patternはLEDの点滅の仕方を表すenum型です */
    public static LEDFlashes pattern;

    public static void reset() {
        sequence = new int[]{0, 0, 0};
        pattern = LEDFlashes.AlwaysOn;
    }

    public LEDModel() {
        reset();
    }

}





