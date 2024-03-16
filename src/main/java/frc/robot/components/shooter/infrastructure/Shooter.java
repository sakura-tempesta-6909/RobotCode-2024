package frc.robot.components.shooter.infrastructure;

import frc.robot.components.shooter.ShooterParameter;
import frc.robot.domain.repository.ShooterRepository;

public class Shooter implements ShooterRepository {
    public Shooter() {
        ShooterParameter.ConstInit();
    }
    @Override
    public void noteIntake() {

    }

    @Override
    public void noteShootSpeaker() {

    }

    @Override
    public void noteShootAmp() {

    }

    @Override
    public void noteOuttake() {

    }

    @Override
    public void readSensors() {

    }
}
