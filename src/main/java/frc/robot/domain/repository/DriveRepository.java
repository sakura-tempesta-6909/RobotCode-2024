package frc.robot.domain.repository;

public interface DriveRepository {
    /**
     * Robot orientedでロボットを動かす
     * @param sideSpeed    左右方向に移動するスピード | [-1, 1] | 止めたいとき0 | Robotに対して右に進むとき正
     * @param fowardSpeed    前後方向に移動するスピード | [-1, 1] | 止めたいとき0 | Robotに対して前に進むとき正
     * @param thetaSpeed    回転するスピード | [-1, 1] | 止めたいとき0 | Robotに対して反時計回りを正とする
     */
    void robotOriented(double sideSpeed, double fowardSpeed, double thetaSpeed);

    
    /**
     * Field orientedでロボットを動かす
     * @param sideSpeed    左右方向に移動するスピード | [-1, 1] | 止めたいとき0 | Fieldに対して右に進むとき正
     * @param fowardSpeed    前後方向に移動するスピード | [-1, 1] | 止めたいとき0 | Fieldに対して前に進むとき正
     * @param thetaSpeed    回転するスピード | [-1, 1] | 止めたいとき0 | Robotに対して反時計回りを正とする
     */
    void fieldOriented(double sideSpeed, double fowardSpeed, double thetaSpeed);

    
    /** ロボットを任意の角度に回転させる
     * @param currentAngle 現在の角度
     * @param setAngle 目標値の角度
     * @return thetaSpeedToSetAngle  回転させる角度
     */
    double setAngle(double setAngle);

    /**
     * センサーを読む
     */
    void readSensors();
}
