package org.firstinspires.ftc.teamcode.driveropmodes;

import static org.firstinspires.ftc.teamcode.Helper.CopyButtonsFromGamepad;
import static org.firstinspires.ftc.teamcode.Helper.GamepadColour;
import static org.firstinspires.ftc.teamcode.Helper.ReportAllMotorSpeed;
import static org.firstinspires.ftc.teamcode.Helper.ReportCurrentMotorDirection;
import static org.firstinspires.ftc.teamcode.Helper.ReportDriveMotorStatus;
import static org.firstinspires.ftc.teamcode.Helper.SetGamepadLight;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardwaremaps.DeepHardwareMap;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Class for testing of manual movement
 */
@TeleOp(name="Manual Movement", group="Linear OpMode")
public class ManualMovementTeleOp extends LinearOpMode {

    // -----------------------------------------
    // | A / Cross    | Front Right Wheel      |
    // | B / Circle   | Front Left Wheel       |
    // | X / Square   | Back Right Wheel       |
    // | Y / Triangle | Back Left Wheel        |
    // | Right Bumper | All Wheels             |
    // | Left Bumper  | Change Wheel Direction |
    // -----------------------------------------

    // MUST be between 0 and 1 ( incl )
    public final int PowerMultiplier = 1;

    boolean[] prev_buttons, curr_buttons;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        // Create hardware map
        DeepHardwareMap deepHardwareMap = new DeepHardwareMap(hardwareMap);

        // Gamepads to use for rising edge detector
        Gamepad curr_gp = new Gamepad();
        Gamepad prev_gp = new Gamepad();

        DcMotorSimple[] motors = new DcMotorSimple[] {
                deepHardwareMap.FrontRightMotor,
                deepHardwareMap.FrontLeftMotor,
                deepHardwareMap.BackRightMotor,
                deepHardwareMap.BackLeftMotor
        };

        // Direction of motor
        int dir = 1;

        // Make sure all motors are behaving properly
        ReportDriveMotorStatus(deepHardwareMap, telemetry);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            SetupGamepads(prev_gp, curr_gp);

            // If pressing left bump for first time then change dir
            if(curr_gp.left_bumper && !prev_gp.left_bumper) {
                ConfigureGamepadLight(dir *= -1);
                ReportCurrentMotorDirection(telemetry, dir);
            }

            if(curr_gp.right_bumper) {
                AllMotorsPower(motors, dir * PowerMultiplier);
            }

            for(int i = 0; i < motors.length; i++) {
                SetMotorPowerIfButton(motors[i], prev_buttons[i], curr_buttons[i], dir * PowerMultiplier);
            }

            ReportAllMotorSpeed(deepHardwareMap, telemetry);
        }
    }

    /**
     * Set gamepad light to appropriate lighting
     * @param dir Direction to change based on
     */
    private void ConfigureGamepadLight(int dir) {
        SetGamepadLight(gamepad1, dir == 1 ? GamepadColour.BLUE : GamepadColour.RED);
    }

    private void SetupGamepads(Gamepad prev_gp, Gamepad current_gp) {
        prev_gp.copy(current_gp);
        prev_buttons = CopyButtonsFromGamepad(prev_gp);

        current_gp.copy(gamepad1);
        curr_buttons = CopyButtonsFromGamepad(current_gp);
    }

    private void AllMotorsPower(DcMotorSimple[] motors, int power) {
        Arrays.stream(motors)
                .forEach(x -> x.setPower(power));
    }

    private void SetMotorPowerIfButton(DcMotorSimple motor, boolean previous_button, boolean current_button, int direction) {
        if(current_button) {
            motor.setPower(direction);
        }
        if(previous_button && !current_button) {
            motor.setPower(0);
        }
    }
}