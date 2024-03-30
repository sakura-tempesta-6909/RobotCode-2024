package frc.robot.components.shooter;

public class ShooterParameter {
    public static class Speed {
        public static final double ShooterTargetSpeed = 4000;
        public static final double PusherShootSpeed = 0.5;
        public static final double PusherIntakeSpeed = 0.2;
        public static final double PusherOuttakeSpeed = 0.2;
        public static final double ShooterIntakeSpeed = 0.3;
        public static final double ShooterSpeedWhenPusherMove = 3950;
        public static final double Neutral = 0;
    }
    public static class PID {
        public static final double ShooterP = 0.0002;
        public static final double ShooterI = 2.05e-7;
        public static final double ShooterD = 0;
        public static final double ShooterF = 0.00015;
    }

    public static class ShootingMotor {
        /** シュートできるUpper&Lower Speedの速度 */
        public static final double shootAvailableSpeedUpper = 3950;
        public static final double shootAvailableSpeedLower = 3950;
        /** シュートできる絶対値の値 */
        public static final double shootAvailableAbsolute = 200;

    }
    public static void ConstInit() {

    }
}
