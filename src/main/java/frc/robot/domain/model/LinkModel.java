package frc.robot.domain.model;

public class LinkModel {
    public static ShooterAngleMode shooterAngleMode;

    public enum ShooterAngleMode {
        /** AMPにSHOOTする角度に変える */
        s_ampShoot,
        /** SPEAKERにPODIUMからSHOOTをする */
        s_speakerShootPodium,
        /** SPEAKERの真下からSHOOTをする*/
        s_speakerShootBelow,
        /** SPEAKERの左右（ドライバー目線で）からSHOOTをする */
        s_speakerShootSide,
        /** NOTEを受け取る */
        s_intakeNote,
        /** CLIMBをする */
        s_climb,
        /** Linkをチェーンを引っ掛ける角度にする */
        s_climbAngle,
        /** Climb時のLinkの角度を上に微調整する */
        s_climbUpFineAdjustment,
        /** Climb時のLinkの角度を下に微調整する */
        s_climbDownFineAdjustment,
        /** RightTriggerでLinkの角度を調整する */
        s_clib,
        /** 何も入力されていない時今の角度を維持する */
        s_keepCurrentAngle, 
        /** 720mm以下の角度 */
        s_stageAngle,
    }

    /** クライムする時のモーターの速度 */
    public static double climbSpeed;

    public static void reset() {
        shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
        resetPID = false;
    }

    public LinkModel() {
        reset();
    }

    public static boolean resetPID;
}
    
