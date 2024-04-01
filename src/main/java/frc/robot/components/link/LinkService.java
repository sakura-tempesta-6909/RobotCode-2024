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
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.Amp);
                break;
            case s_speakerShootBelow:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerBelow);
                break;
            case s_speakerShootPodium:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerPodium);
                break;
            case s_speakerShootSide:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerRight);
                break;
            case s_intakeNote:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.Intake);
                break;
            case s_climb:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.Climb);
                break;
            case s_keepCurrentAngle:
                repository.KeepCurrentAngle();
                break;
            case s_stageAngle:
                break;
            default:
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
