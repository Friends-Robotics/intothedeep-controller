package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportAllMotorSpeed;
import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportDriveMotorStatus;

import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Primary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Secondary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.GamepadButton.*;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwareMap;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

/**
 * Class for testing of simple Mecanum movement
 */
//@Disabled
//@TeleOp(name="Manual Movement", group="Linear OpMode")
//public class ManualMovement extends LinearOpMode {
//    @Override
//    public void runOpMode() {
//        telemetry.addData("Status", "Initialized");
//
//        // Create hardware map
//        HardwareMap teamHardwareMap = new HardwareMap(hardwareMap);
//
//        // Make sure all motors are behaving properly
//        ReportDriveMotorStatus(teamHardwareMap, telemetry);
//
////        DcMotorSimple[] motors = new DcMotorSimple[] {teamHardwareMap.FrontRightMotor, teamHardwareMap.FrontLeftMotor, teamHardwareMap.BackRightMotor, teamHardwareMap.BackLeftMotor};
//
//        GamepadEx.init_gamepads(gamepad1, gamepad2);
//
//        double right_extend = 0;
//        double left_extend = 0;
////        Primary.bind(A, (gp, __) -> right_extend = 0, (gp, __) -> motors[0].setPower(0));
////        Primary.bind(A, (gp, __) -> left_extend = 0, (gp, __) -> motors[0].setPower(0));
//
////        Primary.bind(A, (gp, __) -> motors[0].setPower(1), (gp, __) -> motors[0].setPower(0));
////        Primary.bind(B, (gp, __) -> motors[1].setPower(1), (gp, __) -> motors[1].setPower(0));
////        Primary.bind(X, (gp, __) -> motors[2].setPower(1), (gp, __) -> motors[2].setPower(0));
////        Primary.bind(Y, (gp, __) -> motors[3].setPower(1), (gp, __) -> motors[3].setPower(0));
//
////        Primary.bind(RIGHT_BUMPER, (gp, __) ->  {
////            teamHardwareMap.RightExtendServo.setPosition(0.2);
////            }, (gp, __) -> {
////            teamHardwareMap.RightExtendServo.setPosition(0);
////        });
////
////        Primary.bind(LEFT_BUMPER, (gp, __) -> {
////            teamHardwareMap.LeftExtendServo.setPosition(0.2);
////        }, (gp, __) -> {
////            teamHardwareMap.LeftExtendServo.setPosition(0);
////        });
//
//
//
//        // Primary.bind(RIGHT_TRIGGER, (gp, __) ->  teamHardwareMap.ClawServo.setPosition(1), (gp, __) -> teamHardwareMap.ClawServo.setPosition(0));
//        Primary.bind(LEFT_TRIGGER, (gp, __) -> teamHardwareMap.ViperBucketServo.setPosition(1), (gp, __) -> teamHardwareMap.ViperBucketServo.setPosition(0));
//
//        telemetry.update();
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//
//        Gamepad prev = new Gamepad();
//
//        while (opModeIsActive()) {
//            ReportAllMotorSpeed(teamHardwareMap, telemetry);
//
//            telemetry.addData("Button A State", Primary.get_button_state(A));
//            telemetry.addData("Button B State", Primary.get_button_state(B));
//            telemetry.addData("Button X State", Primary.get_button_state(X));
//            telemetry.addData("Button Y State", Primary.get_button_state(Y));
//
//            telemetry.addData("Bindings Length", Primary.bindings.values().size());
//            telemetry.addData("Bindings Alt Length", Primary.bindings_alt.values().size());
//
//
//            if(!prev.right_bumper && gamepad1.right_bumper) {
//                right_extend = Math.max(Math.min(1, right_extend + 0.1), -1);
//            }
//
//            if(!prev.left_bumper && gamepad1.left_bumper) {
//                right_extend = Math.max(Math.min(1, right_extend - 0.1), -1);
//            }
//
//            if(gamepad1.dpad_up) {
//                right_extend = 0;
//            }
//
//            telemetry.addData("right_Ext4nsion", right_extend);
//            telemetry.addData("left_exntesino", left_extend);
//
//            teamHardwareMap.RightExtendServo.setPosition(right_extend);
//
//            // Primary.update();
//
//            prev.copy(gamepad1);
//        }
//    }
//}
