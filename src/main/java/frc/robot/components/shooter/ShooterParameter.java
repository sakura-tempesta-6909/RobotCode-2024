package frc.robot.components.shooter;

public class ShooterParameter {
    public static class Speed {
        public static final double ShooterTargetSpeed = 4000;
        public static final double PusherSpeakerSpeed = 0.5;
        public static final double PusherAmpSpeed = 0.2;
        public static final double PusherIntakeSpeed = 0.2;
        public static final double PusherOuttakeSpeed = 0.2;
        public static final double ShooterIntakeSpeed = 0.3;
        public static final double ShooterSpeedWhenPusherMove = 3950;
        public static final double Neutral = 0;
    }
    public static class PID {
        public static final double ShooterP = 0.0002;
        public static final double ShooterI = 5e-7;
        public static final double ShooterD = 0;
    }
    public static void ConstInit() {

    }
}
