package frc.robot.components.link;

import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
import frc.robot.components.link.LinkConst.LinkRightSoftLimit;

public class LinkParameter {
    public static final class LeftAngles {
        /**
         * 数値を変える！！
         */
        public static final double Amp = LinkLeftSoftLimit.ForwardSoftLimit;
        public static final double SpeakerBelow = 0.5;
        public static final double SpeakerPodium = 0.3;
        public static final double SpeakerLeft = 0.02;
        public static final double SpeakerRight = 0.02;
        public static final double Intake = 0.02;
        public static final double Climb = -0.6;
        public static final double Stage = LinkLeftSoftLimit.ReverseSoftLimit;
    }
    public static final class RightAngles {
        public static final double Amp = LinkRightSoftLimit.ForwardSoftLimit;
        public static final double SpeakerBelow = 0.5;
        public static final double SpeakerPodium = 0.3;
        public static final double SpeakerLeft = 0.02;
        public static final double SpeakerRight = 0.02;
        public static final double Intake = 0.02;
        public static final double Climb = -0.6;
        public static final double Stage = LinkRightSoftLimit.ReverseSoftLimit;
    }
     //pidの値を書く
    public static class PID {
          public static final double LinkP = 5;
          public static final double LinkI = 2.5e-3;
          public static final double LinkD = 2;
    }

    public static void ConstInit() {
    }
    
}