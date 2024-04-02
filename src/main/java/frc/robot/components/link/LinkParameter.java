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
        public static final double SetClimbLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 235;
        public static final double StageLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit;
        public static final double KeepCurrentAngleLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 48;
        public static final double ClimbLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit;

        public static final double AmpLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 235;
        public static final double SpeakerBelowLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 206;
        public static final double SpeakerPodiumLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 159;
        public static final double SpeakerSecondPodiumLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 88;
        public static final double IntakeLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 154;
        public static final double SetClimbLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 235;
        public static final double StageLinkRight = LinkRightSoftLimit.ReverseSoftLimit;
        public static final double KeepCurrentAngleLinkRight = LinkRightSoftLimit.ReverseSoftLimit + 48;
        public static final double ClimbLinkRight = LinkRightSoftLimit.ReverseSoftLimit;
    }

    //pidの値を書く
    public static class PID {
        public static final double UpLinkP = 6;
        public static final double UpLinkI = 1e-3;
        public static final double UpLinkD = 2;

        public static final double DownLinkP = 2;
        public static final double DownLinkI = 2.5e-4;
        public static final double DownLinkD = 2;

        public static final double ClimbLinkP = 8;
        public static final double ClimbLinkI = 4e-3;
        public static final double ClimbLinkD = 2;
    }

    public static class Current {
        public static final double ClimbCurrent = 40;
    }

    public static class Percent {
        public static final double Climb = -0.6;
        public static final double KeepCurrentAngleLink = 0.05;
    }

    public class FineAdjustment {
        public static final double downAdjustment = -0.1;
        public static final double upAdjustment = 0.1;
    }

    public static void ConstInit() {
    }

}