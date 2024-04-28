package frc.robot.mode;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.domain.repository.LinkRepository;

public class ModeManager {
    public static void changeMode() {
        // DriveModeに変更
        if(driveController.getBackButton()) {
            mode = ModeType.k_drive;
        }

        // ClimbModeに変更
        if(operateController.getStartButton()) {
            mode = ModeType.k_climb;
        }
    }

    public enum ModeType {
        k_drive(DriveMode::changeModel),
        k_test(TestMode::changeState),
        k_climb(ClimbMode::changeModel),
        ;

        private final Runnable changeModel;

        ModeType(Runnable changeModel) {
            this.changeModel = changeModel;
        }

        public void changeModel() {
            this.changeModel.run();
        }
    }

    public static ModeType mode = ModeType.k_drive;

    static XboxController driveController, operateController;

    public static void setupMode() {
        driveController = new XboxController(0);
        operateController = new XboxController(1);
    }
}
