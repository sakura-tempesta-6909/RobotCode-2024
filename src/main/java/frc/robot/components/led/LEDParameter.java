package frc.robot.components.led;

import frc.robot.domain.repository.LEDRepository.LEDFlashes;

public class LEDParameter {
    public static final int[] sequence = new int[] {2,3,4};
    public static final LEDFlashes pattern = LEDFlashes.AlwaysOff;

    public static void ConstInit() {
        
    }
}
