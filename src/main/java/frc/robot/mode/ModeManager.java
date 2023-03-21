package frc.robot.mode;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.domain.model.DriveModel;

public class ModeManager {
    public static ModeType mode = ModeType.k_drive;
    public enum ModeType {
        k_drive(DriveMode::changeState),;

        private final Runnable changeState;
        ModeType(Runnable changeState) {
            this.changeState = changeState;
        }
        public void changeState() {
            this.changeState.run();
        }
    }

    static XboxController driveController, operateController;
    static DriveModel driveModel;
    public static void setupMode(DriveModel driveModel) {
        driveController = new XboxController(0);
        operateController = new XboxController(1);
        ModeManager.driveModel = driveModel;
    }

    public static void changeMode() {

    }
}
