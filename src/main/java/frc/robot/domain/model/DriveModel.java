package frc.robot.domain.model;

public class DriveModel {
    /** ロボットの速度切り替え (fast, mid, slow, stop) */
    public static DriveMovement driveMovement;
    public enum DriveMovement {
        /** ロボットの速度を速くする */
        s_fastDrive,
        /** ロボットの速度を中くらいにする */
        s_midDrive,
        /** ロボットの速度を遅くする */
        s_slowDrive,
        /** ロボットの速度を0にする */
        s_stopDrive,
    }

    /** Robot Oriented と Field Oriented の切り替え */
    public static DriveOriented driveOriented;
    public enum DriveOriented {
        /** Robot Oriented で動く */
        s_robotOriented,
        /** Field Oriented で動く */
        s_fieldOriented,
    }

    /**
     * ドライブベースを動かす
     * @param driveXSpeed     直進成分 [-1 ~ 1]
     * @param driveYSpeed     直進成分 [-1 ~ 1]
     * @param driveThetaSpeed 回転成分 [-1 ~ 1] 反時計(左)回りが正
     */
    public static double driveXSpeed, driveYSpeed, driveThetaSpeed;

    public static void reset() {
        driveMovement = DriveMovement.s_stopDrive;
        driveXSpeed = 0;
        driveYSpeed = 0;
        driveThetaSpeed = 0;
    }

    public DriveModel() {
        reset();
    }
}