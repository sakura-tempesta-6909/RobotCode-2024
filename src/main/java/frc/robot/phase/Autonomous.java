package frc.robot.phase;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.drive.DriveParameter;
import frc.robot.components.drive.infrastructure.Drive;
import frc.robot.domain.measure.LinkMeasuredState;
import frc.robot.domain.measure.ShooterMeasuredState;
import frc.robot.domain.model.DriveModel;
import frc.robot.domain.model.LinkModel;
import frc.robot.domain.model.ShooterModel;
import frc.robot.domain.model.DriveModel.DriveOriented;
import frc.robot.domain.model.LinkModel.ShooterAngleMode;
import frc.robot.domain.model.ShooterModel.ShooterMode;
import frc.robot.domain.repository.DriveRepository;




public class Autonomous {	
    /**
     * ノートをシューター(正面）に入れる<br>
     * バックする
     */
    private static PhaseTransition phaseTransitionA;
    /**
     * ノートをシューター(左）に入れる<br>
     * バックする
     */
    private static PhaseTransition phaseTransitionB;
    /**
     * ノートをシューター(右）に入れる<br>
     * バックする
     */
    private static PhaseTransition phaseTransitionC;
    /**
     * ノートをアンプに入れる<br>
     * バックする
     */
    private static PhaseTransition phaseTransitionD;
    /**
     * バックする
     */
    private static PhaseTransition phaseTransitionE;
	/**
     * なし
     */
    private static PhaseTransition phaseTransitionF;
	
	private static String m_autoSelected;
	
	private final static SendableChooser<String> m_chooser = new SendableChooser<>();

	public static void robotInit() {
		m_chooser.setDefaultOption("Default", "F");
		m_chooser.addOption("Shoot Speaker(Front) & Taxi", "A");
        m_chooser.addOption("Shoot Speaker(Left) & Taxi", "B");
        m_chooser.addOption("Shoot Speaker(Right) & Taxi", "C");
		m_chooser.addOption("Shoot Amp & Taxi", "D");
		m_chooser.addOption("Taxi", "E");
		SmartDashboard.putData("Auto choices", m_chooser);
	}

	/**
     * Empty 
	 * 
     * @param waiter time
	 * @param string 
     */
    private static PhaseTransition.Phase shooting(double waiter, String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
					ShooterModel.shooterMode = ShooterMode.s_shootSpeaker;
                },
                (double time) -> {
                    return time > waiter;
                },
                () -> {
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
                    DriveModel.driveOriented = DriveOriented.s_fieldOriented;
					DriveModel.driveSideSpeed = 0;
					DriveModel.driveMovement = DriveModel.DriveMovement.s_slowDrive;
					DriveModel.driveForwardSpeed = -1;
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
					LinkModel.shooterAngleMode = ShooterAngleMode.s_speakerShootBelow;
                },
                (double time) -> {
                    return LinkMeasuredState.linkSpeakerBelowHeight;
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
                     return LinkMeasuredState.linkAmpHeight;
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
                    return !ShooterMeasuredState.isNoteGet;
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
                    return !ShooterMeasuredState.isNoteGet;
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
                    return LinkMeasuredState.linkUnderStageHeight;
                },
                () -> {
                }, 
                phaseName
        );
	}

    /**
     * Gyroの角度を変える
     *
     * @param phaseName 出力されるフェーズの名前
     */
    private static PhaseTransition.Phase changeGyroAngle(double angle,String phaseName) {
        return new PhaseTransition.Phase(
                () -> {
                },
                (double time) -> {
                    return true;
                },
                () -> {
                    DriveModel.offset = angle;
                }, 
                phaseName
        );
	}


    public static void autonomousInit() {
        phaseTransitionA = new PhaseTransition();
        phaseTransitionB = new PhaseTransition();
        phaseTransitionC = new PhaseTransition();
		phaseTransitionD = new PhaseTransition();
        phaseTransitionE = new PhaseTransition();
        phaseTransitionF = new PhaseTransition();
        PhaseTransition.Phase.PhaseInit();

        phaseTransitionA.registerPhase(
                changeGyroAngle(180, "Offset Gyro"),

				//LINKの角度をSHOOTERにSHOOTする角度に変える
				adjustLinkSpeaker("Move to Shooter Angle"),

				//SPEAKERにSHOOT
				shootSpeaker("Shoot to Speaker in Front"),

				shooting(0.5, "Shooting"),

				//LINKの角度を元の位置にまで戻す
				//adjustLinkBack("Move Angle Back"),
				//Taxi

				taxi(1.5, "Move out of Robot Starting Zone")
        );

        phaseTransitionB.registerPhase(
                changeGyroAngle(180, "Offset Gyro"),

				//LINKの角度をSHOOTERにSHOOTする角度に変える
				adjustLinkSpeaker("Move to Shooter Angle"),

				//SPEAKERにSHOOT
				shootSpeaker("Shoot to Speaker from Left(From Driver)"),

				shooting(0.5, "Shooting"),

				//LINKの角度を元の位置にまで戻す
				//adjustLinkBack("Move Angle Back"),
				//Taxi

				taxi(1.5, "Move out of Robot Starting Zone")
        );

        phaseTransitionC.registerPhase(
                changeGyroAngle(180, "Offset Gyro"),

				//LINKの角度をSHOOTERにSHOOTする角度に変える
				adjustLinkSpeaker("Move to Shooter Angle"),

				//SPEAKERにSHOOT
				shootSpeaker("Shoot to Speaker from Right(From Driver)"),

				shooting(0.5, "Shooting"),

				//LINKの角度を元の位置にまで戻す
				//adjustLinkBack("Move Angle Back"),
				//Taxi

				taxi(1.5, "Move out of Robot Starting Zone")
        );

        phaseTransitionD.registerPhase(
               //LINKの角度をAMPにSHOOTする角度に変える
				adjustLinkAmp("Move to Amp Angle"),

				//AMPにSHOOT
				shootAmp("Shoot to Amp"),

				shooting(0.5, "Shooting"),

				//LINKの角度を元の位置にまで戻す
				//adjustLinkBack("Move Angle Back"),
				//Taxi
				taxi(1.5, "Move out of Robot Starting Zone") 
        );

        phaseTransitionE.registerPhase(
				//Taxi
            	taxi(1.5, "Move out of Robot Starting Zone")

        );

		phaseTransitionF.registerPhase(
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