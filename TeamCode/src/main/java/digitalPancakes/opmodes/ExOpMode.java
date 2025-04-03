package digitalPancakes.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import digitalPancakes.helpers.GamepadEx;
import static digitalPancakes.helpers.GamepadEx.GamepadButton.*;
import static digitalPancakes.helpers.GamepadEx.Primary;
import static digitalPancakes.helpers.GamepadEx.Secondary;

import digitalPancakes.HardwareMap;
import digitalPancakes.helpers.HorizontalExtension;
import digitalPancakes.helpers.ViperExtension;
import digitalPancakes.helpers.Mecanum;

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

@TeleOp(name="Ex Op Mode", group="Linear OpMode")
public class ExOpMode extends LinearOpMode {
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
        HorizontalExtension arm = new HorizontalExtension(
                teamHardwareMap.RightExtendServo,
                teamHardwareMap.LeftExtendServo
        );

        // Create viper slide controller
        ViperExtension extension = new ViperExtension(
                teamHardwareMap.RightViperMotor,
                teamHardwareMap.LeftViperMotor,
                teamHardwareMap.ViperBucketServo
        );

        GamepadEx.init_gamepads(gamepad1, gamepad2);

        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        // Normal Speed
        Primary.bind(A, (a, b) -> {
            m.PowerMultiplier = 0.5;
            gamepad1.setLedColor(0, 0, 255, Gamepad.LED_DURATION_CONTINUOUS);
        });

        // Fast Speed
        Primary.bind(B, (a, b) -> {
            m.PowerMultiplier = 1;
            gamepad1.setLedColor(0, 255, 0, Gamepad.LED_DURATION_CONTINUOUS);
        });


        // Extend viper slide to fully extended position
        Secondary.bind(RIGHT_BUMPER, (c, p) -> {
            telemetry.addLine("Clicked Right Bumper");
            if(p.right_bumper) return;
            extension.Textend(5550);
        });

        // Extend viper slide to resting
        Secondary.bind(LEFT_BUMPER, (c, p) -> {
            if(p.left_bumper) return;
            extension.Textend(0);
        });

        // Full Arm Macro
        Secondary.bind(A, (c, p) -> {
            telemetry.addLine("Clicked A");
            if(p.a) return;
            telemetry.addLine("Not a prev");
        });

        // Toggle Claw Grab
        Secondary.bind(B, (c, p) -> {
            if(p.b) return;
            arm.getClaw().toggleClaw();
        });

        Secondary.bind(Y, (c, p) -> {
            if(p.y) return;
            extension.toggleBucket();
        });

        while (opModeIsActive()) {
            // Move the mecanum wheels
            m.Move(gamepad1);

            // Update Gamepads
            Primary.update(gamepad1);
            Secondary.update(gamepad2);

            telemetry.update();
        }
    }
}