package digitalPancakes.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import digitalPancakes.HardwareMap;
import digitalPancakes.helpers.ArmExtension;
import digitalPancakes.helpers.Mecanum;
import digitalPancakes.helpers.Arm;

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

@TeleOp(name="Competition Movement", group="Linear OpMode")
public class CompetitionOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        // Create hardware map
        HardwareMap teamHardwareMap = new HardwareMap(hardwareMap);

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

            if(currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
                extension.Textend(6000);
                isTargetMax = true;
            }
            if(currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {
                extension.Textend(0);
                isTargetMax = false;
            }

            if(currentGamepad2.triangle && !previousGamepad2.triangle) {
                if(isTargetMax) {
                    extension.Textend(0);
                } else {
                    extension.Textend(6000);
                }

                isTargetMax = !isTargetMax;
            }

            // Viper Slide Extension
//            if(currentGamepad2.right_bumper) {
//                telemetry.addLine("Pressing Right Bumper");
//                extension.Extend();
//            }  else if(currentGamepad2.left_bumper) {
//                telemetry.addLine("Pressing Left Bumper");
//                extension.Dextend();
//            } else {
//                telemetry.addLine("No bumper pressed");
//                extension.Nextend();
//            }

            // Viper Slide Encoder Ticks
            telemetry.addData("Right Viper Slide Encoder Ticks", teamHardwareMap.RightViperMotor.getCurrentPosition());
            telemetry.addData("Left Viper Slide Encoder Ticks", teamHardwareMap.LeftViperMotor.getCurrentPosition());

            // Arm Extension
            if(currentGamepad2.cross && !previousGamepad2.cross) {
                arm.ToggleExtension();
            }

            // Claw Rotation
            if(currentGamepad2.square && !previousGamepad2.square)
            {
                arm.getClaw().ToggleRotation();
            }

            // Claw Closing
            if(currentGamepad2.circle && !previousGamepad2.circle)
            {
                arm.getClaw().ToggleClaw();
            }

            telemetry.update();
        }
    }
}