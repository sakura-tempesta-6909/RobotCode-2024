package frc.robot.components.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class Const {
    public static final class Ports {
        public static final int DriveRightFront = 0;
        public static final int DriveLeftFront = 1;
        public static final int DriveRightBack = 2;
        public static final int DriveLeftBack = 3;
    }

    public static final class Speeds {
        public static final double Neutral = 0;

        public static final double FastDrive = 0.8;
        public static final double MidDrive = 0.5;
        public static final double SlowDrive = 0.3;

    }

    public static final class PID {
        public static final TalonSRXConfiguration leftPIDConfig = new TalonSRXConfiguration();
        public static final TalonSRXConfiguration rightPIDConfig = new TalonSRXConfiguration();
    }

    public static void ConstInit() {
        PID.leftPIDConfig.slot0.kP = 0;
        PID.leftPIDConfig.slot0.kI = 0;
        PID.leftPIDConfig.slot0.kD = 0;
        PID.leftPIDConfig.slot0.maxIntegralAccumulator = 0;

        PID.rightPIDConfig.slot0.kP = 0;
        PID.rightPIDConfig.slot0.kI = 0;
        PID.rightPIDConfig.slot0.kD = 0;
        PID.rightPIDConfig.slot0.maxIntegralAccumulator = 0;
    }
}
