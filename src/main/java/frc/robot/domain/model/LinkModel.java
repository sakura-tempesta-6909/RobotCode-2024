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
        //*Climbの角度にする */
        s_climbAngle,
        //*Climb時のLinkの角度を上に微調整する */
        s_climbUpFineAdjustment,
        //*Climb時のLinkの角度を下に微調整する */
        s_climbDownFineAdjustment,
        /** 何も入力されていない時今の角度を維持する */
        s_keepCurrentAngle
    }
    public static void reset() {
        shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
    }

    public LinkModel() {
        reset();
    }
}
    
