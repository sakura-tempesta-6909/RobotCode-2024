package frc.robot.domain.repository;

public interface DriveRepository {
    /**
     * Robot orientedでロボットを動かす
     * @param xSpeed    左右方向に移動するスピード | [-1, 1] | 止めたいとき0 | Robotに対して右に進むとき正
     * @param ySpeed    前後方向に移動するスピード | [-1, 1] | 止めたいとき0 | Robotに対して前に進むとき正
     * @param thetaSpeed    回転するスピード | [-1, 1] | 止めたいとき0 | Robotに対して反時計回りを正とする
     */
    void robotOriented(double xSpeed, double ySpeed, double thetaSpeed);

    
    /**
     * Field orientedでロボットを動かす
     * @param xSpeed    左右方向に移動するスピード | [-1, 1] | 止めたいとき0 | Fieldに対して右に進むとき正
     * @param ySpeed    前後方向に移動するスピード | [-1, 1] | 止めたいとき0 | Fieldに対して前に進むとき正
     * @param thetaSpeed    回転するスピード | [-1, 1] | 止めたいとき0 | Robotに対して反時計回りを正とする
     */
    void fieldOriented(double xSpeed, double ySpeed, double thetaSpeed);

    /**
     * センサーを読む
     */
    void readSensors();
}
