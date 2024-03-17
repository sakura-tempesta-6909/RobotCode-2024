package frc.robot.domain.repository;

public interface ShooterRepository {
    /**
     * NOTEの回収をする
     */
    void noteIntake(double shooterSpeed, double pusherSpeed);


    /**
     * NOTEをSPEAKERにSHOOTをする
     */
    void noteShootSpeaker(double targetSpeed);

    
    /**
     * NOTEをAMPにSHOOTをする
     */
    void noteShootAmp(double shooterSpeed, double pusherSpeed);


    /**
     * 
     * ミスをした時にNOTEを吐き出す
     * 上(Shoot)との違い：
     * Shootはモーターがある程度の速さで回らないといけないが、吐き出す時にはいらない
     * 
     */
    void noteOuttake(double shooterSpeed, double pusherSpeed);

    void readSensors();

}
