package frc.robot.components.link;

import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
import frc.robot.components.link.LinkConst.LinkRightSoftLimit;

public class LinkParameter {
    public static final class Angles {
        /**
         * 数値を変える！！
         */
        public static final double AmpLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 235;
        public static final double SpeakerBelowLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 206;
        public static final double SpeakerPodiumLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 159;
        public static final double SpeakerSecondPodiumLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 88;
        public static final double IntakeLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 154;
        public static final double ClimbLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 235;
        public static final double StageLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit;
        public static final double KeepCurrentAngleLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 48;

        public static final double AmpLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 235;
        public static final double SpeakerBelowLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 206;
        public static final double SpeakerPodiumLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 159;
        public static final double SpeakerSecondPodiumLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 88;
        public static final double IntakeLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 154;
        public static final double ClimbLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 235;
        public static final double StageLinkRight = LinkRightSoftLimit.ReverseSoftLimit;
        public static final double KeepCurrentAngleLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 48;
    }
     //pidの値を書く
    public static class PID {
          public static final double UpLinkP = 8;
          public static final double UpLinkI = 4e-3;
          public static final double UpLinkD = 2;

          public static final double DownLinkP = 5;
          public static final double DownLinkI = 2.5e-3;
          public static final double DownLinkD = 2;
    }
    public static class Percent {
        public static final double Climb = -0.6;
        public static final double KeepCurrentAngleLink = 0.05;
    }

    public static void ConstInit() {
    }
    
}