package frc.robot.domain.measure;

import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.util.List;

public class CameraMeasuredState {
    public static PhotonPipelineResult Result;
    public static boolean HasTarget;
    public static List<PhotonTrackedTarget> Targets;
}
