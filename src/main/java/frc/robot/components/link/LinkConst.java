 package frc.robot.components.link;

public class LinkConst {
    public static final class Ports {
        public static final int linkMotorLeft = 17;
        public static final int linkMotorRight = 16;
    }
    //1つのfileにpublic classは1つだけ！
    public class LinkLeftSoftLimit {
      /** 最大高度 */
      public static final int ForwardSoftLimit = -780;
      /** 最低高度 */
      public static final int ReverseSoftLimit = -1001;
      /** 最大PercentOutput */
      public static final double PeakOutputForward = .6;
      /** 最低PercentOutput */
      public static final double PeakOutputReverse = -.4;
    }
    public class LinkRightSoftLimit {
      /** 最大高度 */
      public static final int ForwardSoftLimit = 509;
      /** 最低高度 */
      public static final int ReverseSoftLimit = 250;
      /** 最大PercentOutput */
      public static final double PeakOutputForward = .6;
      /** 最低PercentOutput */
      public static final double PeakOutputReverse = -.4;
    }
}
