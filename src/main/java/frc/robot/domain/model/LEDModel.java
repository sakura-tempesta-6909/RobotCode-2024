package frc.robot.domain.model;

import frc.robot.domain.repository.LEDRepository.LEDFlashes;

public class LEDModel {
    public static LEDMode ledMode;

    public enum LEDMode {
        /** LEDを変えたいように変える*/
        s_changeLED;    
    }
    
    public static int[] sequence;
    public static LEDFlashes pattern;

    public static void reset() {
        
    }

    public LEDModel() {
        reset();
    }

}





