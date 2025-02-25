package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportAllMotorSpeed;
import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportDriveMotorStatus;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwareMap;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;
import org.firstinspires.ftc.teamcode.helpers.Mecanum;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Primary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Secondary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.GamepadButton.*;

/**
 * Class for testing of simple Mecanum movement
 *  B -> Claw
 *
 */
@TeleOp(name="Mecanum Movement", group="Linear OpMode")
public class MecanumMovement extends LinearOpMode {
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

        GamepadEx.init_gamepads(gamepad1, gamepad2);

        // Make sure all motors are behaving properly
        ReportDriveMotorStatus(teamHardwareMap, telemetry);
//
//        Secondary.bind(B, (gp, __) -> {
//            teamHardwareMap.ViperBucketServo.setPosition(1);
//            }, (gp, __) ->  {
//            teamHardwareMap.ViperBucketServo.setPosition(0);
//        });
//
//        Secondary.bind(A, (gp, __) -> {
//            teamHardwareMap.RightExtendServo.setPosition(0.3);
//        }, (gp, __) ->  {
//            teamHardwareMap.RightExtendServo.setPosition(0.7);
//        });
//
        telemetry.update();
        waitForStart();

        Gamepad prev = new Gamepad();

        if (isStopRequested()) return;

        double motor_power = 1;

        while (opModeIsActive()) {
            // Give gamepad to mecanum to move wheels
//            m.Move(gamepad1);

//            Primary.update();

            if(gamepad1.a) {
                teamHardwareMap.RightExtendServo.setPosition(0.4);
            }
            else {
                teamHardwareMap.RightExtendServo.setPosition(0.6);
            }

            if(gamepad1.b) {
                telemetry.addLine("b is pressed");
                teamHardwareMap.ClawServo.setPosition(0);
            } else {
                teamHardwareMap.ClawServo.setPosition(1);
            }

            if(gamepad1.x) {
                teamHardwareMap.RightArmServo.setPosition(0.5);
                // teamHardwareMap.RightArmServo.setPosition(0.7);
            } else {
                teamHardwareMap.RightArmServo.setPosition(0);
            }

            if(gamepad1.left_bumper) {
                teamHardwareMap.RightViperMotor.setPower(-motor_power);
                teamHardwareMap.LeftViperMotor.setPower(-motor_power);
            } else {
                teamHardwareMap.RightViperMotor.setPower(0);
                teamHardwareMap.LeftViperMotor.setPower(0);
            }

            if(gamepad1.right_bumper) {
                teamHardwareMap.RightViperMotor.setPower(motor_power);
                teamHardwareMap.LeftViperMotor.setPower(motor_power);
            } else {
                teamHardwareMap.RightViperMotor.setPower(0);
                teamHardwareMap.LeftViperMotor.setPower(0);
            }

            ReportAllMotorSpeed(teamHardwareMap, telemetry);
            prev.copy(gamepad1);
        }
    }
}
