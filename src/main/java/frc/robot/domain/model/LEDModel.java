package frc.robot.domain.model;

public class LEDModel {
    public static LEDMode ledMode;

    public enum LEDMode {
        /** LEDを変えたいように変える*/
        s_changeLED;    

    }
 
    public static void reset() {
        
    }

    public LEDModel() {
        reset();
    }

}





