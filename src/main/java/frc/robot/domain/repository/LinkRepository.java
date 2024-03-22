package frc.robot.domain.repository;

public interface LinkRepository {
    /**
     * リンク機構を特定の角度に傾けることで、シューターの角度を傾ける
     * 
     * @param TargetShooterAngle 目標のlinkAngle[deg] シューターの角度を上げる方向を正とする
     */
    void MoveShooterToSpecifiedAngle(double TargetShooterAngle);
    
    /**
     * 今の角度を維持する
     */
    void KeepCurrentAngle();

    /**
     * センサーを読む
     */
    void readSensors();
}
