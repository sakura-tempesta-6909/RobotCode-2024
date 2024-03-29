package frc.robot.components.link.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.link.LinkConst;
import frc.robot.components.link.LinkParameter;
import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
import frc.robot.components.link.LinkConst.LinkRightSoftLimit;
import frc.robot.components.link.LinkParameter.Angles;
import frc.robot.components.link.LinkParameter.PID;
import frc.robot.domain.measure.LinkMeasuredState;
import frc.robot.domain.repository.LinkRepository;

public class Link implements LinkRepository {
    //Linkの持ち物検査
    final TalonSRX linkMotorLeft, linkMotorRight;

    public Link() {
        LinkParameter.ConstInit();

        //属性の初期化
        linkMotorLeft = new TalonSRX(LinkConst.Ports.linkMotorLeft);
        linkMotorRight = new TalonSRX(LinkConst.Ports.linkMotorRight);

        //moterの設定
        linkMotorLeft.configFactoryDefault();
        linkMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        //Sensorの値を逆にしている
        linkMotorLeft.setSensorPhase(true);
        linkMotorLeft.setInverted(false);

        linkMotorRight.configFactoryDefault();
        linkMotorRight.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        linkMotorRight.setSensorPhase(false);
        linkMotorRight.setInverted(true);
        //linkMotorRight.follow(linkMotorLeft);
        //linkMotorRight.follow(linkMotorLeft, FollowerType.PercentOutput);

        //SoftLimit
        linkMotorLeft.configForwardSoftLimitThreshold(LinkConst.LinkLeftSoftLimit.ForwardSoftLimit);
        linkMotorLeft.configForwardSoftLimitEnable(true);
        linkMotorLeft.configReverseSoftLimitThreshold(LinkConst.LinkLeftSoftLimit.ReverseSoftLimit);
        linkMotorLeft.configReverseSoftLimitEnable(true);
        linkMotorLeft.configPeakOutputForward(LinkConst.LinkLeftSoftLimit.PeakOutputForward);
        linkMotorLeft.configPeakOutputReverse(LinkConst.LinkLeftSoftLimit.PeakOutputReverse);

        linkMotorRight.configForwardSoftLimitThreshold(LinkConst.LinkRightSoftLimit.ForwardSoftLimit);
        linkMotorRight.configForwardSoftLimitEnable(true);
        linkMotorRight.configReverseSoftLimitThreshold(LinkConst.LinkRightSoftLimit.ReverseSoftLimit);
        linkMotorRight.configReverseSoftLimitEnable(true);
        linkMotorRight.configPeakOutputForward(LinkConst.LinkRightSoftLimit.PeakOutputForward);
        linkMotorRight.configPeakOutputReverse(LinkConst.LinkRightSoftLimit.PeakOutputReverse);

        //IdleMode設定
        linkMotorLeft.setNeutralMode(NeutralMode.Brake);
        linkMotorRight.setNeutralMode(NeutralMode.Brake);

        //PID
        linkMotorLeft.config_kP(0, PID.LinkP);
        linkMotorLeft.config_kI(0, PID.LinkI);
        linkMotorLeft.config_kD(0, PID.LinkD);

        linkMotorRight.config_kP(0, PID.LinkP);
        linkMotorRight.config_kI(0, PID.LinkI);
        linkMotorRight.config_kD(0, PID.LinkD);
    }
    @Override
    public void MoveShooterToSpecifiedAngle(double TargetShooterLeftAngle, double TargetShooterRightAngle) {
        linkMotorLeft.set(ControlMode.Position, TargetShooterLeftAngle);
        linkMotorRight.set(ControlMode.Position, TargetShooterRightAngle);
    }

    @Override
    public void readSensors() {
        double linkAngle = linkMotorLeft.getSelectedSensorPosition();
        //SmartDashboard.putNumber("linkMotorLeft position", linkMotorLeft.getSelectedSensorPosition());
        LinkMeasuredState.linkLeftAngle = linkMotorLeft.getSelectedSensorPosition();
        SmartDashboard.putNumber("LinkLeftAngle", LinkMeasuredState.linkLeftAngle);
        LinkMeasuredState.linkRightAngle = linkMotorRight.getSelectedSensorPosition();
        SmartDashboard.putNumber("linkRightAngle", LinkMeasuredState.linkRightAngle);
        // 初期化
        LinkMeasuredState.linkAmpsHeight = false;
        LinkMeasuredState.linkAmpHeight = false;
        LinkMeasuredState.linkClimbHeight = false;
        LinkMeasuredState.linkSpeakerHeight = false;
        LinkMeasuredState.linkUnderStage = false;
        // 条件に応じてboolean変数の値を更新
        if (LinkMeasuredState.linkLeftAngle <= Angles.AmpLinkLeft + 5 && Angles.AmpLinkLeft >= LinkLeftSoftLimit.ForwardSoftLimit - 5) {
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

        SmartDashboard.putNumber("linkMotorLeftOutputPersent", linkMotorLeft.getMotorOutputPercent());
        SmartDashboard.putNumber("linkMotorRightOutputPersent", linkMotorRight.getMotorOutputPercent());
    }
    @Override
    public void KeepCurrentAngle() {
        if(linkMotorLeft.getSelectedSensorPosition() <= LinkLeftSoftLimit.ReverseSoftLimit + 40 && linkMotorRight.getSelectedSensorPosition() <= LinkRightSoftLimit.ReverseSoftLimit + 40) {
            linkMotorLeft.set(ControlMode.PercentOutput, 0.05);
            linkMotorRight.set(ControlMode.PercentOutput, 0.05);
        } else {
            linkMotorLeft.set(ControlMode.PercentOutput, 0);
            linkMotorRight.set(ControlMode.PercentOutput, 0);
        }

    }

    @Override 
    public void MoveShooterClimb() {
      linkMotorLeft.set(ControlMode.PercentOutput, LinkParameter.Angles.ClimbLinkLeft);
      linkMotorRight.set(ControlMode.PercentOutput, LinkParameter.Angles.ClimbLinkRight);
    }

    @Override
    public void MoveShooterFineAdjustment(double upOrDown) {
      //Climb時の微調整用
      //CheakConst
      linkMotorLeft.set(ControlMode.PercentOutput, upOrDown);
      linkMotorRight.set(ControlMode.PercentOutput, upOrDown);
    }

    // @Override
    // public void MoveShooterDownFineAdjustment() {
    //   //Climb時の微調整用(↓)
    //   linkMotorLeft.set(ControlMode.PercentOutput, -0.1);
    //   linkMotorRight.set(ControlMode.PercentOutput, -0.1);
    // }

    public void test1() {
      linkMotorLeft.set(ControlMode.Position, LinkLeftSoftLimit.ForwardSoftLimit);
      linkMotorRight.set(ControlMode.Position, LinkRightSoftLimit.ForwardSoftLimit);
    }
    public void test2() {
      linkMotorLeft.set(ControlMode.Position, LinkLeftSoftLimit.ReverseSoftLimit);
      linkMotorRight.set(ControlMode.Position,  LinkRightSoftLimit.ReverseSoftLimit);
    }
}