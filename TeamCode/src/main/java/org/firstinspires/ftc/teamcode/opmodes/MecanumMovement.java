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
import org.firstinspires.ftc.teamcode.helpers.Arm;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Primary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.Secondary;
import static org.firstinspires.ftc.teamcode.helpers.GamepadEx.GamepadButton.*;

//        -------------------------------------------------------------------------------
//        | Controller Button     | Description           | Motor/Servo Affected        |
//        -------------------------------------------------------------------------------
//
//        -------------------------------------------------------------------------------
//        | Secondary: A/Cross    | Extend Claw Forward   | Right and Left Extend Servo |
//        -------------------------------------------------------------------------------
//        | Secondary: B/Circle   | Open / Close Claw     | Claw Servo                  |
//        -------------------------------------------------------------------------------
//        | Secondary: X/Square   | Claw Rotation         | Right Arm Servo             |
//        -------------------------------------------------------------------------------
//        | Secondary: Y/Triangle | Toggle Precision Mode | N/A                         |
//        -------------------------------------------------------------------------------
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

        Arm arm = new Arm(
                teamHardwareMap.RightExtendServo,
                teamHardwareMap.LeftExtendServo,
                teamHardwareMap.RightArmServo,
                teamHardwareMap.LeftArmServo,
                teamHardwareMap.ClawServo
        );

        ReportDriveMotorStatus(teamHardwareMap, telemetry);

        telemetry.update();
        waitForStart();

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        double motor_power = 1;

        if (isStopRequested()) return;

        while (opModeIsActive()) {
//            telemetry.addData("Centre Odometer", teamHardwareMap.CenterOdometer.getVelocity());
//            telemetry.addData("Left Odometer", teamHardwareMap.LeftOdometer.getVelocity());
//            telemetry.addData("Right Odometer", teamHardwareMap.RightOdometer.getVelocity());

            telemetry.addData("Right Viper Ticks", teamHardwareMap.RightViperMotor.getCurrentPosition());
            telemetry.addData("Left Viper Ticks", teamHardwareMap.LeftViperMotor.getCurrentPosition());

            telemetry.addData("Right Viper Ticks", teamHardwareMap.RightViperMotor.getVelocity());
            telemetry.addData("Left Viper Ticks", teamHardwareMap.LeftViperMotor.getVelocity());

            m.Move(gamepad1);

            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);



            if(currentGamepad2.right_bumper) {
                teamHardwareMap.RightViperMotor.setPower(motor_power);
            }
            else if(currentGamepad2.right_trigger > 0) {
                teamHardwareMap.RightViperMotor.setPower(-motor_power);
            }
            else {
                teamHardwareMap.RightViperMotor.setPower(0);
            }

            if(currentGamepad2.left_bumper) {
                teamHardwareMap.LeftViperMotor.setPower(motor_power);
            }
            else if(currentGamepad2.left_trigger > 0) {
                teamHardwareMap.LeftViperMotor.setPower(-motor_power);
            }
            else {
                teamHardwareMap.LeftViperMotor.setPower(0);
            }


            if(currentGamepad2.cross && !previousGamepad2.cross) {
                arm.ToggleExtension();
            }
            if(currentGamepad2.square && !previousGamepad2.square)
            {
                arm.Claw.ToggleRotation();
            }
            if(currentGamepad2.circle && !previousGamepad2.circle)
            {
                arm.Claw.ToggleClaw();
            }

            ReportAllMotorSpeed(teamHardwareMap, telemetry);
            telemetry.update();
        }
    }
}
