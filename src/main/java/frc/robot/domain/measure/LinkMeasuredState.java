package frc.robot.domain.measure;

public class LinkMeasuredState {
    /**LinkLeftの現在の角度 */
    public static double linkLeftAngle;
    //*LinkRightの現在の角度 */
    public static double linkRightAngle;
    /**Ampの高さか否か */
    public static boolean linkAmpHeight;
    /**SpeakerBelowの高さか否か */
    public static boolean linkSpeakerBelowHeight;
    /**720mm以下か否か(Stageの高さより下) */
    public static boolean linkUnderStageHeight;
    /**climbの高さか否か */
    public static boolean linkClimbHeight;
    //**Sourceの高さか否か */
    public static boolean linkIntakeHeight;
    //**Podiumを後ろのBumperが超えているときのLinkの高さに達しているか否か */
    public static boolean linkPodiumHeight;
    //**Speakerが第2Podiumの高さに達しているか否か */
    public static boolean linkSpeakerSecondPodiumHeight;
}
