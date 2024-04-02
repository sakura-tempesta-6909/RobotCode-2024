package frc.robot.domain.measure;

public class ShooterMeasuredState {
    /** ShooterのUpper&Lowerの取得した速度 */
    public static double shooterUpperSpeed;
    public static double shooterLowerSpeed;
    /** 今noteを保持しているかどうか */
    public static boolean isNoteGet;
    /** 今Shootができるかどうか */
    public static boolean readyToShoot;
    /** Shootが0.2秒以上維持できるかのためのカウンター */
    public static int counter = 0;
}
