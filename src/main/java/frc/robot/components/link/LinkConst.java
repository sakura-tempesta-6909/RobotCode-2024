package frc.robot.components.link;

public class LinkConst {
    public static final class Ports {
        public static final int linkMotorLeft = 12;
        public static final int linkMotorRight = 6;
    }
    //1つのfileにpublic classは1つだけ！
    public class LinkSoftLimit {
    public static final int ForwardSoftLimit = 480;
    public static final int ReverseSoftLimit = 250;
    public static final double PeakOutputForward = .3;
    public static final double PeakOutputReverse = -.3;
}
}
