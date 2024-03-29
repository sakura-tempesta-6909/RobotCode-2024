package frc.robot.phase;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.proto.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.components.drive.DriveConst.DriveConstants;

public class Autonomous {
	public static void autonomousInit() {

	}

	public static void run() {

	}

	//[20:40]コードを書く場所が違いそうなので確認して
	/*public Command getAutonomousCommand() {
		//1. Create trajectory settings
		TrajectoryConfig trajectoryConfig = new TrajectoryConfig(
			AutoConstants.kMaxSpeedMetersPerSecond,
			AutoConstants.kMaxAccelerationMetersPerSecondSquared)
				.setKinematics(DriveConstants.kDriveKinematics);
		//2. Generate trajectory
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			new Pose2d(0, 0, new Rotation2d(0)),
			List.of(
				new Translation2d(1, 0),
				new Translation2d(1, 1)
			),
			new Pose2d(2, 1, Rotation2d.fromDegrees(180)),
			trajectoryConfig
		);
		//3. Define PID controllers for tracking trajectory
		PIDController xController = new PIDController(AutoConstants.kPXController, 0, 0);
		PIDController yController = new PIDController(AutoConstants.kPYController, 0, 0);
		return null;
	}*/
}
