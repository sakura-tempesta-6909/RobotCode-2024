package frc.robot.components.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

class Const {
    static final class Ports {
        static final int DriveRightFront = 0;
        static final int DriveLeftFront = 1;
        static final int DriveRightBack = 2;
        static final int DriveLeftBack = 3;
    }

    static final class Speeds {
        static final double Neutral = 0;

        static final double FastDrive = 0.8;
        static final double MidDrive = 0.5;
        static final double SlowDrive = 0.3;

    }

    static final class PID {
        static final TalonSRXConfiguration leftPIDConfig = new TalonSRXConfiguration();
        static final TalonSRXConfiguration rightPIDConfig = new TalonSRXConfiguration();
    }

    static void ConstInit() {
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
