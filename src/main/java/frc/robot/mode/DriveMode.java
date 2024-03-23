package frc.robot.mode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.DriveModel.DriveOriented;
import frc.robot.domain.model.ShooterModel.ShooterMode;
import frc.robot.subClass.Util;

class DriveMode extends ModeManager {
    public static void changeModel() {
        DriveModel.driveMovement = DriveModel.DriveMovement.s_midDrive;
        DriveModel.driveSideSpeed = Util.deadband(driveController.getLeftX());
        DriveModel.driveFowardSpeed = Util.deadband(-driveController.getLeftY()); //スティックを奥に倒すと正になるように変更
        DriveModel.driveThetaSpeed = Util.deadband(-driveController.getRightX()); //スティックを右に倒すと反時計回りになるように変更
        DriveModel.driveOriented = DriveModel.DriveOriented.s_fieldOriented;
        if(driveController.getRightBumper()) {
            DriveModel.driveOriented = DriveModel.DriveOriented.s_robotOriented;
        } 
    }
}
