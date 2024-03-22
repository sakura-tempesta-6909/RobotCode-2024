package frc.robot.components.link;

public class LinkParameter {
    public static final class Angles {
        /**
         * 数値を変える！！
         */
        public static final double Amp = 0.8;
        public static final double SpeakerBelow = 0.5;
        public static final double SpeakerPodium = 0.3;
        public static final double SpeakerLeft = 0.02;
        public static final double SpeakerRight = 0.02;
        public static final double Intake = 0.02;
         public static final double Climb = 0.02;
    }
     //pidの値を書く
        public static class PID {
          public static final double LinkP = 5;
          public static final double LinkI = 0;
          public static final double LinkD = 0;
        }

    public static void ConstInit() {
    }
    
}