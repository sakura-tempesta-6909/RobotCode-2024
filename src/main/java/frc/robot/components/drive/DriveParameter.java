package frc.robot.components.drive;
//ファイルにはConstInitメソッドのみをもつクラスを記述する
public class DriveParameter {
    /** ロボットの速さを3段階で調整する */
    public static final class Speeds {
        public static final double Neutral = 0;

        public static final double FastDrive = 0.8;
        public static final double MidDrive = 0.4;
        public static final double SlowDrive = 0.2;

        public static final double FastThetaDrive = 0.6;
        public static final double MidThetaDrive = 0.3;
        public static final double SlowThetaDrive = 0.15;

        public static final double kP = 0.003;
        public static final double kI = 0;
        public static final double kD = 0;
        
    }

    public static void ConstInit(){
        
    }
}
