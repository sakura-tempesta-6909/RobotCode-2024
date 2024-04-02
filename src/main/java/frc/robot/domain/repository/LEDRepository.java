package frc.robot.domain.repository;

public interface LEDRepository {
    
    /**
     * LEDを変えたい色に変える
     * この状態の場合はこの色という情報はchangeModelでやる
     */
    void changeLight(int red, int green, int blue);

    void flashLight(int red, int green, int blue);

    /**
     * センサーを読む
     */
    void readSensors();
}
