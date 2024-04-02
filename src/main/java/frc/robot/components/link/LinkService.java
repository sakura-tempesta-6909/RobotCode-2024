package frc.robot.components.link;

import frc.robot.components.Service;
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
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerPodiumLinkLeft, LinkParameter.Angles.SpeakerPodiumLinkRight);
                break;
            case s_speakerShootSide:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.SpeakerSecondPodiumLinkLeft, LinkParameter.Angles.SpeakerSecondPodiumLinkRight);                
                break;
            case s_intakeNote:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.IntakeLinkLeft, LinkParameter.Angles.IntakeLinkRight);
                break;
            case s_climbAngle:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.ClimbLinkLeft, LinkParameter.Angles.SetClimbLinkRight);
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
                break;
            case s_stageAngle:
                repository.MoveShooterToSpecifiedAngle(LinkParameter.Angles.StageLinkLeft, LinkParameter.Angles.StageLinkRight);
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
