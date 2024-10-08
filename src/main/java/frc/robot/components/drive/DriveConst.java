package frc.robot.components.drive;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

//ファイルには空のクラスのみを記述する
public final class DriveConst {
    public static final class ModuleConstants{
        public static final double kWheelDiameterMeters = edu.wpi.first.math.util.Units.inchesToMeters(4); //wheelの直径(メートル)?
        public static final double kDriveMotorGearRatio = 1/6.12; // NEO 1回転でdrive motor が 1/6.12 回転する | drive motor 1回転 : NEO 6.12回転
        public static final double kTurningMotorGearRatio = 7./150; // 数値改変  // NEO 1回転でturning motor が 7/150 回転する | turning motor 7回転 : NEO 150回転
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters; //NEOが1回転すると何メートル進むか
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI; //NEOが1回転すると何ラジアン回転するか
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter/60;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad/60;
        public static final double kPTurning = 0.5; //pid制御のp
    }

    public static final class DriveConstants {
        // Distance between right and left wheels
        public static final double kTrackWidth = Units.inchesToMeters(21);
        // Distance between front and back wheels
        public static final double kWheelBase = Units.inchesToMeters(25.5);

        // 機体の回転軸から見た4つのwheelの (x座標 , y座標)
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, kTrackWidth / 2), //frontLeftの情報が[0]に入っている
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2), //frontRightの情報が[1]に入っている
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2), //backLeftの情報が[2]に入っている
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2)); //backRightの情報が[3]に入っている

        //motorのIDを教える
        public static final int kFrontLeftDriveMotorPort = 10;
        public static final int kBackLeftDriveMotorPort = 1;
        public static final int kFrontRightDriveMotorPort = 7;
        public static final int kBackRightDriveMotorPort = 4;

        public static final int kFrontLeftTurningMotorPort = 12;
        public static final int kBackLeftTurningMotorPort = 3;
        public static final int kFrontRightTurningMotorPort = 9;
        public static final int kBackRightTurningMotorPort = 6;

        //反時計回り(true) , 時計回り(false)
        public static final boolean kFrontLeftTurningEncoderReversed = true;
        public static final boolean kBackLeftTurningEncoderReversed = true;
        public static final boolean kFrontRightTurningEncoderReversed = true;
        public static final boolean kBackRightTurningEncoderReversed = true;

        //absolute encoder (CANCoder) のIDを教える
        public static final int kFrontLeftDriveAbsoluteEncoderPort = 11;
        public static final int kBackLeftDriveAbsoluteEncoderPort = 2;
        public static final int kFrontRightDriveAbsoluteEncoderPort = 8;
        public static final int kBackRightDriveAbsoluteEncoderPort = 5;

        public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = DriveConstants.kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kMaxAngularSpeedRadiansPerSecond = //
                DriveConstants.kPhysicalMaxAngularSpeedRadiansPerSecond / 10;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
        /** 左右方向のPID制御のPの値 */
        public static final double kPXController = 1.5;
        /** 前後方向のPID制御のPの値 */
        public static final double kPYController = 1.5;
        /** 回転のPID制御のPの値 */
        public static final double kPThetaController = 3;

        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = //
                new TrapezoidProfile.Constraints(
                        kMaxAngularSpeedRadiansPerSecond,
                        kMaxAngularAccelerationRadiansPerSecondSquared);
    }
}
