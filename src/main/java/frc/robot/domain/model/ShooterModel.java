package frc.robot.domain.model;

public class ShooterModel {
    public static ShooterMode shooterMode;

    public enum ShooterMode {
        /** NOTEの回収 */
        s_intake,
        /** NOTEのSPEAKERへのSHOOT */
        s_shootSpeaker,
        /** NOTEのAMPへのSHOOT */
        s_shootAmp,
        /** NOTEのouttake */
        s_outtake,
        /** Intakeを止める */
        s_stopIntake, 
        /**　回転数を早める */
        s_increaseRotation,
    }
 
    public static void reset() {
        shooterMode = ShooterMode.s_stopIntake;
    }

    public ShooterModel() {
        reset();
    }

}


