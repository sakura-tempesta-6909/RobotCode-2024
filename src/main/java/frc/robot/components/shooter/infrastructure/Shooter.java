package frc.robot.components.shooter.infrastructure;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.shooter.ShooterConst;
import frc.robot.components.shooter.ShooterParameter;
import frc.robot.components.shooter.ShooterParameter.ShootingMotor;
import frc.robot.domain.measure.ShooterMeasuredState;
import frc.robot.domain.repository.ShooterRepository;

public class Shooter implements ShooterRepository {

    final CANSparkMax noteUpperShooter, noteLowerShooter, notePusher;
    final SparkPIDController noteUpperShooterPID, noteLowerShooterPID;
    final DigitalInput noteDirectionSensor;
    final RelativeEncoder upperShooterEncoder, lowerShooterEncoder;

    public Shooter() {
        noteUpperShooter = new CANSparkMax(ShooterConst.Ports.NoteUpperShooter, CANSparkLowLevel.MotorType.kBrushless);
        noteLowerShooter = new CANSparkMax(ShooterConst.Ports.NoteLowerShooter, CANSparkLowLevel.MotorType.kBrushless);
        notePusher = new CANSparkMax(ShooterConst.Ports.NotePusher, CANSparkLowLevel.MotorType.kBrushless);
        noteUpperShooter.restoreFactoryDefaults();
        noteLowerShooter.restoreFactoryDefaults();
        noteUpperShooter.setInverted(true);
        noteLowerShooter.setInverted(true);
        notePusher.setInverted(true);
        noteUpperShooterPID = noteUpperShooter.getPIDController();
        noteLowerShooterPID = noteLowerShooter.getPIDController();
        noteDirectionSensor = new DigitalInput(ShooterConst.Ports.NoteDirectionSensor);
        upperShooterEncoder = noteUpperShooter.getEncoder();
        lowerShooterEncoder = noteLowerShooter.getEncoder();
        

        noteUpperShooterPID.setP(ShooterParameter.PID.ShooterP);
        noteUpperShooterPID.setI(ShooterParameter.PID.ShooterI);
        noteUpperShooterPID.setD(ShooterParameter.PID.ShooterD);
        noteLowerShooterPID.setP(ShooterParameter.PID.ShooterP);
        noteLowerShooterPID.setI(ShooterParameter.PID.ShooterI);
        noteLowerShooterPID.setD(ShooterParameter.PID.ShooterD);

        ShooterParameter.ConstInit();
    }
    @Override
    public void noteIntake() {
        noteUpperShooter.set(-ShooterParameter.Speed.ShooterIntakeSpeed);
        noteLowerShooter.set(-ShooterParameter.Speed.ShooterIntakeSpeed);
        notePusher.set(-ShooterParameter.Speed.PusherIntakeSpeed);
        if (ShooterMeasuredState.isNoteGet) {
            noteUpperShooter.set(ShooterParameter.Speed.Neutral);
            noteLowerShooter.set(ShooterParameter.Speed.Neutral);
            notePusher.set(ShooterParameter.Speed.Neutral);
        }

    }

    @Override
    public void noteShootSpeaker() {
        noteUpperShooterPID.setReference(ShooterParameter.Speed.ShooterTargetSpeed, CANSparkBase.ControlType.kVelocity);
        noteLowerShooterPID.setReference(ShooterParameter.Speed.ShooterTargetSpeed, CANSparkBase.ControlType.kVelocity);
        if(ShooterMeasuredState.readyToShoot) {
            notePusher.set(ShooterParameter.Speed.PusherShootSpeed);
        }
        else {
            notePusher.set(ShooterParameter.Speed.Neutral);
        }
    }
    

    @Override
    public void noteShootAmp() {
        noteUpperShooter.set(ShooterParameter.Speed.AmpShooterSpeed);
        noteLowerShooter.set(-ShooterParameter.Speed.AmpShooterSpeed);
        notePusher.set(ShooterParameter.Speed.PusherShootSpeed);
    }

    @Override
    public void noteOuttake() {
        noteUpperShooter.set(ShooterParameter.Speed.ShooterIntakeSpeed);
        noteLowerShooter.set(ShooterParameter.Speed.ShooterIntakeSpeed);
        notePusher.set(ShooterParameter.Speed.PusherOuttakeSpeed);
    }

    @Override
    public void readSensors() {
        ShooterMeasuredState.shooterUpperSpeed = upperShooterEncoder.getVelocity();
        ShooterMeasuredState.shooterLowerSpeed = lowerShooterEncoder.getVelocity();

        ShooterMeasuredState.isNoteGet = !noteDirectionSensor.get();
        SmartDashboard.putNumber("noteUpperShooter", upperShooterEncoder.getVelocity());
        SmartDashboard.putNumber("noteLowerShooter", lowerShooterEncoder.getVelocity());
        SmartDashboard.putNumber("diff", upperShooterEncoder.getVelocity()-lowerShooterEncoder.getVelocity());

        ShooterMeasuredState.readyToShoot = ShooterMeasuredState.shooterUpperSpeed > ShootingMotor.shootAvailableSpeedUpper 
        && ShooterMeasuredState.shooterLowerSpeed > ShootingMotor.shootAvailableSpeedLower 
        && Math.abs(ShooterMeasuredState.shooterLowerSpeed - ShooterMeasuredState.shooterUpperSpeed) < ShootingMotor.shootAvailableAbsolute;

        SmartDashboard.putBoolean("ready to shoot", ShooterMeasuredState.readyToShoot);
    }
    @Override
    public void stopIntake() {
      noteUpperShooter.set(ShooterParameter.Speed.Neutral);
      noteLowerShooter.set(ShooterParameter.Speed.Neutral);
      notePusher.set(ShooterParameter.Speed.Neutral);
    }
    @Override
    public void increaseRotation() {
        noteUpperShooterPID.setReference(ShooterParameter.Speed.ShooterTargetSpeed, CANSparkBase.ControlType.kVelocity);
        noteLowerShooterPID.setReference(ShooterParameter.Speed.ShooterTargetSpeed, CANSparkBase.ControlType.kVelocity);
    }
}
