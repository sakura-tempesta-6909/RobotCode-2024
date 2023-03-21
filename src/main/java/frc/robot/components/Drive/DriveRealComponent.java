package frc.robot.components.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.domain.repository.DriveRepository;

public class DriveRealComponent extends DriveRepository {
    private final WPI_TalonSRX driveRightFront, driveLeftFront;
    private final VictorSPX driveRightBack, driveLeftBack;
    private final DifferentialDrive differentialDrive;
    private final PIDController rotationPidController;

    public DriveRealComponent() {
        driveRightFront = new WPI_TalonSRX(Const.Ports.DriveRightFront);
        driveLeftFront = new WPI_TalonSRX(Const.Ports.DriveLeftFront);
        driveRightBack = new VictorSPX(Const.Ports.DriveRightBack);
        driveLeftBack = new VictorSPX(Const.Ports.DriveLeftBack);

        driveRightBack.follow(driveRightFront);
        driveLeftBack.follow(driveLeftFront);

        differentialDrive = new DifferentialDrive(driveLeftFront, driveRightFront);

        driveRightFront.configAllSettings(Const.PID.rightPIDConfig);
        driveLeftFront.configAllSettings(Const.PID.leftPIDConfig);
        rotationPidController = new PIDController(0, 0, 0);
        rotationPidController.setIntegratorRange(0, 0);

    }

    @Override
    public void arcadeDrive(double xSpeed, double zRotation) {
        differentialDrive.arcadeDrive(xSpeed, zRotation);
        differentialDrive.feed();
    }

    @Override
    public void straightPID(double straightTarget) {
        driveLeftFront.set(ControlMode.Position, DriveTools.convertCentimeterToPosition(straightTarget));
        driveRightFront.set(ControlMode.Position, DriveTools.convertCentimeterToPosition(straightTarget));
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
        DriveMeasuredState.leftActualPosition = DriveTools.convertPositionToCentiMeter(driveLeftFront.getSelectedSensorPosition());
        DriveMeasuredState.rightActualPosition = DriveTools.convertPositionToCentiMeter(driveRightFront.getSelectedSensorPosition());
    }
}
