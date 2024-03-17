package frc.robot.domain.model;

public class ShooterModel {
    public static ShooterMode shooterMode;
    public enum ShooterMode {
        s_noteIntake,
        s_noteOuttake,
        s_noteShootSpeaker,
        s_noteShootAmp,


    }
    public static void reset() {

    }
    public  ShooterModel() {
        reset();
    }
}
