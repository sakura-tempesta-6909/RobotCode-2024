package frc.robot.domain.model;

public class LinkModel {
    public static ShooterAngleMode shooterAngleMode;

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
        /** 何も入力されていない時 */
        s_inputnothing
    }

    public static void reset() {
        shooterAngleMode = ShooterAngleMode.s_inputnothing;
    }

    public LinkModel() {
        reset();
    }
}
    
