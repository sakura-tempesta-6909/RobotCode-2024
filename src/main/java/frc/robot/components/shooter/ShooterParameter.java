package frc.robot.components.shooter;

public class ShooterParameter {
    public static class Speed {
        /** SpeakerにShooterする時のShooter(上下）の理想の速度 */
        public static final double ShooterTargetSpeed = 4500;
        /** SpeakerにShootする時のPusherの速度 */
        public static final double PusherShootSpeed = 0.3;
        /** AmpにShootする時のPusherの速度 */
        public static final double PusherAmpSpeed = 0.3;
        /** AmpにShootする時のShooterの速度 */
        public static final double ShooterAmpSpeed = 0.38;
        /** Intakeする時のPusherの速度 */
        public static final double PusherIntakeSpeed = 0.2;
        /** Intakeする時のShooterの速度 */
        public static final double ShooterIntakeSpeed = 0.3;
        /** Outtakeする時のPusherの速度 */
        public static final double PusherOuttakeSpeed = 0.3;
        /** 初期設定 */
        public static final double Neutral = 0;
    }
    public static class PID {
        /**ShooterのPIDの値 */
        public static final double ShooterP = 0.000047;
        public static final double ShooterI = 1.5e-7;
        public static final double ShooterD = 100;
        public static final double ShooterF = 0.00018;
        public static final double ShooterIZone = 1000;
    }

    public static class ShootLimit {
        /** シュートできるUpper&Lower Speedの速度 */
        public static final double ShootLowerLimitSpeed = 4450;
        
        public static final double ShootUpperLimitSpeed = 4550;

        /** シュートできる時のUpperとLowerの速度絶対値の値 */
        public static final double ShootLimitAbsoluteValue = 50;
    }
    public static void ConstInit() {

    }
}
