package frc.robot.components.link.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.Angle;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.link.LinkConst;
import frc.robot.components.link.LinkParameter;
import frc.robot.components.link.LinkParameter.PID;
import frc.robot.domain.measure.LinkMeasuredState;
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
        //Sensorの値を逆にしている
        linkMotorLeft.setSensorPhase(true);
        linkMotorLeft.setInverted(false);

        linkMotorRight.configFactoryDefault();
        linkMotorRight.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        //linkMotorRight.setSensorPhase(false);
        //linkMotorRight.setInverted(true);
        //linkMotorRight.follow(linkMotorLeft);
        //linkMotorRight.follow(linkMotorLeft, FollowerType.PercentOutput);

        //SoftLimit
        linkMotorLeft.configForwardSoftLimitThreshold(LinkConst.LinkLeftSoftLimit.ForwardSoftLimit);
        linkMotorLeft.configForwardSoftLimitEnable(true);
        linkMotorLeft.configReverseSoftLimitThreshold(LinkConst.LinkLeftSoftLimit.ReverseSoftLimit);
        linkMotorLeft.configReverseSoftLimitEnable(true);
        linkMotorLeft.configPeakOutputForward(LinkConst.LinkLeftSoftLimit.PeakOutputForward);
        linkMotorLeft.configPeakOutputReverse(LinkConst.LinkLeftSoftLimit.PeakOutputReverse);

        // linkMotorRight.configForwardSoftLimitThreshold(LinkConst.LinkSoftLimit.ForwardSoftLimit);
        // linkMotorRight.configForwardSoftLimitEnable(true);
        // linkMotorRight.configReverseSoftLimitThreshold(LinkConst.LinkSoftLimit.ReverseSoftLimit);
        // linkMotorRight.configReverseSoftLimitEnable(true);
        // linkMotorRight.configPeakOutputForward(LinkConst.LinkSoftLimit.PeakOutputForward);
        // linkMotorRight.configPeakOutputReverse(LinkConst.LinkSoftLimit.PeakOutputReverse);

        //IdleMode設定
        linkMotorLeft.setNeutralMode(NeutralMode.Brake);
        linkMotorRight.setNeutralMode(NeutralMode.Brake);

        //PID
        linkMotorLeft.config_kP(0, PID.LinkP);
    }
    @Override
    public void MoveShooterToSpecifiedAngle(double TargetShooterAngle) {
        linkMotorLeft.set(ControlMode.Position, TargetShooterAngle);
        linkMotorRight.set(ControlMode.Position, TargetShooterAngle);
    }

    @Override
    public void readSensors() {
        LinkMeasuredState.linkLeftAngle = linkMotorLeft.getSelectedSensorPosition();
        SmartDashboard.putNumber("LinkLeftAngle", LinkMeasuredState.linkLeftAngle);
        double linkRightAngle = linkMotorRight.getSelectedSensorPosition();
        SmartDashboard.putNumber("linkRightAngle", linkRightAngle);
        // 初期化
        LinkMeasuredState.linkAmpHeight = false;
        LinkMeasuredState.linkClimbHeight = false;
        LinkMeasuredState.linkSpeakerHeight = false;
        LinkMeasuredState.linkUnderStage = false;
        // 条件に応じてboolean変数の値を更新
        if (LinkMeasuredState.linkLeftAngle <= -250 && LinkMeasuredState.linkLeftAngle >= -260) {
          LinkMeasuredState.linkAmpHeight = true;
          LinkMeasuredState.linkClimbHeight = true;
        } else if (LinkMeasuredState.linkLeftAngle <= -405 && LinkMeasuredState.linkLeftAngle >= -395) {
          LinkMeasuredState.linkOverPodium = true;
        } else if (LinkMeasuredState.linkLeftAngle <= -405 && LinkMeasuredState.linkLeftAngle >= -395) {  
          LinkMeasuredState.linkSourceHeight = true;
        } else if (LinkMeasuredState.linkLeftAngle <= -485) {
          LinkMeasuredState.linkUnderStage = true;
        } else if (LinkMeasuredState.linkLeftAngle <= -295 && LinkMeasuredState.linkLeftAngle >= -305) {
          LinkMeasuredState.linkSpeakerHeight = true;
        }
        SmartDashboard.putBoolean("Amp", LinkMeasuredState.linkAmpHeight);
        SmartDashboard.putBoolean("Speaker", LinkMeasuredState.linkSpeakerHeight);
        SmartDashboard.putBoolean("Climb", LinkMeasuredState.linkClimbHeight);
        SmartDashboard.putBoolean("Stage", LinkMeasuredState.linkUnderStage);
        SmartDashboard.putBoolean("Source", LinkMeasuredState.linkSourceHeight);
        SmartDashboard.putBoolean("Podium", LinkMeasuredState.linkOverPodium);
    }

    @Override
    public void KeepCurrentAngle() {
        if(linkMotorLeft.getSelectedSensorPosition() <= -400) {
            linkMotorLeft.set(ControlMode.PercentOutput, 0.1);
        } else {
            linkMotorLeft.set(ControlMode.PercentOutput, 0);
        }

    }

    @Override 
    public void MoveShooterClimb() {
    }

    @Override
    public void MoveShooterFineAdjustment() {
    }

    public void test1() {
        linkMotorRight.set(ControlMode.PercentOutput, 0.2);
    }
    public void test2() {
        linkMotorRight.set(ControlMode.PercentOutput, -0.2);
    }
}