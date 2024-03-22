package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.components.Service;
import frc.robot.components.drive.DriveService;
import frc.robot.components.drive.infrastructure.Drive;
import frc.robot.components.led.LEDService;
import frc.robot.components.led.infrastructure.LED;
import frc.robot.components.link.LinkService;
import frc.robot.components.link.infrastructure.Link;
import frc.robot.components.shooter.ShooterService;
import frc.robot.components.shooter.infrastructure.Shooter;
import frc.robot.domain.repository.DriveRepository;
import frc.robot.mode.ModeManager;
import frc.robot.phase.Autonomous;
import frc.robot.subClass.Util;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Robot extends TimedRobot {
    ArrayList<Service> services = new ArrayList<>();

    @Override
    public void robotInit() {
        services.add(new DriveService(new Drive()));
        services.add(new LEDService(new LED()));
        services.add(new LinkService((new Link())));
        services.add(new ShooterService(new Shooter()));
        ModeManager.setupMode();
    }

    @Override
    public void robotPeriodic() {
        Util.allSendConsole();
    }

    @Override
    public void autonomousInit() {
        Autonomous.autonomousInit();
    }

    @Override
    public void autonomousPeriodic() {
        for (Service service : services) {
            service.resetModel();
            service.readSensors();
        }

        Autonomous.run();

        for (Service service : services) {
            service.applyModel();
        }
    }

    @Override
    public void teleopInit() {
        ModeManager.mode = ModeManager.ModeType.k_drive;
    }

    @Override
    public void teleopPeriodic() {
        for (Service service : services) {
            service.resetModel();
            service.readSensors();
        }

        ModeManager.changeMode();

        ModeManager.mode.changeModel();

        for (Service service : services) {
            service.applyModel();
        }
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        for (Service service : services) {
            service.readSensors();
        }
    }

    @Override
    public void testInit() {
        ModeManager.mode = ModeManager.ModeType.k_test;
    }

    Link link = new Link();
    XboxController controller = new XboxController(0);

    @Override
    public void testPeriodic() {
        for (Service service : services) {
            service.resetModel();
            service.readSensors();
        }

        ModeManager.mode.changeModel();

        for (Service service : services) {
            service.applyModel();
        }
        
    }
}