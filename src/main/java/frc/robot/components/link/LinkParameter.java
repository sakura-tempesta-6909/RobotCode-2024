package frc.robot.components.link;

import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
import frc.robot.components.link.LinkConst.LinkRightSoftLimit;

public class LinkParameter {
    public static final class Angles {
        /**
         * 数値を変える！！
         */
        public static final double AmpLinkLeft = LinkLeftSoftLimit.ForwardSoftLimit;
        public static final double SpeakerBelowLinkLeft = LinkLeftSoftLimit.ForwardSoftLimit - 24;
        public static final double SpeakerPodiumLinkLeft = 0.3;
        public static final double SpeakerSideLinkLeft = 0.02;
        public static final double IntakeLinkLeft = 0.02;
        public static final double ClimbLinkLeft = -0.6;
        public static final double StageLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit;

        public static final double AmpLinkRight = LinkRightSoftLimit.ForwardSoftLimit;
        public static final double SpeakerBelowLinkRight = LinkRightSoftLimit.ForwardSoftLimit - 24;
        public static final double SpeakerPodiumLinkRight = 0.3;
        public static final double SpeakerSideLinkRight = 0.02;
        public static final double IntakeLinkRight = 0.02;
        public static final double ClimbLinkRight = -0.6;
        public static final double StageLinkRight = LinkRightSoftLimit.ReverseSoftLimit;
    }
     //pidの値を書く
    public static class PID {
          public static final double LinkP = 5;
          public static final double LinkI = 2.5e-3;
          public static final double LinkD = 2;
    }
    public static class Percent {
        public static final double Climb = -0.6;
    }

    public static void ConstInit() {
    }
    
}