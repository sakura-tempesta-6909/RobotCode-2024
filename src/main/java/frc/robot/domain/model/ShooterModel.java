package frc.robot.domain.model;

public class ShooterModel {
    public static ShooterMode driveBaseMode;

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

    }
 
    public static void reset() {
        
    }

    public ShooterModel() {
        reset();
    }

}





