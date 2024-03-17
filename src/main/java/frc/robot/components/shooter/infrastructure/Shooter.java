package frc.robot.components.shooter.infrastructure;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.shooter.ShooterConst;
import frc.robot.components.shooter.ShooterParameter;
import frc.robot.domain.repository.ShooterRepository;

public class Shooter implements ShooterRepository {
    final CANSparkMax noteUpperShooter, noteLowerShooter, notePusher;
    final SparkPIDController noteUpperShooterPID, noteLowerShooterPID;
    public Shooter() {
        noteUpperShooter = new CANSparkMax(ShooterConst.Ports.NoteUpperShooter, CANSparkLowLevel.MotorType.kBrushless);
        noteLowerShooter = new CANSparkMax(ShooterConst.Ports.NoteLowerShooter, CANSparkLowLevel.MotorType.kBrushless);
        notePusher = new CANSparkMax(ShooterConst.Ports.NotePusher, CANSparkLowLevel.MotorType.kBrushless);
        noteUpperShooter.restoreFactoryDefaults();
        noteLowerShooter.restoreFactoryDefaults();
        noteUpperShooter.setInverted(true);
        noteLowerShooter.setInverted(true);
        noteUpperShooterPID = noteUpperShooter.getPIDController();
        noteLowerShooterPID = noteLowerShooter.getPIDController();

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
        noteUpperShooter.set(ShooterParameter.Speed.ShooterIntakeSpeed);
        noteLowerShooter.set(ShooterParameter.Speed.ShooterIntakeSpeed);
        notePusher.set(ShooterParameter.Speed.PusherSpeed);

    }

    @Override
    public void noteShootSpeaker() {
        noteUpperShooterPID.setReference(ShooterParameter.Speed.ShooterTargetSpeed, CANSparkBase.ControlType.kVelocity);
        noteLowerShooterPID.setReference(ShooterParameter.Speed.ShooterTargetSpeed, CANSparkBase.ControlType.kVelocity);
    }

    @Override
    public void noteShootAmp() {
        noteUpperShooter.set(ShooterParameter.Speed.ShooterIntakeSpeed);
        noteLowerShooter.set(-ShooterParameter.Speed.ShooterIntakeSpeed);
        notePusher.set(ShooterParameter.Speed.PusherSpeed);
    }

    @Override
    public void noteOuttake() {
        noteUpperShooter.set(-ShooterParameter.Speed.ShooterIntakeSpeed);
        noteLowerShooter.set(-ShooterParameter.Speed.ShooterIntakeSpeed);
        notePusher.set(-ShooterParameter.Speed.PusherSpeed);
    }

    @Override
    public void readSensors() {

    }
    @Override
    public void stopIntake() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopIntake'");
    }
}
