package frc.robot.mode;

import edu.wpi.first.wpilibj.XboxController;

public class ModeManager {
    public static void changeMode() {
        //ClimbModeからDriveMode
        if(driveController.getBackButton()) {
            mode = ModeType.k_drive;
        }

        //DriveModeからClimbMode
        if(driveController.getStartButton()) {
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
