package frc.robot.components.link.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.link.LinkConst;
import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
import frc.robot.components.link.LinkParameter;
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

        //motorの設定
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
    public void KeepCurrentAngle() {
        if (linkMotorLeft.getSelectedSensorPosition() <= LinkParameter.Angles.KeepCurrentAngleLinkLeft && linkMotorRight.getSelectedSensorPosition() <= LinkParameter.Angles.KeepCurrentAngleLinkRight) {
            linkMotorLeft.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
            linkMotorRight.set(ControlMode.PercentOutput, LinkParameter.Percent.KeepCurrentAngleLink);
        } else {
            linkMotorLeft.set(ControlMode.PercentOutput, 0);
            linkMotorRight.set(ControlMode.PercentOutput, 0);
        }
    }

    @Override
    public void readSensors() {
        //SmartDashboard.putNumber("linkMotorLeft position", linkMotorLeft.getSelectedSensorPosition());
        LinkMeasuredState.linkLeftAngle = linkMotorLeft.getSelectedSensorPosition();
        LinkMeasuredState.linkRightAngle = linkMotorRight.getSelectedSensorPosition();
        // 初期化
        LinkMeasuredState.linkAmpsHeight = false;
        LinkMeasuredState.linkAmpHeight = false;
        LinkMeasuredState.linkClimbHeight = false;
        LinkMeasuredState.linkSpeakerHeight = false;
        LinkMeasuredState.linkUnderStageHeight = false;

        // 条件に応じてboolean変数の値を更新
        SmartDashboard.putBoolean("Amp", LinkMeasuredState.linkAmpsHeight);
        if (LinkMeasuredState.linkLeftAngle <= -250 && LinkMeasuredState.linkLeftAngle >= -260) {
            LinkMeasuredState.linkUnderStageHeight = false;
            LinkMeasuredState.linkSpeakerSideHeight = false;
            // 条件に応じてboolean変数の値を更新
            if (LinkMeasuredState.linkLeftAngle <= Angles.AmpLinkLeft + 5 && Angles.AmpLinkLeft >= LinkLeftSoftLimit.ForwardSoftLimit - 5) {
                LinkMeasuredState.linkAmpHeight = true;
                LinkMeasuredState.linkClimbHeight = true;
            } else if (LinkMeasuredState.linkLeftAngle <= Angles.SpeakerPodiumLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerPodiumLinkLeft - 5) {
                LinkMeasuredState.linkPodiumHeight = true;
            } else if (LinkMeasuredState.linkLeftAngle <= Angles.IntakeLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.IntakeLinkLeft - 5) {
                LinkMeasuredState.linkIntakeHeight = true;
            } else if (LinkMeasuredState.linkLeftAngle <= Angles.StageLinkLeft) {
                LinkMeasuredState.linkUnderStageHeight = true;
            } else if (LinkMeasuredState.linkLeftAngle <= Angles.SpeakerSideLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerSideLinkLeft - 5) {
                LinkMeasuredState.linkSpeakerHeight = true;
            } else if (LinkMeasuredState.linkLeftAngle <= Angles.SpeakerSideLinkLeft + 5 && LinkMeasuredState.linkLeftAngle >= Angles.SpeakerSideLinkLeft - 5) {
                LinkMeasuredState.linkSpeakerSideHeight = true;
            }

            SmartDashboard.putNumber("LinkLeftAngle", LinkMeasuredState.linkLeftAngle);
            SmartDashboard.putNumber("linkRightAngle", LinkMeasuredState.linkRightAngle);
            SmartDashboard.putBoolean("Amp", LinkMeasuredState.linkAmpHeight);
            SmartDashboard.putBoolean("SpeakerBelow", LinkMeasuredState.linkSpeakerHeight);
            SmartDashboard.putBoolean("Climb", LinkMeasuredState.linkClimbHeight);
            SmartDashboard.putBoolean("UnderStage", LinkMeasuredState.linkUnderStageHeight);
            SmartDashboard.putBoolean("Intake", LinkMeasuredState.linkIntakeHeight);
            SmartDashboard.putBoolean("Podium", LinkMeasuredState.linkPodiumHeight);
            SmartDashboard.putBoolean("SpeakerSide", LinkMeasuredState.linkSpeakerSideHeight);

            SmartDashboard.putNumber("linkMotorLeftOutputPercent", linkMotorLeft.getMotorOutputPercent());
            SmartDashboard.putNumber("linkMotorRightOutputPercent", linkMotorRight.getMotorOutputPercent());
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
        //CheckConst
        linkMotorLeft.set(ControlMode.PercentOutput, upOrDown);
        linkMotorRight.set(ControlMode.PercentOutput, upOrDown);
    }
}