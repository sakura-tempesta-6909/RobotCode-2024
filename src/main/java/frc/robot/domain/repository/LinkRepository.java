package frc.robot.domain.repository;

public interface LinkRepository {
    /**
     * リンク機構を特定の角度に傾けることで、シューターの角度を傾ける
     するする
     * 
     * @param TargetShooterAngle 目標のlinkAngle[deg] シューターの角度を上げる方向を正とする
     */
    void MoveShooterToSpecifiedAngle(double TargetShooterLeftAngle, double TargetShooterRightAngle);
    
    /**
     * 今の角度を維持する
     */
    void KeepCurrentAngle();

    /**
     * センサーを読む
     */
    void readSensors();

    /**
     * climbする(Linkの角度を下げる時にPersentOutputを使用)
     * @param MostlyClimb 大体のClimb時の角度
     */
    void MoveShooterClimb();

    /**
     * climbするときにLinkの角度を微調整するために上げる
     * @param Up
     * @param Down
     */
    void MoveShooterFineAdjustment(double upOrDown); 
}
