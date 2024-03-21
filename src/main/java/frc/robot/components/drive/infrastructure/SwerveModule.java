package frc.robot.components.drive.infrastructure;



import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.Time;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.drive.DriveConst;

public class SwerveModule {

    //2つNEOモーターがある(with a spark max motor controller)[5:09]
    private final CANSparkMax driveMotor;
    private final CANSparkMax turningMotor;

    //だから2つのエンコーダーにアクセスしたいよね[5:11]
    //CANEncoder -> RelativeEncoder
    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    //turning motorにabsolute encoderがついている[5:18]
    //ロボットの電源が切れるたびにモーターのエンコーダーは前の値を失うから
    //AnalogInput -> CANcoder
    private final CANcoder absoluteEncoder;

    //kasugaya
    SparkPIDController m_pidController;
    

    //[6:07]
    //IDとは、スパマについている番号。wheelは4つあり、その一つ一つに、turningMotor、driveMotor、absoluteEncoder(CANCoder)の３種類あるので、4*3=12まで番号が振られている。
    //ロボットを起動した瞬間から、動いている間もずっと呼び出される
    public SwerveModule(int driveMotorId, int turningMotorId, boolean turningMotorReversed, int absoluteEncoderId){
        //AnalogInput -> CANcoder
        absoluteEncoder = new CANcoder(absoluteEncoderId);

        //モーターのスパマ番号はこれだよと教えてあげる [6:15]
        driveMotor = new CANSparkMax(driveMotorId, MotorType.kBrushless);
        turningMotor = new CANSparkMax(turningMotorId, MotorType.kBrushless);

        driveMotor.setInverted(false);
        turningMotor.setInverted(turningMotorReversed);

        //どのエンコーダーを使うか教えてあげる
        driveEncoder = driveMotor.getEncoder();
        turningEncoder = turningMotor.getEncoder();

        //メートルとラジアン(弧度法)で処理できるようにエンコーダーの変数転換をする [6:23]
        driveEncoder.setPositionConversionFactor(DriveConst.ModuleConstants.kDriveEncoderRot2Meter);
        driveEncoder.setVelocityConversionFactor(DriveConst.ModuleConstants.kDriveEncoderRPM2MeterPerSec);
        turningEncoder.setPositionConversionFactor(DriveConst.ModuleConstants.kTurningEncoderRot2Rad);
        turningEncoder.setVelocityConversionFactor(DriveConst.ModuleConstants.kTurningEncoderRPM2RadPerSec);

        //kasugaya
        m_pidController = turningMotor.getPIDController();
        m_pidController.setP(0.5);
        m_pidController.setI(0);
        m_pidController.setD(0);
        
        m_pidController.setOutputRange(-1, 1);        
        //kasugaya

        //エンコーダーの値をリセットする
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            resetEncoders();
        });
        thread.start();
        
    }

    //エンコーダーの値を取得するための便利なメソッド [7:11]
    public double getDrivePosition(){
        //drive motor により何メートル進んだかを返す
        return driveEncoder.getPosition();
    }

    public double getTurningPosition(){
        //turning motor により何ラジアン動いたかを返す
        return turningEncoder.getPosition();
    }

    public double getDriveVelocity(){
        //drive motor により秒速何メートルで進んだかを返す
        return driveEncoder.getVelocity();
    }

    public double getTurningVelocity(){
        //turning motor により秒速何ラジアンで動いたかを返す
        return turningEncoder.getVelocity();
    }

    public double getAbsoluteEncoderRad(){
        //読み取った電圧の値を電圧で割る [7:22]
        //動画とさくてんが使うエンコーダーが違うので、神が直したよ
        //動画では電圧を使って回転数を出しているけど、さくてんは良いエンコーダーを使っているので、電圧とかは使わず、getAbosolutePosition()でsteerringが何回転したか返してくれる
        double angle = absoluteEncoder.getAbsolutePosition().getValueAsDouble();
        //何回転をラジアンに変換する
        angle *= 2 * Math.PI;
        return angle ;
    }

    //絶対値エンコーダーの値を与える [7:50]
    public void resetEncoders(){
        //driveEncoderを0にリセット [7:54]
        driveEncoder.setPosition(0);
        //absoluteEncoderの値をturningEncoderに教えてあげる [7:57]
        //absoluteEncoderは最初のsteeringの角度を知っているが、教えてあげないとsteeringの角度を知れない
        turningEncoder.setPosition(getAbsoluteEncoderRad());
    }

    //速さと角度の情報を返す関数を作る [8:23]
    public SwerveModuleState getState(){
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }
    
    /**
     * wheelを動かす
     * @param state
     */
    public void setDesiredState(SwerveModuleState state){
        //コントローラーを使い始めるとき毎回タイヤが0度になる問題を解決 [9:29]
        if(Math.abs(state.speedMetersPerSecond) < 0.001){
            stop();//15行後にあるよ
            return;
        }
        
        //操縦するときjoystickが90度を超えなくていいようにする [8:35]
        state = SwerveModuleState.optimize(state,getState().angle);
        //動きが速すぎるので 1/3 かけて遅くする [8:50]
        driveMotor.set(state.speedMetersPerSecond /3);
        //pid制御について角度の目標値と現在値を計算する？ [9:00]　春日谷君のコードに変える
        //turningMotor.set(turningPidController.calculate(getTurningPosition(), state.angle.getRadians()));

        //春日谷
        double setPoint = state.angle.getDegrees(); //getDegreesで度数法に変換
        SmartDashboard.putNumber("setPoint", setPoint);
        double current = turningEncoder.getPosition() / Math.PI / 2 * 360; //ラジアンを度数法に変換
        SmartDashboard.putNumber("current", current);
        if(setPoint < 0) {
            setPoint = setPoint % 360 ;
            setPoint = setPoint + 360 ;
        }
        setPoint = Math.floor(current/360) * 360 + setPoint;

        if(current - setPoint > 180) {
            setPoint = setPoint + 360;
        }else if(setPoint - current > 180) {
            setPoint = setPoint - 360;
        }
        //NEOが何回転するか -> 回転ではなくラジアンに直す
        /*double x_setPoint = setPoint * 5 / 84 ;
        SmartDashboard.putNumber("SetPoint", setPoint);*/
        //度数法から弧度法(ラジアン)に変換
        double x_setPoint = setPoint * Math.PI * 2 / 360;
        SmartDashboard.putNumber("setPoint2", setPoint);

        m_pidController.setReference(x_setPoint, CANSparkMax.ControlType.kPosition);

        SmartDashboard.putNumber("ProcessVariable", turningEncoder.getPosition());
        //春日谷

        //System.out.println(getTurningPosition() + " " + state.angle.getRotations());
        //デバッグ情報を送信 [9:03]
        SmartDashboard.putString("Swerve[" + absoluteEncoder.getDeviceID() + "] state", state.toString());
    }
    
    //[9:36]
    public void stop() {
        driveMotor.set(0);
        turningMotor.set(0);
    }

}

