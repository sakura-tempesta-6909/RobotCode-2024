package frc.robot.components.drive;
//ファイルにはConstInitメソッドのみをもつクラスを記述する
public class DriveParameter {
    /** ロボットの速さを3段階で調整する */
    public static final class Speeds {
        /** ロボットを止めるときの速度 */
        public static final double Neutral = 0;

        /** ロボットが速いときの進む速度 */
        public static final double FastDrive = 0.8;
        /** ロボットが普通の速さのときの進む速度 */
        public static final double MidDrive = 0.4;
        /** ロボットが遅いときの進む速度 */
        public static final double SlowDrive = 0.25;

        /** ロボットが速いときの回転速度 */
        public static final double FastThetaDrive = 0.6;
        /** ロボットが普通の速さときの回転速度 */
        public static final double MidThetaDrive = 0.3;
        /** ロボットが遅いときの回転速度 */
        public static final double SlowThetaDrive = 0.2;

        /** gyroのPID制御　Pの値 */
        public static final double kP = 0.008;
        /** gyroのPID制御　Iの値 */
        public static final double kI = 0.0005;
        /** gyroのPID制御　Dの値 */
        public static final double kD = 0;
        
    }

    public static void ConstInit(){
        
    }
}
