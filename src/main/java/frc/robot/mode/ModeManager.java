package frc.robot.mode;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.domain.model.DriveModel;

public class ModeManager {
    public static void changeMode() {

    }

    public enum ModeType {
        k_drive(DriveMode::changeState),
        k_test(TestMode::changeState),
        ;

        private final Runnable changeState;

        ModeType(Runnable changeState) {
            this.changeState = changeState;
        }

        public void changeState() {
            this.changeState.run();
        }
    }

    public static ModeType mode = ModeType.k_drive;

    static XboxController driveController, operateController;

    public static void setupMode() {
        driveController = new XboxController(0);
        operateController = new XboxController(1);
    }
}
