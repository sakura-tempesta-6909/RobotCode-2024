package frc.robot.domain.repository;

public interface LinkRepository {
    /**
     * リンク機構を特定の角度に傾けることで、シューターの角度を傾ける
     * 
     * @param TargetShooterLeftAngle 目標のlinkLeftAngle[deg] シューターの角度を上げる方向を正とする
     * @param TargetShooterRightAngle 目標のlinkRightAngle[deg] シューターの角度を上げる方向を正とする
     */
    void MoveShooterToSpecifiedAngle(double TargetShooterLeftAngle, double TargetShooterRightAngle);
    
    /**
     * 今の角度を維持する
     */
    void KeepCurrentAngle();

    /**
     * climbする(Linkの角度を下げる時にPersentOutputを使用)
     * @param MostlyClimb 大体のClimb時の角度
     */
    void MoveShooterClimb();

    /**
     * climbするときにLinkの角度を微調整するために上げる
     * @param Up Climb時にLInkの角度を任意の分だけ少しずつ上げる(PercentOutput使用)
     * @param Down Climb時にLInkの角度を任意の分だけ少しずつ下げる(PercentOutput使用)
     */
    void MoveShooterFineAdjustment(double upOrDown); 

    void resetPID();

    /**
     * センサーを読む
     */
    void readSensors();
}
