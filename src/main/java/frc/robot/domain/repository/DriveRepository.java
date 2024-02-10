package frc.robot.domain.repository;

public interface DriveRepository {
    /**
     * ドライブベースを動かす
     *
     * @param xSpeed    直進成分 [-1, 1]
     * @param zRotation 回転成分 [-1, 1] 反時計(左)回りが正
     */
    void arcadeDrive(double xSpeed, double zRotation);

    /**
     * PIDで直進する
     *
     * @param straightTarget 目標地点[cm]
     */
    void straightPID(double straightTarget);

    /**
     * 直進用のPIDControllerをリセットする
     */
    void resetStraightPIDController();

    /**
     * PIDで回転する
     *
     * @param targetAngle 目標角度
     */
    void rotationPID(double targetAngle);

    /**
     * 回転用のPIDControllerをリセットする
     */
    void resetRotationPIDController();

    /**
     * センサーを読む
     */
    void readSensors();
}
