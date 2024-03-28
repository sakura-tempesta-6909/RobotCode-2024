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
    
