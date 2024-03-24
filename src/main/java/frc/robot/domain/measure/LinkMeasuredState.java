package frc.robot.domain.measure;

public class LinkMeasuredState {
    /**Linkの現在の角度 */
    public static double linkLeftAngle;
    /**Ampの高さか否か */
    public static boolean linkAmpsHeight;
    /**Speakerの高さか否か */
    //A
    public static boolean linkAmpHeight;
    /**Speakerの真下の高さか否か */
    //B
    public static boolean linkSpeakerHeight;
    /**720mm以下か否か(Stageの高さより下) */
    //L
    public static boolean linkUnderStage;
    /**climbの高さか否か */
    //Y
    public static boolean linkClimbHeight;
    //**Sourceの高さか否か */
    //X
    public static boolean linkSourceHeight;
    //**Podiumを後ろのBumperが超えているときのLinkの高さに達しているか否か */
    public static boolean linkOverPodium;
}
