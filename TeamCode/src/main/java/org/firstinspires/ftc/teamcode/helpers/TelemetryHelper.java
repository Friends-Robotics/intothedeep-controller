package org.firstinspires.ftc.teamcode.helpers;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwareMap;

public class TelemetryHelper {
    /**
     * Logs operational state of all motors
     * @param hardwareMap The HardwareMap being used
     * @param telemetry Telemetry object from OpMode for logging
     */
    public static void ReportDriveMotorStatus(HardwareMap hardwareMap, Telemetry telemetry) {
//        telemetry.addData("Front Right Motor", hardwareMap.FrontRightMotor == null ? "Fault" : "Operational");
//        telemetry.addData("Front Left Motor", hardwareMap.FrontLeftMotor == null ? "Fault" : "Operational");
//        telemetry.addData("Back Right Motor", hardwareMap.BackRightMotor == null ? "Fault" : "Operational");
//        telemetry.addData("Back Left Motor", hardwareMap.BackLeftMotor == null ? "Fault" : "Operational");
        telemetry.update();
    }

    /**
     * Logs current power of all drive motors in hardware map
     * @param hardwareMap HardwareMap currently in use
     * @param telemetry Telemetry object from OpMode for logging
     */
    public static void ReportAllMotorSpeed(HardwareMap hardwareMap, Telemetry telemetry) {
//        telemetry.addData("Front Right Motor Power", hardwareMap.FrontRightMotor.getPower());
//        telemetry.addData("Front Left Motor Power", hardwareMap.FrontLeftMotor.getPower());
//        telemetry.addData("Back Right Motor Power", hardwareMap.BackRightMotor.getPower());
//        telemetry.addData("Back Left Motor Power", hardwareMap.BackLeftMotor.getPower());
        telemetry.update();
    }
}
