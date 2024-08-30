package frc.robot.components.camera.infrastructure;

import edu.wpi.first.apriltag.AprilTag;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import frc.robot.components.camera.CameraParameter;
import frc.robot.domain.measure.CameraMeasuredState;
import frc.robot.domain.repository.CameraRepository;
import org.photonvision.PhotonCamera;
import org.photonvision.simulation.PhotonCameraSim;
import org.photonvision.simulation.VisionSystemSim;


public class Camera implements CameraRepository {
    private final PhotonCamera camera;
   public Camera() {
       CameraParameter.ConstInit();
       camera = new PhotonCamera("Photon Vision");

   }


    @Override
    public void readSensors() {
        CameraMeasuredState.Result = camera.getLatestResult();
        CameraMeasuredState.HasTarget = camera.hasTargets();
        CameraMeasuredState.Targets = CameraMeasuredState.Result.getTargets();

    }
}
