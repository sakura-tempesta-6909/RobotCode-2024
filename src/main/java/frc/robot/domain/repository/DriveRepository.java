package frc.robot.domain.repository;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public interface DriveRepository {
    /**
     * Robot orientedでロボットを動かす
     * @param sideSpeed    左右方向に移動するスピード | [-1, 1] | 止めたいとき0 | Robotに対して右に進むとき正
     * @param forwardSpeed    前後方向に移動するスピード | [-1, 1] | 止めたいとき0 | Robotに対して前に進むとき正
     * @param thetaSpeed    回転するスピード | [-1, 1] | 止めたいとき0 | Robotに対して反時計回りを正とする
     */
    void robotOriented(double sideSpeed, double forwardSpeed, double thetaSpeed);

    
    /**
     * Field orientedでロボットを動かす
     * @param sideSpeed    左右方向に移動するスピード | [-1, 1] | 止めたいとき0 | Fieldに対して右に進むとき正　//左じゃない？
     * @param forwardSpeed    前後方向に移動するスピード | [-1, 1] | 止めたいとき0 | Fieldに対して前に進むとき正
     * @param thetaSpeed    回転するスピード | [-1, 1] | 止めたいとき0 | Robotに対して反時計回りを正とする
     */
    void fieldOriented(double sideSpeed, double forwardSpeed, double thetaSpeed);

    /** ロボットを任意の位置に移動させる
     * @param setPositionPose2d 目標値の座標
     * @param setAngleRotation2d 目標値の角度
     * @param time 移動させる時間
     */
    void setPosition(Pose2d setPositionPose2d, Rotation2d setAngleRotation2d, double time);
    
    /** ロボットを任意の角度に回転させる
     * @param setAngle 目標値の角度
     * @return thetaSpeedToSetAngle  回転させる角度
     */
    double setAngle(double setAngle);

    /** ジャイロセンサーをリセットする */
    void resetGyroSensor();

    /**
     * センサーを読む
     */
    void readSensors();
}
