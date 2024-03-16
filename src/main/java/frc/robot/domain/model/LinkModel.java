package frc.robot.domain.model;

public class LinkModel {
    public static ShooterAngleMode driveBaseMode;

    public enum ShooterAngleMode {
        /** AMPにSHOOTをする */
        s_ampShoot,
        /** SPEAKERにPODIUMからSHOOTをする */
        s_speakerShootPodium,
        /** SPEAKERの真下からSHOOTをする*/
        s_speakerShootBelow,
        /** SPEAKERの左側（ドライバー目線で）からSHOOTをする */
        s_speakerShootLeft,
        /** SPEAKERの右側（ドライバー目線で）からSHOOTをする */
        s_speakerShootRight,
        /** NOTEを受け取る */
        s_intakeNote,
        /** CLIMBをする */
        s_climb,
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
        
    }

    public LinkModel() {
        reset();
    }
}