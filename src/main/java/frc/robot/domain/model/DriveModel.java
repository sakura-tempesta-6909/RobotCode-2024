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

    /** ロボットを任意の角度に回転させる trueの時にPIDで回転させる */
    public static boolean driveAngle;
    /** ロボットの目標の角度 */
    public static double setAngle;

    /** trueの時にジャイロセンサーをリセットする */
    public static boolean resetGyroSensor;

    /**
     * ドライブベースを動かす
     * @param driveSideSpeed     左右成分 [-1 ~ 1] 右に進むとき正
     * @param driveForwardSpeed     前後成分 [-1 ~ 1] 前に進むとき正
     * @param driveThetaSpeed 回転成分 [-1 ~ 1] 反時計(左)回りが正
     */
    public static double driveSideSpeed, driveForwardSpeed, driveThetaSpeed;

    public static void reset() {
        driveMovement = DriveMovement.s_stopDrive;
        driveOriented = DriveOriented.s_fieldOriented;
        driveSideSpeed = 0;
        driveForwardSpeed = 0;
        driveThetaSpeed = 0;
        driveAngle = false;
        resetGyroSensor = false;
    }

    public DriveModel() {
        reset();
    }
}