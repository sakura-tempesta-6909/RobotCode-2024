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
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.AmpLinkLeft, LinkParameter.Angles.AmpLinkRight);
                break;
            case s_speakerShootBelow:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerBelowLinkLeft, LinkParameter.Angles.SpeakerBelowLinkRight);
                break;
            case s_speakerShootPodium:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerPodiumLinkLeft, LinkParameter.Angles.SpeakerBelowLinkRight);
                break;
            case s_speakerShootSide:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerSideLinkLeft, LinkParameter.Angles.SpeakerSideLinkRight);                
                break;
            case s_intakeNote:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.IntakeLinkLeft, LinkParameter.Angles.IntakeLinkRight);
                break;
            case s_climbAngle:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.ClimbLinkLeft, LinkParameter.Angles.ClimbLinkRight);
                break;
            case s_keepCurrentAngle:
                repository.KeepCurrentAngle();
                break;
            case s_climb:
                repository.MoveShooterClimb();
                break;
            case s_climbUpFineAdjustment:
                repository.MoveShooterFineAdjustment(LinkConst.FineAdjustment.upAdjustment);
                break;
            case s_climbDownFineAdjustment:
                repository.MoveShooterFineAdjustment(LinkConst.FineAdjustment.downAdjustment);
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
