package frc.robot.components.link;

public class LinkConst {
    public static final class Ports {
        public static final int linkMotorLeft = 16;
        public static final int linkMotorRight = 17;
    }
    //1つのfileにpublic classは1つだけ！
    public class LinkSoftLimit {
    public static final int ForwardSoftLimit = -270;
    public static final int ReverseSoftLimit = -480;
    public static final double PeakOutputForward = .3;
    public static final double PeakOutputReverse = -.3;
    }
}
