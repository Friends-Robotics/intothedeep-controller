package digitalPancakes.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import digitalPancakes.HardwareMap;
import digitalPancakes.helpers.Claw;
import digitalPancakes.helpers.ViperExtension;
import digitalPancakes.helpers.Mecanum;
import digitalPancakes.helpers.HorizontalExtension;
import digitalPancakes.helpers.Wrist;

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
        HorizontalExtension arm = new HorizontalExtension(
                teamHardwareMap.RightExtendServo,
                teamHardwareMap.LeftExtendServo
        );

        Wrist wrist = new Wrist(teamHardwareMap.RightArmServo, teamHardwareMap.LeftArmServo);

        // Create viper slide controller
        ViperExtension extension = new ViperExtension(
                teamHardwareMap.RightViperMotor,
                teamHardwareMap.LeftViperMotor,
                teamHardwareMap.ViperBucketServo
        );

        Claw claw = new Claw(teamHardwareMap.ClawServo);

        telemetry.update();
        waitForStart();

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        if (isStopRequested()) return;

        boolean out = false;

        double val = 0;

        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            // GAMEPAD 1 CONTROLS

            // Move the mecanum wheels
            m.Move(gamepad1);

            // Mecanum Speed
            if(currentGamepad1.cross) {
                m.PowerMultiplier = 0.5;
                gamepad1.setLedColor(0, 0, 255, Gamepad.LED_DURATION_CONTINUOUS);
            } else if(currentGamepad1.circle) {
                m.PowerMultiplier = 1;
                gamepad1.setLedColor(0, 255, 0, Gamepad.LED_DURATION_CONTINUOUS);
            }




            // GAMEPAD 2 CONTROLS

            // Viper Slide
            if(currentGamepad2.right_bumper) {
                extension.Extend();
            } else if(currentGamepad2.left_bumper) {
                extension.Dextend();
            } else {
                extension.Nextend();
            }

            if(currentGamepad2.cross && !previousGamepad2.cross) {
                if(arm.IsExtended()) {
                    arm.extensionIn();
                    wrist.up();
                } else {
                    arm.extensionOut();
                    wrist.down();
                    claw.clawOpen();
                }
            }

            // Opening Bucket
            if(currentGamepad2.triangle && !previousGamepad2.triangle) {
                extension.toggleBucket();
            }

            if(currentGamepad2.square && !previousGamepad2.square) {
                if(wrist.getDown()) {
                    wrist.up();
                } else {
                    wrist.down();
                }
            }

            // Claw Closing
            if(currentGamepad2.circle && !previousGamepad2.circle)
            {
                if(claw.IsOpen()) {
                    claw.clawClose();
                } else {
                    claw.clawOpen();
                }
            }

            if(currentGamepad1.triangle && !previousGamepad1.triangle) {
                // Start macro

                claw.clawOpen();
                sleep(200);
                extension.depositBucket();
                sleep(200);

                arm.setExtension(0.3);
                sleep(200);
                wrist.max();
                claw.setClaw(0.35);
                sleep(600);
                claw.clawClose();

                arm.extensionOut();
                sleep(200);

                wrist.up();
                sleep(200);

                extension.receiveBucket();
                sleep(300);

                arm.extensionIn();
            }

            if(currentGamepad1.square && !previousGamepad1.square) {
                // Start macro
                claw.clawOpen();
                sleep(200);

                arm.setExtension(0.3);
                sleep(200);
                wrist.max();
                claw.setClaw(0.35);
                sleep(600);
                claw.clawClose();

                arm.extensionOut();
                sleep(200);

                wrist.up();
                sleep(200);

                arm.extensionIn();
            }

            telemetry.addData("Claw is open", claw.IsOpen());
            telemetry.addData("Current Rotation Value", val);
            telemetry.addData("left ticks: ", teamHardwareMap.LeftViperMotor.getCurrentPosition());
            telemetry.addData("right ticks: ", teamHardwareMap.RightViperMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}