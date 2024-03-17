package frc.robot.domain.repository;

public interface DriveRepository {
    /**
     * Robot orientedでロボットを動かす
     */
    void robotOriented(double xSpeed, double ySpeed, double thetaSpeed);

    /**
     * Field orientedでロボットを動かす
     */
    void fieldOriented(double xSpeed, double ySpeed, double thetaSpeed);

    /**
     * センサーを読む
     */
    void readSensors();
}
