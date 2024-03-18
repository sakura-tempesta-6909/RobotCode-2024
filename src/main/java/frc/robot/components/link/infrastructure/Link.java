package frc.robot.components.link.infrastructure;

import frc.robot.components.link.LinkParameter;
import frc.robot.domain.repository.LinkRepository;

public class Link implements LinkRepository {
    public Link() {
        LinkParameter.ConstInit();
    }
    @Override
    public void MoveShooterToSpecifiedAngle(double TargetShooterAngle) {

    }

    @Override
    public void readSensors() {

    }
    @Override
    public void KeepCurrentAngle() {
        
    }
}
