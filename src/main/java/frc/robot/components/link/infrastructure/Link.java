package frc.robot.components.link.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
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
    // Linkの持ち物検査
    final TalonSRX linkMotorLeft, linkMotorRight;

    public Link() {
        LinkParameter.ConstInit();

        // 属性の初期化
        linkMotorLeft = new TalonSRX(LinkConst.Ports.linkMotorLeft);
        linkMotorRight = new TalonSRX(LinkConst.Ports.linkMotorRight);

        // motorの初期化
        linkMotorLeft.configFactoryDefault();
        linkMotorRight.configFactoryDefault();
        // Sensorの種類
        linkMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        linkMotorRight.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        // Sensorの値を(false)で逆にしている
        linkMotorLeft.setSensorPhase(true);
        linkMotorRight.setSensorPhase(true);
        // motorの向きを(true)で逆にしている
        linkMotorLeft.setInverted(false);
        linkMotorRight.setInverted(true);

        //linkMotorRight.follow(linkMotorLeft);
        //linkMotorRight.follow(linkMotorLeft, FollowerType.PercentOutput);

        //SoftLimitの設定
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

        // IdleMode(motorのモード)設定
        linkMotorLeft.setNeutralMode(NeutralMode.Brake);
        linkMotorRight.setNeutralMode(NeutralMode.Brake);

        // PIDの設定
        linkMotorLeft.config_kP(0, PID.UpLinkP);
        linkMotorLeft.config_kI(0, PID.UpLinkI);
        linkMotorLeft.config_kD(0, PID.UpLinkD);
        linkMotorLeft.config_kP(1, PID.DownLinkP);
        linkMotorLeft.config_kI(1, PID.DownLinkI);
        linkMotorLeft.config_kP(2, PID.ClimbLinkP);
        linkMotorLeft.config_kI(2, PID.ClimbLinkI); 

        linkMotorRight.config_kP(0, PID.UpLinkP);
        linkMotorRight.config_kI(0, PID.UpLinkI);
        linkMotorRight.config_kD(0, PID.UpLinkD);
        linkMotorRight.config_kP(1, PID.DownLinkP);
        linkMotorRight.config_kI(1, PID.DownLinkI);
        linkMotorRight.config_kP(2, PID.ClimbLinkP);
        linkMotorRight.config_kI(2, PID.ClimbLinkI); 

        linkMotorLeft.config_IntegralZone(0, 50);
        linkMotorRight.config_IntegralZone(0, 50);
    }

    @Override
    public void MoveShooterToSpecifiedAngle(double TargetShooterLeftAngle, double TargetShooterRightAngle) {
      // Linkを上げるときと下げるときでのPIDのslot切り替え
      if(TargetShooterLeftAngle >= LinkParameter.Angles.SpeakerSecondPodiumLinkLeft && TargetShooterRightAngle >= LinkParameter.Angles.SpeakerSecondPodiumLinkRight) {
        linkMotorLeft.selectProfileSlot(0, 0);
        linkMotorRight.selectProfileSlot(0, 0);
      } else {
        linkMotorLeft.selectProfileSlot(1, 0);
        linkMotorRight.selectProfileSlot(1, 0);
      }
      // LinkをsetpointまでPIDで動かすようにする
      linkMotorLeft.set(ControlMode.Position, TargetShooterLeftAngle, DemandType.ArbitraryFeedForward, 0.05);
      linkMotorRight.set(ControlMode.Position, TargetShooterRightAngle, DemandType.ArbitraryFeedForward, 0.05);
    }
    
    @Override
    public void KeepCurrentAngle() {
      // Linkの角度が重さで落ちないようしている
      if(linkMotorLeft.getSelectedSensorPosition() <= LinkParameter.Angles.KeepCurrentAngleLinkLeft && linkMotorRight.getSelectedSensorPosition() <= LinkParameter.Angles.KeepCurrentAngleLinkRight) {
        linkMotorLeft.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
        linkMotorRight.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
      } else {
        linkMotorLeft.set(ControlMode.PercentOutput, 0);
        linkMotorRight.set(ControlMode.PercentOutput, 0);
      }

    }

    @Override 
    public void MoveShooterClimb(double climbSpeed) {
      linkMotorLeft.set(ControlMode.PercentOutput, climbSpeed);
      linkMotorRight.set(ControlMode.PercentOutput, climbSpeed);
    }

    @Override
    public void MoveShooterFineAdjustment(double upOrDown) {
      //Climb時の微調整用
      //CheakConst
      linkMotorLeft.set(ControlMode.PercentOutput, upOrDown);
      linkMotorRight.set(ControlMode.PercentOutput, upOrDown);
    }

    @Override
    public void disableSoftLimit() {
      linkMotorLeft.configForwardSoftLimitEnable(false);
      linkMotorLeft.configReverseSoftLimitEnable(false);
      linkMotorRight.configForwardSoftLimitEnable(false);
      linkMotorRight.configReverseSoftLimitEnable(false);
    }

    @Override
    public void enableSoftLimit() {
      linkMotorLeft.configForwardSoftLimitEnable(true);
      linkMotorLeft.configReverseSoftLimitEnable(true);
      linkMotorRight.configForwardSoftLimitEnable(true);
      linkMotorRight.configReverseSoftLimitEnable(true);
    }

    @Override
    public void readSensors() {
        LinkMeasuredState.linkLeftAngle = linkMotorLeft.getSelectedSensorPosition() - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit;
        SmartDashboard.putNumber("LinkLeftAngle", LinkMeasuredState.linkLeftAngle);
        LinkMeasuredState.linkRightAngle = linkMotorRight.getSelectedSensorPosition() - LinkConst.LinkRightSoftLimit.ReverseSoftLimit;
        SmartDashboard.putNumber("linkRightAngle", LinkMeasuredState.linkRightAngle);

        
        // 初期化
        LinkMeasuredState.linkAmpHeight = false;
        LinkMeasuredState.linkClimbHeight = false;
        LinkMeasuredState.linkSpeakerBelowHeight = false;
        LinkMeasuredState.linkUnderStageHeight = false;
        LinkMeasuredState.linkSpeakerSecondPodiumHeight = false;
        LinkMeasuredState.linkPodiumHeight = false;
        // 条件に応じてboolean変数の値を更新
        if(LinkMeasuredState.linkLeftAngle <= Angles.AmpLinkLeft + 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit && LinkMeasuredState.linkLeftAngle >= LinkLeftSoftLimit.ForwardSoftLimit - 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit) {
          LinkMeasuredState.linkAmpHeight = true;
          LinkMeasuredState.linkClimbHeight = true;
        }
        if(LinkMeasuredState.linkLeftAngle <= Angles.SpeakerPodiumLinkLeft + 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerPodiumLinkLeft - 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit) {
          LinkMeasuredState.linkPodiumHeight = true;
        }
        if(LinkMeasuredState.linkLeftAngle <= Angles.IntakeLinkLeft + 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit && LinkMeasuredState.linkLeftAngle >= Angles.IntakeLinkLeft - 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit) {  
          LinkMeasuredState.linkIntakeHeight = true;
        }
        if(LinkMeasuredState.linkLeftAngle <= Angles.StageLinkLeft + 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit) {
          LinkMeasuredState.linkUnderStageHeight = true;
        }
        if(LinkMeasuredState.linkLeftAngle <= Angles.SpeakerBelowLinkLeft + 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerBelowLinkLeft - 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit) {
          LinkMeasuredState.linkSpeakerBelowHeight = true;
        }
        if(LinkMeasuredState.linkLeftAngle <= Angles.SpeakerSecondPodiumLinkLeft + 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerSecondPodiumLinkLeft - 5 - LinkConst.LinkLeftSoftLimit.ReverseSoftLimit) {
          LinkMeasuredState.linkSpeakerSecondPodiumHeight = true;
        }

        SmartDashboard.putBoolean("Amp", LinkMeasuredState.linkAmpHeight);
        SmartDashboard.putBoolean("SpeakerBelow", LinkMeasuredState.linkSpeakerBelowHeight);
        SmartDashboard.putBoolean("Climb", LinkMeasuredState.linkClimbHeight);
        SmartDashboard.putBoolean("UnderStage", LinkMeasuredState.linkUnderStageHeight);
        SmartDashboard.putBoolean("Intake", LinkMeasuredState.linkIntakeHeight);
        SmartDashboard.putBoolean("Podium", LinkMeasuredState.linkPodiumHeight);
        SmartDashboard.putBoolean("SecondPodium", LinkMeasuredState.linkSpeakerSecondPodiumHeight);

        linkMotorLeft.getIntegralAccumulator();
        SmartDashboard.putNumber("Accmulator", linkMotorLeft.getIntegralAccumulator());
    }

    public void test() {
    }

    @Override
    public void resetPID() {
      linkMotorLeft.setIntegralAccumulator(0);
      linkMotorRight.setIntegralAccumulator(0);
    }
}