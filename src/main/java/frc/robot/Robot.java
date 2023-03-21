package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.components.Drive.*;
import frc.robot.components.Service;
import frc.robot.domain.model.DriveModel;
import frc.robot.mode.ModeManager;
import frc.robot.phase.Autonomous;
import frc.robot.subClass.MQTT;
import frc.robot.subClass.Util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Robot extends TimedRobot {
    ArrayList<Service> services = new ArrayList<>();
    MQTT mqtt = new MQTT();

    PrintStream defaultConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    @Override
    public void robotInit() {
        System.setOut(new PrintStream(newConsole));
        Thread thread = new Thread(() -> {
            mqtt.connect();
        });
        thread.start();

        services.add(new DriveService(new DriveRealComponent()));

//        State.StateInit();
        Util.sendSystemOut(defaultConsole, newConsole);
        defaultConsole.print(newConsole);
        newConsole = new ByteArrayOutputStream();
    }

    @Override
    public void robotPeriodic() {
        Util.sendSystemOut(defaultConsole, newConsole);
        defaultConsole.print(newConsole);
        newConsole = new ByteArrayOutputStream();
    }

    @Override
    public void autonomousInit() {
        Autonomous.autonomousInit();
    }

    @Override
    public void autonomousPeriodic() {
//        State.StateReset();

        for (Service service : services) {
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

        ModeManager.mode.changeState();

        for (Service service : services) {
            service.applyModel();
        }

        Util.allSendConsole();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        for (Service service : services) {
            service.readSensors();
        }
        Util.allSendConsole();
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
//        State.StateReset();
        for (Service service : services) {
            service.readSensors();
        }

        ModeManager.mode.changeState();

        for (Service service : services) {
            service.applyModel();
        }

    }
}
