package org.firstinspires.ftc.teamcode.driveropmodes;

import static org.firstinspires.ftc.teamcode.Helper.RainbowLeds;
import static org.firstinspires.ftc.teamcode.Helper.ReportAllMotorSpeed;
import static org.firstinspires.ftc.teamcode.Helper.ReportDriveMotorStatus;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mecanum;
import static org.firstinspires.ftc.teamcode.Helper.*;

import org.firstinspires.ftc.teamcode.MotorState;
import org.firstinspires.ftc.teamcode.hardwaremaps.DeepHardwareMap;

/**
 * Class for testing of simple Mecanum movement
 */
@TeleOp(name="Mecanum Movement", group="Linear OpMode")
public class MecanumMovementTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        int iteration_counter = 0;

        // Create hardware map
        DeepHardwareMap teamHardwareMap = new DeepHardwareMap(hardwareMap);

        // Init mecanum with a power of 0.5
        Mecanum m = Mecanum.Init(
                teamHardwareMap.FrontRightMotor,
                teamHardwareMap.FrontLeftMotor,
                teamHardwareMap.BackRightMotor,
                teamHardwareMap.BackLeftMotor,
                1
                );

        // Make sure all motors are behaving properly
        ReportDriveMotorStatus(teamHardwareMap, telemetry);

        MotorState prev_ms, curr_ms;
        curr_ms = new MotorState(teamHardwareMap);

        m.correctState = curr_ms;
        m.telemetry = telemetry;

        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            prev_ms = curr_ms.copy();
            curr_ms.update(teamHardwareMap);
            // Give gamepad to mecanum to move wheels
            m.Move(gamepad1, prev_ms, curr_ms);
            ReportAllMotorSpeed(teamHardwareMap, telemetry);
        }
    }
}
