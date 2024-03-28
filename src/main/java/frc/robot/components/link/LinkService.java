package frc.robot.components.link;

import frc.robot.components.Service;
import frc.robot.components.drive.DriveParameter;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.repository.LinkRepository;

public class LinkService implements Service {
    LinkRepository repository;

    public LinkService(LinkRepository repository) {
        this.repository = repository;
    }
    @Override
    public void applyModel() {
        switch (LinkModel.shooterAngleMode) {
            case s_ampShoot:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.LeftAngles.Amp);
                break;
            case s_speakerShootBelow:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.LeftAngles.SpeakerBelow);
                break;
            case s_speakerShootPodium:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.LeftAngles.SpeakerPodium);
                break;
            case s_speakerShootLeft:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.LeftAngles.SpeakerLeft);                
                break;
            case s_speakerShootRight:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.LeftAngles.SpeakerRight);
                break;
            case s_intakeNote:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.LeftAngles.Intake);
                break;
            case s_climb:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.LeftAngles.Climb);
                break;
            case s_inputnothing:
                repository.KeepCurrentAngle();
                break;
        }
    }


    @Override
    public void readSensors() {
        repository.readSensors();
    }

    @Override
    public void resetModel() {
        LinkModel.reset();
    }
}
