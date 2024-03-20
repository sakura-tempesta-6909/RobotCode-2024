package frc.robot.domain.model;

public class DriveModel {
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
        /** PIDで指定した角度に向く */
        s_pidTurn,
    }

    public static DriveOrientedStraight driveOrientedStraight;
    public enum DriveOrientedStraight {
        /** Field Oriented でまっすぐ前に進む */
        s_fieldOrientedStraight,
        /** Robot Oriented でまっすぐ前に進む */
        s_robotOrientedStraight;
    }

    /**
     * ドライブベースを動かす
     *
     * @param driveXSpeed    直進成分 [-1 ~ 1]
     * @param driveZRotation 回転成分 [-1 ~ 1] 反時計(左)回りが正
     */
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
        driveMovement = DriveMovement.s_stopDrive;
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