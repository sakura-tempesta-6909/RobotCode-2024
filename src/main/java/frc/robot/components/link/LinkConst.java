 package frc.robot.components.link;

import edu.wpi.first.wpilibj.StadiaController;

public class LinkConst {
    public static final class Ports {
        public static final int linkMotorLeft = 17;
        public static final int linkMotorRight = 16;
    }
    //1つのfileにpublic classは1つだけ！
    public class LinkLeftSoftLimit {
      // //3/24の値
      // public static final int ForwardSoftLimit = -1270;
      // public static final int ReverseSoftLimit = -1500;

      //3/25の値
      public static final int ForwardSoftLimit = -255;
      public static final int ReverseSoftLimit = -490;
      public static final double PeakOutputForward = .6;
      public static final double PeakOutputReverse = -.6;
    }
    public class LinkRightSoftLimit {
      // //3/24の値
      // public static final int ForwardSoftLimit = 1546;
      // public static final int ReverseSoftLimit = 1316;

      //3/25の値
      public static final int ForwardSoftLimit = 512;
      public static final int ReverseSoftLimit = 277;
      public static final double PeakOutputForward = .6;
      public static final double PeakOutputReverse = -.6;
    }
    public class FineAdjustment {
      public static final double downAdjustment = -0.1;
      public static final double upAdjustment = 0.1;
    }
}
