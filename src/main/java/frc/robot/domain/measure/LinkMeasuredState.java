package frc.robot.domain.measure;

public class LinkMeasuredState {
    /**Linkの現在の角度 */
    public static double linkAngleSensor;
    /**Ampの高さか否か */
    public static boolean linkAmpsHeight;
    /**Speakerの高さか否か */
    public static boolean linkSpeakerHeight;
    /**720mm以下か否か(Stageの高さより下) */
    public static boolean linkUnderStage;
    /**climbの高さか否か */
    public static boolean linkClimbHeight;
}
