package digitalPancakes.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import digitalPancakes.HardwareMap;
import digitalPancakes.helpers.ArmExtension;
import digitalPancakes.helpers.Mecanum;
import digitalPancakes.helpers.Arm;

//        -------------------------------------------------------------------------------
//        | Controller Button     | Description           | Motor/Servo Affected        |
//        -------------------------------------------------------------------------------
//
//        -------------------------------------------------------------------------------
//        | Primary  : Left Stick | Field Movement        | All Movement Motors         |
//        -------------------------------------------------------------------------------
//        | Primary  : Right Stick| Field Rotation        | All Movement Motors         |
//        -------------------------------------------------------------------------------
//        | Secondary: Right Stick| Field Rotation        | All Movement Motors         |
//        -------------------------------------------------------------------------------
//        | Secondary: Right Stick| Field Rotation        | All Movement Motors         |
//        -------------------------------------------------------------------------------
//        | Secondary: Right Stick| Field Rotation        | All Movement Motors         |
//        -------------------------------------------------------------------------------
//        | Secondary: A/Cross    | Extend Claw Forward   | All extension Macro         |
//        -------------------------------------------------------------------------------
//        | Secondary: B/Circle   | Open / Close Claw     | Claw Servo                  |
//        -------------------------------------------------------------------------------
//        | Secondary: X/Square   | Claw Rotation         | Right Arm Servo             |
//        -------------------------------------------------------------------------------
//        | Secondary: Y/Triangle | Toggle Precision Mode | N/A                         |
//        -------------------------------------------------------------------------------
//        | Secondary: Y/Triangle | Toggle Precision Mode | N/A                         |
//        -------------------------------------------------------------------------------
//        | Secondary: Y/Triangle | Toggle Precision Mode | N/A                         |
//        -------------------------------------------------------------------------------

@TeleOp(name="Competition Movement", group="Linear OpMode")
public class CompetitionOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        // Create hardware map
        HardwareMap teamHardwareMap = new HardwareMap(hardwareMap);

        // Create mecanum drive
        Mecanum m = Mecanum.Init(
                teamHardwareMap.FrontRightMotor,
                teamHardwareMap.FrontLeftMotor,
                teamHardwareMap.BackRightMotor,
                teamHardwareMap.BackLeftMotor,
                0.5
        );

        // Create arm controller
        Arm arm = new Arm(
                teamHardwareMap.RightExtendServo,
                teamHardwareMap.LeftExtendServo,
                teamHardwareMap.RightArmServo,
                teamHardwareMap.LeftArmServo,
                teamHardwareMap.ClawServo
        );

        // Create viper slide controller
        ArmExtension extension = new ArmExtension(
                teamHardwareMap.RightViperMotor,
                teamHardwareMap.LeftViperMotor
        );

        telemetry.update();
        waitForStart();

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        boolean isTargetMax = false;

        if (isStopRequested()) return;

        ElapsedTime time = new ElapsedTime();
        time.reset();

        boolean out = false;

        double arm_extend_time = 0.5;
        double extend_extend_time = 0.5;
        double claw_extend_time = 0.25;
        double macro_extend_time = 1;

        double macro_upwards_extend  = 0.5;

        double macro_time = 0.5;
        double arm_time = 0.5;
        double extend_time = 0.5;
        double claw_time = 0.25;

        while (opModeIsActive()) {
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            telemetry.addData("Is Target Max: ", isTargetMax);

            // Mecanum Speed
            if(currentGamepad1.cross) {
                m.PowerMultiplier = 0.5;
                gamepad1.setLedColor(0, 0, 255, Gamepad.LED_DURATION_CONTINUOUS);
            } else if(currentGamepad1.circle) {
                m.PowerMultiplier = 1;
                gamepad1.setLedColor(0, 255, 0, Gamepad.LED_DURATION_CONTINUOUS);
            }

            // Move the mecanum wheels
            m.Move(gamepad1);

            // Viper
            if(currentGamepad2.right_bumper && (time.time() - macro_upwards_extend) > 0.5) {
                extension.Textend(5550);
                macro_upwards_extend = time.time();
            }

            if(currentGamepad2.left_bumper && (time.time() - macro_upwards_extend) > 0.5) {
                extension.Textend(0);
                macro_upwards_extend = time.time();
            }

            // Viper Slide Encoder Ticks
            telemetry.addData("Right Viper Slide Encoder Ticks", teamHardwareMap.RightViperMotor.getCurrentPosition());
            telemetry.addData("Left Viper Slide Encoder Ticks", teamHardwareMap.LeftViperMotor.getCurrentPosition());

            telemetry.addData("Macro Extension Enabled", (time.time() - macro_time) > macro_extend_time);
            telemetry.addData("Extension Extension Enabled", (time.time() - extend_time) > extend_extend_time);
            telemetry.addData("Claw Extension Enabled", (time.time() - claw_time) > claw_extend_time);

            // Arm Extension
            if(currentGamepad2.cross && (time.time() - macro_time) > macro_extend_time) {
                arm.toggleExtension(1f);
                arm.getClaw().toggleRotation();
                if(!out) {
                    arm.getClaw().clawClose();
                } else {
                    arm.getClaw().clawOpen();
                }
                macro_time = time.time();
                out = !out;
            }

            // Arm Extension
            if(currentGamepad2.dpad_down && (time.time() - macro_time) > macro_extend_time) {
                arm.toggleExtension(0.5f);
                arm.getClaw().toggleRotation();
                if(!out) {
                    arm.getClaw().clawClose();
                } else {
                    arm.getClaw().clawOpen();
                }
                macro_time = time.time();
                out = !out;
            }

            // Arm Extension
            if(currentGamepad2.dpad_up && (time.time() - macro_time) > macro_extend_time) {
                arm.toggleExtension(0.25f);
                arm.getClaw().toggleRotation();
                if(!out) {
                    arm.getClaw().clawClose();
                } else {
                    arm.getClaw().clawOpen();
                }
                macro_time = time.time();
                out = !out;
            }

            telemetry.addData("OUT", out);

            // Claw Rotation
            if(currentGamepad2.square && (time.time() - arm_time) > arm_extend_time) {
                arm.getClaw().toggleRotation();
                arm_time = time.time();
            }

            // Claw Closing
            if(currentGamepad2.circle  && (time.time() - claw_time) > claw_extend_time)
            {
                arm.getClaw().toggleClaw();
                claw_time = time.time();
            }

            telemetry.addData("left ticks: ", teamHardwareMap.LeftViperMotor.getCurrentPosition());
            telemetry.addData("right ticks: ", teamHardwareMap.RightViperMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}