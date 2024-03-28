package frc.robot.components.drive.infrastructure;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//9:54
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.components.drive.DriveConst;
import frc.robot.components.drive.DriveConst.DriveConstants;
import frc.robot.domain.measure.DriveMeasuredState;

public class SwerveSubsystem extends SubsystemBase{
    public final SwerveModule frontLeft = new SwerveModule(
        DriveConst.DriveConstants.kFrontLeftDriveMotorPort,
        DriveConst.DriveConstants.kFrontLeftTurningMotorPort,
        DriveConst.DriveConstants.kFrontLeftTurningEncoderReversed,
        DriveConst.DriveConstants.kFrontLeftDriveAbsoluteEncoderPort);
    
    public final SwerveModule frontRight = new SwerveModule(
        DriveConst.DriveConstants.kFrontRightDriveMotorPort,
        DriveConst.DriveConstants.kFrontRightTurningMotorPort,
        DriveConst.DriveConstants.kFrontRightTurningEncoderReversed,
        DriveConst.DriveConstants.kFrontRightDriveAbsoluteEncoderPort);

    public final SwerveModule backLeft = new SwerveModule(
        DriveConst.DriveConstants.kBackLeftDriveMotorPort,
        DriveConst.DriveConstants.kBackLeftTurningMotorPort,
        DriveConst.DriveConstants.kBackLeftTurningEncoderReversed,
        DriveConst.DriveConstants.kBackLeftDriveAbsoluteEncoderPort);

    public final SwerveModule backRight = new SwerveModule(
        DriveConst.DriveConstants.kBackRightDriveMotorPort,
        DriveConst.DriveConstants.kBackRightTurningMotorPort,
        DriveConst.DriveConstants.kBackRightTurningEncoderReversed,
        DriveConst.DriveConstants.kBackRightDriveAbsoluteEncoderPort);
        
    //ロボットを起動するたびにジャイロスコープをリセットし、その方向を順方向に設定したい[10:08]
    //AHRS -> ADXRS450_Gyro
    //AHRS(SPI.Port.kMXP) -> ADXRS450_Gyro()
    //このジャイロだと教える
    private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    //[20:00]
    private final SwerveDriveOdometry odometer = new SwerveDriveOdometry(DriveConstants.kDriveKinematics, new Rotation2d(0),
    new SwerveModulePosition[]{
        frontLeft.getPosition(),
        frontRight.getPosition(),
        backLeft.getPosition(),
        backRight.getPosition()
    });

    //ジャイロスコープは起動するたびに再調整で忙しいから、1秒遅らせてからリクエストする[10:35]
    public SwerveSubsystem(){
        new Thread(() -> {
            try{
                Thread.sleep(1000);
            }catch(Exception e){
            }
        }).start();
    }
    public void zeroHeading(){
        gyro.reset();
    }

    //デフォルトでジャイロスコープからロボットの進行方向を取得する関数を作る[10:44]
    //ジャイロスコープは連続値のため、360度や720度などになってしまうので、わかりやすくするため-180度~180度に変換[11:01]
    public double getHeading(){
        return Math.IEEEremainder(gyro.getAngle(), 360);
    }
    //getHeading()は度数法なので Rotation2d 型に変換する
    public Rotation2d getRotation2d(){
        return Rotation2d.fromDegrees(getHeading());
    }
    //[20:13]
    public Pose2d getPose(){
        return odometer.getPoseMeters();
    }
    //Pose2d は平行移動2Dと回転2Dを持つクラス
    public void resetOdometry(Pose2d pose) {
        odometer.resetPosition(getRotation2d(), new SwerveModulePosition[]{
        frontLeft.getPosition(),
        frontRight.getPosition(),
        backLeft.getPosition(),
        backRight.getPosition()
    },pose);
    }

    //ロボットの進行方向の値を監視[11:13]
    @Override
    public void periodic(){
        //[20:10]
        odometer.update(getRotation2d(), new SwerveModulePosition[]{
        frontLeft.getPosition(),
        frontRight.getPosition(),
        backLeft.getPosition(),
        backRight.getPosition()
    });
        SmartDashboard.putNumber("Robot Heading", getHeading());
        //[20:30]
        SmartDashboard.putString("Robot Location", getPose().getTranslation().toString());
        DriveMeasuredState.drivePosition = getPose();
    }

    //モジュールを停止する関数[11:20]
    public void stopModules(){
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    //モジュールを設定する関数[11:21]
    public void setModuleStates(SwerveModuleState[] desiredStates){
        //frontLeftの情報が[0]に入っている
        //frontRightの情報が[1]に入っている
        //backLeftの情報が[2]に入っている
        //backRightの情報が[3]に入っている
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }

}
