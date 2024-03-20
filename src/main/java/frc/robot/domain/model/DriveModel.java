package frc.robot.domain.model;

public class DriveModel {
    public static DriveMovement DriveMovement;
    public enum DriveMovement {
        /** ロボットの速度を速くする */
        s_fastDrive,
        /** ロボットの速度を中くらいにする */
        s_midDrive,
        /** ロボットの速度を遅くする */
        s_slowDrive,
        /** ロボットの速度を0にする */
        s_stopDrive,
        /** ロボットの回転を右回転にする */
        s_rightTurn,
        /** ロボットの回転を左回転にする */
        s_leftTurn,
        /** ロボットの方向をフィールドに対して前を向く */
        s_fowardTurn,
    }

    public static DriveOriented driveOriented;
    public enum DriveOriented {
        /** Field Oriented で動く */
        s_fieldOriented,
        /** Robot Oriented で動く */
        s_robotOriented;
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
        DriveMovement = DriveMovement.s_stopDrive;
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