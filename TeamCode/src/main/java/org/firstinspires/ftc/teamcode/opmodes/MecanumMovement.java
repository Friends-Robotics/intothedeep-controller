package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportAllMotorSpeed;
import static org.firstinspires.ftc.teamcode.helpers.TelemetryHelper.ReportDriveMotorStatus;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwareMap;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;
import org.firstinspires.ftc.teamcode.helpers.Mecanum;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Primary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Secondary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.GamepadButton.*;

//        -----------------------------------------------------------------------
//        | Controller Button | Description           | Motor/Servo Affected    |
//        -----------------------------------------------------------------------
//
//        -----------------------------------------------------------------------
//        | Secondary: A      | Extend Claw Forward   | Right Extend Servo      |
//        -----------------------------------------------------------------------
//        | Secondary: B      | Open / Close Claw     | Claw Servo              |
//        -----------------------------------------------------------------------
//        | Secondary: X      | Claw Rotation         | Right Arm Servo         |
//        -----------------------------------------------------------------------
//        | Secondary: Y      | Toggle Precision Mode | N/A                     |
//        -----------------------------------------------------------------------
//
@TeleOp(name="Movement", group="Linear OpMode")
public class MecanumMovement extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        // Create hardware map
        HardwareMap teamHardwareMap = new HardwareMap(hardwareMap);

        // Init mecanum with a power of 0.5
        Mecanum m = Mecanum.Init(
                teamHardwareMap.FrontRightMotor,
                teamHardwareMap.FrontLeftMotor,
                teamHardwareMap.BackRightMotor,
                teamHardwareMap.BackLeftMotor,
                0.5
        );

        GamepadEx.init_gamepads(gamepad1, gamepad2);

        ReportDriveMotorStatus(teamHardwareMap, telemetry);

        telemetry.update();
        waitForStart();

        Gamepad prev = new Gamepad();

        Gamepad primary = gamepad1;
        Gamepad secondary = gamepad1;

        double motor_power = 1;
        boolean precision_mode = false;

//        teamHardwareMap.RightArmServo.scaleRange(0, 1);
//        teamHardwareMap.LeftArmServo.scaleRange(0, 1);
//
//        teamHardwareMap.ClawServo.setPosition(0);

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Centre Odometer", teamHardwareMap.CenterOdometer.getVelocity());
            telemetry.addData("Left Odometer", teamHardwareMap.LeftOdometer.getVelocity());
            telemetry.addData("Right Odometer", teamHardwareMap.RightOdometer.getVelocity());

            m.Move(primary);

            if(!prev.y && secondary.y) {
                precision_mode = !precision_mode;
            }
//
//            if(secondary.a) {
//                teamHardwareMap.RightExtendServo.setPosition(0.35);
//            } else {
//                teamHardwareMap.RightExtendServo.setPosition(0.7);
//            }
//
//            if(!prev.a && secondary.a) {
//                if(teamHardwareMap.RightExtendServo.getPosition() == 0.4) {
//                    teamHardwareMap.RightExtendServo.setPosition(0.55);
//                }
//                else teamHardwareMap.RightExtendServo.setPosition(0.4);
//            }

//            if(!prev.b && secondary.b) {
//                if(teamHardwareMap.ClawServo.getPosition() == 0) {
//                    teamHardwareMap.ClawServo.setPosition(1);
//                }
//                else teamHardwareMap.ClawServo.setPosition(0);
//            }
//
//            // Fix servos for this and then uncomment
//            if(secondary.x) {
//                teamHardwareMap.RightArmServo.setPosition(0);
//                // teamHardwareMap.LeftArmServo.setPosition(0);
//            } else {
//                teamHardwareMap.RightArmServo.setPosition(1);
//                // teamHardwareMap.LeftArmServo.setPosition(0);
//            }
//
//            // Fix servos for this and then uncomment
//            if(secondary.b) {
//                teamHardwareMap.LeftArmServo.setPosition(0);
//                // teamHardwareMap.LeftArmServo.setPosition(0);
//            } else {
//                teamHardwareMap.LeftArmServo.setPosition(1);
//                // teamHardwareMap.LeftArmServo.setPosition(0);
//            }
//
//
//            if(secondary.left_bumper) {
//                teamHardwareMap.RightViperMotor.setPower(-motor_power);
//                teamHardwareMap.LeftViperMotor.setPower(-motor_power);
//            } else {
//                teamHardwareMap.RightViperMotor.setPower(0);
//                teamHardwareMap.LeftViperMotor.setPower(0);
//            }
//
            if(secondary.right_bumper) {
                teamHardwareMap.RightViperMotor.setPower(motor_power);
            }
            else if(secondary.right_trigger > 0) {
                teamHardwareMap.RightViperMotor.setPower(-motor_power);
            }
            else {
                teamHardwareMap.RightViperMotor.setPower(0);
            }

            if(secondary.left_bumper) {
                teamHardwareMap.LeftViperMotor.setPower(motor_power);
            }
            else if(secondary.left_trigger > 0) {
                teamHardwareMap.LeftViperMotor.setPower(-motor_power);
            }
            else {
                teamHardwareMap.LeftViperMotor.setPower(0);
            }

            ReportAllMotorSpeed(teamHardwareMap, telemetry);
            telemetry.addData("Precision Mode", precision_mode);
            prev.copy(secondary);
        }
    }
}
