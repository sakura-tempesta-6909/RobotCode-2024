package frc.robot.domain.repository;

public interface ShooterRepository {
    /**
     * NOTEの回収をする
     */
    void noteIntake();


    /**
     * NOTEをSPEAKERにSHOOTをする
     */
    void noteShootSpeaker();

    
    /**
     * NOTEをAMPにSHOOTをする
     */
    void noteShootAmp();


    /**
     * ミスをした時にNOTEを吐き出す
     * 上(Shoot)との違い：
     * Shootはモーターがある程度の速さで回らないといけないが、吐き出す時にはいらない
     */
    void noteOuttake();


    /**
     * Intakeをやめる（Rollerの動きを止める）
     */
    void stopIntake();

    /**
     * 回転率を早める
     */
    void increaseRotation();

    /**
     * センサーを読みとる
     */
    void readSensors();
}
