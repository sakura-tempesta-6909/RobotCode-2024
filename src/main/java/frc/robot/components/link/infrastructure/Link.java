package frc.robot.components.link.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.link.LinkConst;
import frc.robot.components.link.LinkParameter;
import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
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
        linkMotorRight.setSensorPhase(true);
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
        linkMotorLeft.config_kP(0, PID.UpLinkP);
        linkMotorLeft.config_kI(0, PID.UpLinkI);
        linkMotorLeft.config_kD(0, PID.UpLinkD);
        linkMotorLeft.config_kP(1, PID.DownLinkP);
        linkMotorLeft.config_kI(1, PID.DownLinkI);
        linkMotorLeft.config_kD(1, PID.DownLinkD);

        linkMotorRight.config_kP(0, PID.UpLinkP);
        linkMotorRight.config_kI(0, PID.UpLinkI);
        linkMotorRight.config_kD(0, PID.UpLinkD);
        linkMotorRight.config_kP(1, PID.DownLinkP);
        linkMotorRight.config_kI(1, PID.DownLinkI);
        linkMotorRight.config_kD(1, PID.DownLinkD);
    }
    @Override
    public void MoveShooterToSpecifiedAngle(double TargetShooterLeftAngle, double TargetShooterRightAngle) {
      if(TargetShooterLeftAngle >= LinkParameter.Angles.SpeakerSecondPodiumLinkLeft && TargetShooterRightAngle >= LinkParameter.Angles.SpeakerSecondPodiumLinkRight) {
            SmartDashboard.putNumber("PiDslot", 0);
        linkMotorLeft.selectProfileSlot(0, 0);
        linkMotorRight.selectProfileSlot(0, 0);
      } else {
              SmartDashboard.putNumber("PiDslot", 1);
        linkMotorLeft.selectProfileSlot(1, 0);
        linkMotorRight.selectProfileSlot(1, 0);
      }

        linkMotorLeft.set(ControlMode.Position, TargetShooterLeftAngle);
        linkMotorRight.set(ControlMode.Position, TargetShooterRightAngle);
    }

    @Override
    public void readSensors() {
        LinkMeasuredState.linkLeftAngle = linkMotorLeft.getSelectedSensorPosition();
        SmartDashboard.putNumber("LinkLeftAngle", LinkMeasuredState.linkLeftAngle);
        LinkMeasuredState.linkRightAngle = linkMotorRight.getSelectedSensorPosition();
        SmartDashboard.putNumber("linkRightAngle", LinkMeasuredState.linkRightAngle);
        // 初期化
        LinkMeasuredState.linkAmpHeight = false;
        LinkMeasuredState.linkClimbHeight = false;
        LinkMeasuredState.linkSpeakerBelowHeight = false;
        LinkMeasuredState.linkUnderStageHeight = false;
        LinkMeasuredState.linkSpeakerSecondPodiumHeight = false;
        LinkMeasuredState.linkPodiumHeight = false;
        // 条件に応じてboolean変数の値を更新
        if(LinkMeasuredState.linkLeftAngle <= Angles.AmpLinkLeft + 5 && Angles.AmpLinkLeft >= LinkLeftSoftLimit.ForwardSoftLimit - 5) {
          LinkMeasuredState.linkAmpHeight = true;
          LinkMeasuredState.linkClimbHeight = true;
        }
        if(LinkMeasuredState.linkLeftAngle <= Angles.SpeakerPodiumLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerPodiumLinkLeft - 5) {
          LinkMeasuredState.linkPodiumHeight = true;
        } 
        if(LinkMeasuredState.linkLeftAngle <= Angles.IntakeLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.IntakeLinkLeft - 5) {  
          LinkMeasuredState.linkIntakeHeight = true;
        } 
        if(LinkMeasuredState.linkLeftAngle <= Angles.StageLinkLeft) {
          LinkMeasuredState.linkUnderStageHeight = true;
        } 
        if(LinkMeasuredState.linkLeftAngle <= Angles.SpeakerBelowLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerBelowLinkLeft - 5) {
          LinkMeasuredState.linkSpeakerBelowHeight = true;
        } 
        if(LinkMeasuredState.linkLeftAngle <= Angles.SpeakerSecondPodiumLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerSecondPodiumLinkLeft - 5) {
          LinkMeasuredState.linkSpeakerSecondPodiumHeight = true;
        }
        LinkMeasuredState.linkCurrent = linkMotorLeft.getStatorCurrent();
        SmartDashboard.putBoolean("Amp", LinkMeasuredState.linkAmpHeight);
        SmartDashboard.putBoolean("SpeakerBelow", LinkMeasuredState.linkSpeakerBelowHeight);
        SmartDashboard.putBoolean("Climb", LinkMeasuredState.linkClimbHeight);
        SmartDashboard.putBoolean("UnderStage", LinkMeasuredState.linkUnderStageHeight);
        SmartDashboard.putBoolean("Intake", LinkMeasuredState.linkIntakeHeight);
        SmartDashboard.putBoolean("Podium", LinkMeasuredState.linkPodiumHeight);
        SmartDashboard.putBoolean("SecondPodium", LinkMeasuredState.linkSpeakerSecondPodiumHeight);

        SmartDashboard.putNumber("linkMotorLeftOutputPersent", linkMotorLeft.getMotorOutputPercent());
        SmartDashboard.putNumber("linkMotorRightOutputPersent", linkMotorRight.getMotorOutputPercent());
    }
    @Override
    public void KeepCurrentAngle() {
        if(linkMotorLeft.getSelectedSensorPosition() <= LinkParameter.Angles.KeepCurrentAngleLinkLeft && linkMotorRight.getSelectedSensorPosition() <= LinkParameter.Angles.KeepCurrentAngleLinkRight) {
            linkMotorLeft.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
            linkMotorRight.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
        } else {
            linkMotorLeft.set(ControlMode.PercentOutput, 0);
            linkMotorRight.set(ControlMode.PercentOutput, 0);
        }

    }

    @Override 
    public void MoveShooterClimb() {
      linkMotorLeft.set(ControlMode.PercentOutput, LinkParameter.Percent.Climb);
      linkMotorRight.set(ControlMode.PercentOutput, LinkParameter.Percent.Climb);
    }

    @Override
    public void MoveShooterFineAdjustment(double upOrDown) {
      //Climb時の微調整用
      //CheakConst
      linkMotorLeft.set(ControlMode.PercentOutput, upOrDown);
      linkMotorRight.set(ControlMode.PercentOutput, upOrDown);
    }

    public void test() {
    }
}