package frc.robot.components.drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

/**
 * プログラムで調整可能な値
 */
public class DriveParameter {
    public static final class Speeds {
        public static final double Neutral = 0;

        public static final double FastDrive = 0.8;
        public static final double MidDrive = 0.5;
        public static final double SlowDrive = 0.3;
        public static final double TrapezoidalAcceleration = 0.02;
    }

    public static final class PID {
        public static final TalonSRXConfiguration leftPIDConfig = new TalonSRXConfiguration();
        public static final TalonSRXConfiguration rightPIDConfig = new TalonSRXConfiguration();
        public static final double RotationPID_kP = 0;
        public static final double RotationPID_kI = 0;
        public static final double RotationPID_kD = 0;
        public static final double RotationPID_maxIAccum = 0;
    }

    public static void ConstInit() {
        /* 位置用 */
        PID.leftPIDConfig.slot0.kP = 0;
        PID.leftPIDConfig.slot0.kI = 0;
        PID.leftPIDConfig.slot0.kD = 0;
        PID.leftPIDConfig.slot0.maxIntegralAccumulator = 0;
        PID.leftPIDConfig.slot0.integralZone = 0;

        PID.rightPIDConfig.slot0.kP = 0;
        PID.rightPIDConfig.slot0.kI = 0;
        PID.rightPIDConfig.slot0.kD = 0;
        PID.rightPIDConfig.slot0.maxIntegralAccumulator = 0;
        PID.rightPIDConfig.slot0.integralZone = 0;

        /* 速度用 */
        PID.leftPIDConfig.slot1.kP = 0;
        PID.leftPIDConfig.slot1.kI = 0;
        PID.leftPIDConfig.slot1.kD = 0;
        PID.leftPIDConfig.slot1.maxIntegralAccumulator = 0;

        PID.rightPIDConfig.slot1.kP = 0;
        PID.rightPIDConfig.slot1.kI = 0;
        PID.rightPIDConfig.slot1.kD = 0;
        PID.rightPIDConfig.slot1.maxIntegralAccumulator = 0;
    }
}
