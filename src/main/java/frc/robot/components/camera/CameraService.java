package frc.robot.components.camera;

import frc.robot.components.Service;
import frc.robot.components.camera.infrastructure.Camera;
import frc.robot.domain.repository.CameraRepository;

public class CameraService implements Service {
    CameraRepository repository;
    public CameraService(CameraRepository repository) {
        this.repository = repository;
    }

    @Override
    public void applyModel() {

    }

    @Override
    public void readSensors() {

    }

    @Override
    public void resetModel() {

    }
}
