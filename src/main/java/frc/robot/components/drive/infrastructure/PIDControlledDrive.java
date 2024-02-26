package frc.robot.components.drive.infrastructure;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.components.drive.DriveConst;

/**
 * arcadeDriveをPIDベースでやる
 */
public class PIDControlledDrive extends BasicDrive {
    @Override
    public void arcadeDrive(double xSpeed, double zRotation) {
        xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
        zRotation = MathUtil.clamp(zRotation, -1.0, 1.0);

        double leftSpeed = xSpeed - zRotation;
        double rightSpeed = xSpeed + zRotation;

        // Find the maximum possible value of (throttle + turn) along the vector
        // that the joystick is pointing, then desaturate the wheel speeds
        double greaterInput = Math.max(Math.abs(xSpeed), Math.abs(zRotation));
        double lesserInput = Math.min(Math.abs(xSpeed), Math.abs(zRotation));
        if (greaterInput == 0.0) {
            leftSpeed = 0.0;
            rightSpeed = 0.0;
        }
        double saturatedInput = (greaterInput + lesserInput) / greaterInput;
        leftSpeed /= saturatedInput;
        rightSpeed /= saturatedInput;

        driveLeftFront.selectProfileSlot(1, 0);
        driveRightFront.selectProfileSlot(1, 0);
        this.driveLeftFront.set(TalonSRXControlMode.Velocity, leftSpeed * DriveConst.Wheel.MaxVelocity);
        this.driveRightFront.set(TalonSRXControlMode.Velocity, rightSpeed * DriveConst.Wheel.MaxVelocity);
    }
}
