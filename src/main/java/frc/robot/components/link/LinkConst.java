 package frc.robot.components.link;

public class LinkConst {
    public static final class Ports {
        public static final int linkMotorLeft = 16;
        public static final int linkMotorRight = 17;
    }
    //1つのfileにpublic classは1つだけ！
    public class LinkLeftSoftLimit {
      public static final int ForwardSoftLimit = -250;
      public static final int ReverseSoftLimit = -490;
      public static final double PeakOutputForward = .3;
      public static final double PeakOutputReverse = -.3;
    }
    public class LinkRightSoftLimit {
      public static final int ForwardSoftLimit = -250;
      public static final int ReverseSoftLimit = -490;
      public static final double PeakOutputForward = .3;
      public static final double PeakOutputReverse = -.3;
    }
}
