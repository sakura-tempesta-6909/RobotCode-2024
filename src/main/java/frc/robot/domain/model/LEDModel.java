package frc.robot.domain.model;

import frc.robot.domain.repository.LEDRepository.LEDFlashes;

public class LEDModel {
    public static LEDMode ledMode;

    public enum LEDMode {
        /** LEDを変えたいように変える*/
        s_changeLED;    
    }
    
    public static int[] sequence = new int[] {2, 3, 4};
    public static LEDFlashes pattern = LEDFlashes.AlwaysOff;

    public static void reset() {
        
    }

    public LEDModel() {
        reset();
    }

}





