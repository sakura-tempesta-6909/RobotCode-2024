package frc.robot.domain.model;

public class DriveModel {
    public static DriveBaseMode driveBaseMode;

    public enum DriveBaseMode {
        /** ロボットの速度を速くする */
        s_fastDrive,
        /** ロボットの速度を中くらいにする */
        s_midDrive,
        /** ロボットの速度を遅くする */
        s_slowDrive,
        /** ロボットの速度を0にする */
        s_stopDrive,
        /** PIDでまっすぐ前に進む */
        s_pidStraight,
        /** PIDで指定した角度に向く */
        s_pidTurn,
    }

    public static double driveXSpeed, driveZRotation;

    /* s_pidStraight */
    /** 直線PIDで進む距離 [cm] */
    public static double straightPIDTarget;
    /** 直線PIDのリセット */
    public static boolean resetStraightPID;

    /* s_pidTurn */
    /** 直線PIDで回転する角度 [degree] */
    public static double rotationPIDTarget;
    /** 回転PIDのリセット */
    public static boolean resetRotationPID;

    public static void reset() {
        driveBaseMode = DriveBaseMode.s_stopDrive;
        driveXSpeed = 0;
        driveZRotation = 0;
        straightPIDTarget = 0;
        rotationPIDTarget = 0;
        resetStraightPID = false;
        resetRotationPID = false;
    }

    public DriveModel() {
        reset();
    }
}