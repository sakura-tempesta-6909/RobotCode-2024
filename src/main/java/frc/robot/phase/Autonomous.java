package frc.robot.phase;

import apple.laf.JRSUIConstants.State;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.proto.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.components.drive.DriveConst.DriveConstants;
import frc.robot.components.link.LinkParameter;
import frc.robot.domain.measure.LinkMeasuredState;
import frc.robot.domain.measure.ShooterMeasuredState;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;
import frc.robot.domain.model.ShooterModel.ShooterMode;


public static void autonomousInit() {

	}

	public static void run() {

	}
	 package frc.robot.phase;


public class Autonomous {
    /**
     * ノートをシューターに入れる<br>
     * バックする
     */
    private static PhaseTransition phaseTransitionA;
    /**
     * ノートをアンプに入れる<br>
     * バックする
     */
    private static PhaseTransition phaseTransitionB;
    /**
     * バックする
     */
    private static PhaseTransition phaseTransitionC;
	/**
     * なし(アウトテーク)
     */
    private static PhaseTransition phaseTransitionD;

    /**
     * outTakeを一定時間[sec]行う
     *
     * @param waiter    outTakeする時間（実行時間）[sec]
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase outTake(double waiter, String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
                    IntakeState.intakeExtensionState = IntakeState.IntakeExtensionStates.s_openIntake;
                    IntakeState.intakeState = IntakeState.RollerStates.s_outtakeGamePiece;
                },
                (double time) -> {
                    return time > waiter;
                },
                phaseName
        );
    }

	/**
	 * Taxiをする
     * 後ろに一定時間[sec]下がる
     *
     * @param waiter    後ろに下がる時間（実行時間）[sec]
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase taxi(double waiter, String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
                    
                },
                (double time) -> {
                    return time > waiter;
                },
                phaseName
        );
    }


	/**
     * LINKの値をSHOOTERにSHOOTする角度に変える
	 * 
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase adjustLinkSpeaker(String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
					LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootPodium;
				//	ShooterMode.shooterMode = ShooterMode.s_shootSpeaker;
				//	LinkModel.shooterAngleMode = ShooterAngleMode.s_keepCurrentAngle;
           	 	//	ShooterModel.shooterMode = ShooterMode.s_increaseRotation;
				// ShooterMeasuredState.shooterUpperSpeed = ShooterParameter.Speed.ShooterTargetSpeed;
                },
                (double time) -> {
                    return LinkMeasuredState.linkSpeakerHeight = true;
                },
                () -> {
                },
                phaseName
        );
    }

	/**
     * LINKの値をAMPにSHOOTする角度に変える
	 * 
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase adjustLinkAmp(String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
					LinkModel.shooterAngleMode = ShooterAngleMode.s_ampShoot;
                },
                (double time) -> {
                    return LinkMeasuredState.linkAmpsHeight = true;
					// Amps か Amp かわからん
                },
                () -> {
                },
                phaseName
        );
    }

    /**
     * SPEAKERにSHOOTする
     *
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase shootSpeaker(String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
					ShooterModel.shooterMode = ShooterMode.s_shootSpeaker;
                },
                (double time) -> {
                    return ShooterMeasuredState.isNoteGet == false;
                },
                () -> {
                },
                phaseName
        );
	}

	/**
     * AMPにSHOOTする
     *
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase shootAmp(String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
					ShooterModel.shooterMode = ShooterMode.s_shootAmp;
                },
                (double time) -> {
                    return ShooterMeasuredState.isNoteGet == false;
                },
                () -> {
                },
                phaseName
        );
	}

	/**
     * LINKの角度を元に戻す
     *
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase adjustLinkBack(String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
					
                },
                (double time) -> {
                    return LinkMeasuredState.linkUnderStage == true;
                },
                () -> {
                },
                phaseName
        );
	}

    public static void autonomousInit() {
        phaseTransitionA = new PhaseTransition();
        phaseTransitionB = new PhaseTransition();
        phaseTransitionC = new PhaseTransition();
        PhaseTransition.Phase.PhaseInit();

        phaseTransitionA.registerPhase(
				//LINKの角度をSHOOTERにSHOOTする角度に変える
				adjustLinkSpeaker("Move to Shooter Angle"),

				//SPEAKERにSHOOT
				shootSpeaker("Shoot to Speaker"),

				//LINKの角度を元の位置にまで戻す
				adjustLinkBack("Move Angle Back"),
				//Taxi
				taxi(5, "Move out of Robot Starting Zone")
        );

        phaseTransitionB.registerPhase(
               //LINKの角度をAMPにSHOOTする角度に変える
				adjustLinkAmp("Move to Amp Angle"),

				//AMPにSHOOT
				shootAmp("Shoot to Amp"),

				//LINKの角度を元の位置にまで戻す
				adjustLinkBack("Move Angle Back"),
				//Taxi
				taxi(5, "Move out of Robot Starting Zone") 
        );

        phaseTransitionC.registerPhase(
				//Taxi
            	taxi(5, "Move out of Robot Starting Zone")

        );

		phaseTransitionD.registerPhase(
                outTake(5, "GamePiece Outtake")
        );
    }

    public static void run() {
        switch (State.autonomousPhaseTransType) {
            case "A":
                phaseTransitionA.run();
                break;
            case "B":
                phaseTransitionB.run();
                break;
            case "C":
                phaseTransitionC.run();
                break;
			case "D":
                phaseTransitionD.run();
                break;
            default:
                phaseTransitionD.run();
                System.out.println("Now Running TransitionC!\nThere's something wrong\nPlease Enter Autonomous Phase Transition Type!!!");
                break;
        }
    }
}

