package frc.robot.components.drive.infrastructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.components.drive.DriveConst;
import frc.robot.components.drive.DriveParameter;
import frc.robot.components.drive.DriveTools;
import frc.robot.domain.measure.DriveMeasuredState;
import frc.robot.domain.repository.DriveRepository;

/**
 * 基本的なDriveBase
 */
public class BasicDrive implements DriveRepository {
    final WPI_TalonSRX driveRightFront, driveLeftFront;
    final DifferentialDrive differentialDrive;
    final PIDController rotationPidController;

    public BasicDrive() {
        DriveParameter.ConstInit();
        driveRightFront = new WPI_TalonSRX(DriveConst.Ports.DriveRightFront);
        driveLeftFront = new WPI_TalonSRX(DriveConst.Ports.DriveLeftFront);
        VictorSPX driveRightBack = new VictorSPX(DriveConst.Ports.DriveRightBack);
        VictorSPX driveLeftBack = new VictorSPX(DriveConst.Ports.DriveLeftBack);

        driveRightBack.follow(driveRightFront);
        driveLeftBack.follow(driveLeftFront);

        differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);

        driveRightFront.configAllSettings(DriveParameter.PID.rightPIDConfig);
        driveLeftFront.configAllSettings(DriveParameter.PID.leftPIDConfig);
        rotationPidController = new PIDController(DriveParameter.PID.RotationPID_kP, DriveParameter.PID.RotationPID_kI, DriveParameter.PID.RotationPID_kD);
        rotationPidController.setIntegratorRange(-DriveParameter.PID.RotationPID_maxIAccum, DriveParameter.PID.RotationPID_maxIAccum);
    }

    @Override
    public void arcadeDrive(double xSpeed, double zRotation) {
        differentialDrive.arcadeDrive(xSpeed, zRotation);
        differentialDrive.feed();
    }

    @Override
    public void straightPID(double straightTarget) {
        driveLeftFront.selectProfileSlot(0, 0);
        driveRightFront.selectProfileSlot(0, 0);
        driveLeftFront.set(ControlMode.Position, DriveTools.convertCmToPoints(straightTarget));
        driveRightFront.set(ControlMode.Position, DriveTools.convertCmToPoints(straightTarget));
    }

    @Override
    public void resetStraightPIDController() {
        driveLeftFront.setSelectedSensorPosition(0);
        driveRightFront.setSelectedSensorPosition(0);
    }

    @Override
    public void rotationPID(double targetAngle) {
        arcadeDrive(0, rotationPidController.calculate(DriveMeasuredState.actualAngle, targetAngle));
    }

    @Override
    public void resetRotationPIDController() {
        rotationPidController.reset();
    }

    @Override
    public void readSensors() {
        DriveMeasuredState.leftActualPosition = DriveTools.convertPointsToCm(driveLeftFront.getSelectedSensorPosition());
        DriveMeasuredState.rightActualPosition = DriveTools.convertPointsToCm(driveRightFront.getSelectedSensorPosition());
    }
}
