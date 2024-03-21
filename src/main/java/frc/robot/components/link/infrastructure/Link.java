package frc.robot.components.link.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.link.LinkConst;
import frc.robot.components.link.LinkParameter;
import frc.robot.components.link.LinkParameter.PID;
import frc.robot.domain.repository.LinkRepository;

public class Link implements LinkRepository {
    //Linkの持ち物検査
    final VictorSPX linkMotorRight;
    final TalonSRX linkMotorLeft;

    public Link() {
        LinkParameter.ConstInit();

        //属性の初期化
        linkMotorLeft = new TalonSRX(LinkConst.Ports.linkMotorLeft);
        linkMotorRight = new VictorSPX(LinkConst.Ports.linkMotorRight);

        //moterの設定
        linkMotorLeft.configFactoryDefault();
        linkMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        linkMotorRight.configFactoryDefault();
        linkMotorRight.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        linkMotorLeft.setInverted(true);

        //SoftLimit
        linkMotorLeft.configForwardSoftLimitThreshold(LinkConst.LinkSoftLimit.ForwardSoftLimit);
        linkMotorLeft.configForwardSoftLimitEnable(true);
        linkMotorLeft.configReverseSoftLimitThreshold(LinkConst.LinkSoftLimit.ReverseSoftLimit);
        linkMotorLeft.configReverseSoftLimitEnable(true);
        linkMotorLeft.configPeakOutputForward(LinkConst.LinkSoftLimit.PeakOutputForward);
        linkMotorLeft.configPeakOutputReverse(LinkConst.LinkSoftLimit.PeakOutputReverse);

        linkMotorRight.configForwardSoftLimitThreshold(LinkConst.LinkSoftLimit.ForwardSoftLimit);
        linkMotorRight.configForwardSoftLimitEnable(true);
        linkMotorRight.configReverseSoftLimitThreshold(LinkConst.LinkSoftLimit.ReverseSoftLimit);
        linkMotorRight.configReverseSoftLimitEnable(true);
        linkMotorRight.configPeakOutputForward(LinkConst.LinkSoftLimit.PeakOutputForward);
        linkMotorRight.configPeakOutputReverse(LinkConst.LinkSoftLimit.PeakOutputReverse);

        //IdleMode設定
        linkMotorLeft.setNeutralMode(NeutralMode.Brake);
        linkMotorRight.setNeutralMode(NeutralMode.Brake);

        //PID
        linkMotorLeft.config_kP(0, PID.LinkP);
        linkMotorRight.config_kP(0, PID.LinkP);
    }
    @Override
    public void MoveShooterToSpecifiedAngle(double TargetShooterAngle) {
        linkMotorLeft.set(ControlMode.Position, TargetShooterAngle);
    }

    @Override
    public void readSensors() {
        //Smartdashbordはここ！
        linkMotorLeft.getSelectedSensorPosition();
        SmartDashboard.putNumber("linkMotorLeft position ", linkMotorLeft.getSelectedSensorPosition());
    }
    @Override
    public void KeepCurrentAngle() {
        if(linkMotorLeft.getSelectedSensorPosition() <= -400) {
            linkMotorLeft.set(ControlMode.PercentOutput, 0.1);
        } 
        //SmartDashboard.putNumber("linkMotorLeft position ", linkMotorLeft.getSelectedSensorPosition());

    }
    public void test() {}
}