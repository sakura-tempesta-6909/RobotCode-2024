package frc.robot.domain.repository;

public interface DriveRepository {
    /**
     * ドライブベースを動かす
     *
     * @param xSpeed    直進成分 [-1, 1]
     * @param zRotation 回転成分 [-1, 1] 反時計(左)回りが正
     */
    public abstract void arcadeDrive(double xSpeed, double zRotation);

    /**
     * PIDで直進する
     *
     * @param straightTarget 目標地点[cm]
     */
    public abstract void straightPID(double straightTarget);

    /**
     * 直進用のPIDControllerをリセットする
     */
    public abstract void resetStraightPIDController();

    /**
     * PIDで回転する
     *
     * @param targetAngle 目標角度
     */
    public abstract void rotationPID(double targetAngle);

    /**
     * 回転用のPIDControllerをリセットする
     */
    public abstract void resetRotationPIDController();

    /**
     * センサーを読む
     */
    public abstract void readSensors();
}
