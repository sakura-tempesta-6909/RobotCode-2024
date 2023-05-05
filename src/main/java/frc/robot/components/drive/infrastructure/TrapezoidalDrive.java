package frc.robot.components.drive.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.components.drive.DriveParameter;
import frc.robot.components.drive.DriveTools;

/**
 * 台形制御を用いたドライブベース
 */
public class TrapezoidalDrive extends BasicDrive {
    private double preXSpeed, preZRotation;

    @Override
    public void arcadeDrive(double xSpeed, double zRotation) {
        if (xSpeed - preXSpeed >= DriveParameter.Speeds.TrapezoidalAcceleration) {
            xSpeed = preXSpeed + DriveParameter.Speeds.TrapezoidalAcceleration;
        } else if (xSpeed - preXSpeed <= -DriveParameter.Speeds.TrapezoidalAcceleration) {
            xSpeed = preXSpeed - DriveParameter.Speeds.TrapezoidalAcceleration;
        }

        if (zRotation - preZRotation >= DriveParameter.Speeds.TrapezoidalAcceleration) {
            zRotation = preZRotation + DriveParameter.Speeds.TrapezoidalAcceleration;
        } else if (zRotation - preZRotation <= -DriveParameter.Speeds.TrapezoidalAcceleration) {
            zRotation = preZRotation - DriveParameter.Speeds.TrapezoidalAcceleration;
        }
        super.arcadeDrive(xSpeed, zRotation);

        preXSpeed = xSpeed;
        preZRotation = zRotation;
    }

    @Override
    public void straightPID(double straightTarget) {
        // 台形制御をやりたければ追加する
        driveLeftFront.set(ControlMode.Position, DriveTools.convertCmToPoints(straightTarget));
        driveRightFront.set(ControlMode.Position, DriveTools.convertCmToPoints(straightTarget));
    }
}
