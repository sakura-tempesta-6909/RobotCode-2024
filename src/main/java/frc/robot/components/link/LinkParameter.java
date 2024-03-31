package frc.robot.components.link;

import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
import frc.robot.components.link.LinkConst.LinkRightSoftLimit;

public class LinkParameter {
    public static final class Angles {
        /**
         * 数値を変える！！
         */
        public static final double AmpLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 236;
        public static final double SpeakerBelowLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 212;
        public static final double SpeakerPodiumLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 60;
        public static final double SpeakerSideLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 186;
        public static final double IntakeLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 156;
        public static final double ClimbLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 236;
        public static final double StageLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit;
        public static final double KeepCurrentAngleLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 40;

        public static final double AmpLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 236;
        public static final double SpeakerBelowLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 212;
        public static final double SpeakerPodiumLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 60;
        public static final double SpeakerSideLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 186;
        public static final double IntakeLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 156;
        public static final double ClimbLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 236;
        public static final double StageLinkRight = LinkRightSoftLimit.ReverseSoftLimit;
        public static final double KeepCurrentAngleLinkRight = LinkLeftSoftLimit.ReverseSoftLimit + 40;
    }
     //pidの値を書く
    public static class PID {
          public static final double LinkP = 5;
          public static final double LinkI = 2.5e-3;
          public static final double LinkD = 2;
    }
    public static class Percent {
        public static final double Climb = -0.6;
        public static final double KeepCurrentAngleLink = 0.05;
    }

    public static void ConstInit() {
    }
    
}