package frc.robot.components.drive;

public class DriveTools {
    public static double convertPointsToCm(double points) {
        return points / DriveConst.Wheel.PointsPerCm;
    }

    public static double convertCmToPoints(double cm) {
        return cm * DriveConst.Wheel.PointsPerCm;
    }
}
