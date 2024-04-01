package frc.robot.phase;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.drive.DriveParameter;
import frc.robot.domain.measure.LinkMeasuredState;
import frc.robot.domain.measure.ShooterMeasuredState;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.DriveModel.DriveOriented;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;
import frc.robot.domain.model.ShooterModel.ShooterMode;




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
     * なし
     */
    private static PhaseTransition phaseTransitionD;
	
	private static String m_autoSelected;
	
	private final static SendableChooser<String> m_chooser = new SendableChooser<>();

	public static void robotInit() {
		m_chooser.setDefaultOption("Default", "D");
		m_chooser.addOption("Shoot Speaker & Taxi", "A");
		m_chooser.addOption("Shoot Amp & Taxi", "B");
		m_chooser.addOption("Taxi", "C");
		SmartDashboard.putData("Auto choices", m_chooser);
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
                    DriveModel.driveOriented = DriveOriented.s_fieldOriented;
					DriveModel.driveSideSpeed = 0;
					DriveModel.driveFowardSpeed *= DriveParameter.Speeds.SlowDrive;
					/**
					 * ドライブベースを動かす
					 * @param driveSideSpeed     左右成分 [-1 ~ 1] 右に進むとき正
					 * @param driveFowardSpeed     前後成分 [-1 ~ 1] 前に進むとき正
					 * @param driveThetaSpeed 回転成分 [-1 ~ 1] 反時計(左)回りが正
					 */
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
                     return LinkMeasuredState.linkAmpHeight = true;
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
                    return ShooterMeasuredState.isNoteGet = false;
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
                    return ShooterMeasuredState.isNoteGet = false;
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
					LinkModel.shooterAngleMode = ShooterAngleMode.s_stageAngle;
                },
                (double time) -> {
                    return LinkMeasuredState.linkUnderStage = true;
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
				taxi(3, "Move out of Robot Starting Zone")
        );

        phaseTransitionB.registerPhase(
               //LINKの角度をAMPにSHOOTする角度に変える
				adjustLinkAmp("Move to Amp Angle"),

				//AMPにSHOOT
				shootAmp("Shoot to Amp"),

				//LINKの角度を元の位置にまで戻す
				adjustLinkBack("Move Angle Back"),
				//Taxi
				taxi(3, "Move out of Robot Starting Zone") 
        );

        phaseTransitionC.registerPhase(
				//Taxi
            	taxi(3, "Move out of Robot Starting Zone")

        );

		phaseTransitionD.registerPhase(
                //なんもしない
        );

		m_autoSelected = m_chooser.getSelected();
	}

    public static void run() {
        switch (m_autoSelected) {
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

