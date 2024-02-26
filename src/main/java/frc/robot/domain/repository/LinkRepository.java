package frc.robot.domain.repository;

public interface LinkRepository {
    /**
     * リンクを曲げることによってシューターを傾ける
     *
     * @param TargetShooterAngle 目標のlinkAngle シューターの角度を上げる方向を正とする
     * 
     */
    void MoveShooterToSpecifiedAngle(double TargetShooterAngle);

}
