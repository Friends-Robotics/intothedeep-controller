package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportAllMotorSpeed;
import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportDriveMotorStatus;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Primary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Secondary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.GamepadButton.*;

import org.firstinspires.ftc.teamcode.HardwareMap;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;
import org.firstinspires.ftc.teamcode.helpers.Mecanum;

/**
 * Class for testing of simple Mecanum movement
 */
@TeleOp(name="Mecanum GamepadEx Movement", group="Linear OpMode")
public class MecanumGamepadEx extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        // Create hardware map
        HardwareMap teamHardwareMap = new HardwareMap(hardwareMap);

        // Init mecanum with a power of 0.5
//        Mecanum m = Mecanum.Init(
//                teamHardwareMap.FrontRightMotor,
//                teamHardwareMap.FrontLeftMotor,
//                teamHardwareMap.BackRightMotor,
//                teamHardwareMap.BackLeftMotor,
//                0.5
//        );

        // Make sure all motors are behaving properly
        ReportDriveMotorStatus(teamHardwareMap, telemetry);

        GamepadEx.init_gamepads(gamepad1, gamepad2);

//        Primary.bind((gp, __) -> m.Move(gp), ALWAYS);

        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Give gamepad to mecanum to move wheels
            Primary.update();
            ReportAllMotorSpeed(teamHardwareMap, telemetry);
        }
    }
}
