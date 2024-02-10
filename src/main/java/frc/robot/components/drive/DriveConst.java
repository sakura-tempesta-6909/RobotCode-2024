package frc.robot.components.drive;

import edu.wpi.first.math.util.Units;

/**
 * プログラムで調整不可能な値
 */
public class DriveConst {
    public static final class Ports {
        public static final int DriveRightFront = 0;
        public static final int DriveLeftFront = 1;
        public static final int DriveRightBack = 2;
        public static final int DriveLeftBack = 3;
    }

    public static final class Wheel {
        /** [points/rotation] */
        static final double EncoderPointsPerRevolution = 4096;
        /** タイヤの直径 [cm] */
        static final double Cm = Units.inchesToMeters(6.0) * 100;
        /** タイヤの円周 [cm] */
        public static final double CmPerWheelRevolution = Cm * Math.PI;
        /** 1cm進むとどのくらいPointが増えるか [points/cm] */
        public static final double PointsPerCm = EncoderPointsPerRevolution / CmPerWheelRevolution;
        /** 最高RPM [rotation/minute] */
        static final double MaxRPM = 5000;
        /** 最高速度 速度PID制御のため [points/100ms] */
        public static final double MaxVelocity = EncoderPointsPerRevolution * MaxRPM / (60 * 10);
    }
}
