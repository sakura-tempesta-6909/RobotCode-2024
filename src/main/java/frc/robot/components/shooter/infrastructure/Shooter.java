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
    public void noteIntake(double shooterSpeed, double pusherSpeed) {
        noteUpperShooter.set(shooterSpeed);
        noteLowerShooter.set(shooterSpeed);
        notePusher.set(pusherSpeed);

    }

    @Override
    public void noteShootSpeaker(double targetSpeed) {
        noteUpperShooterPID.setReference(targetSpeed, CANSparkBase.ControlType.kVelocity);
        noteLowerShooterPID.setReference(targetSpeed, CANSparkBase.ControlType.kVelocity);
    }

    @Override
    public void noteShootAmp(double shooterSpeed, double pusherSpeed) {
        noteUpperShooter.set(shooterSpeed);
        noteLowerShooter.set(-shooterSpeed);
        notePusher.set(pusherSpeed);
    }

    @Override
    public void noteOuttake(double shooterSpeed, double pusherSpeed) {
        noteUpperShooter.set(shooterSpeed);
        noteLowerShooter.set(shooterSpeed);
        notePusher.set(pusherSpeed);
    }

    @Override
    public void readSensors() {

    }
}
