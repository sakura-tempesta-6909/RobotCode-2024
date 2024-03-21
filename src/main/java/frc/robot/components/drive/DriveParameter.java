package frc.robot.components.drive;
//ファイルにはConstInitメソッドのみをもつクラスを記述する
public class DriveParameter {
    /** ロボットの速さを3段階で調整する */
    public static final class Speeds {
        public static final double Neutral = 0;

        public static final double FastDrive = 0.8;
        public static final double MidDrive = 0.5;
        public static final double SlowDrive = 0.3;
    }

    public static void ConstInit(){
        
    }
}
