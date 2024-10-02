package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import static org.firstinspires.ftc.teamcode.Helper.*;

/**
 Class designed to provide helper methods to operate mecanum wheels
 */
public class Mecanum {
    private DcMotorSimple frontRightMotor,
        frontLeftMotor,
        backRightMotor,
        backLeftMotor;

    public double PowerMultiplier = 1;
    public Telemetry telemetry = null;

    /**
     * Creates the Mecanum object. Sets private fields and configures motor directions.
     * @param frontRight Front Right Motor Object
     * @param frontLeft Front Left Motor Object
     * @param backRight Back Right Motor Object
     * @param backLeft Back Left Motor Object
     * @return A new Mecanum object
     */
    public static Mecanum Init(DcMotorSimple frontRight, DcMotorSimple frontLeft, DcMotorSimple backRight, DcMotorSimple backLeft, double power) {
        Mecanum m = new Mecanum();
        m.frontRightMotor = frontRight;
        m.frontLeftMotor = frontLeft;
        m.backRightMotor = backRight;
        m.backLeftMotor = backLeft;
        m.PowerMultiplier = power;
        return m;
    }

    public MotorState correctState;
    private int[] correctStateCount = new int[] {0, 0, 0, 0};
    private int CORRECTSTATELEN = 10;

    /**
     * Analyses gamepad and sets power of motors appropriately
     * @param gp Gamepad object
     */
    public void Move(Gamepad gp, MotorState prev, MotorState curr) {
        // If invalid power multiplier range is provided then just set value to 1
        if (PowerMultiplier < 0 || PowerMultiplier > 1) {
            ReportIfTelemetry(telemetry, "Power Multiplier should be between 0 and 1", "Power Multiplier defaulting to 1");
            PowerMultiplier = 1;
        }

        PowerMultiplier = (-gp.left_stick_y + 1) / 2;
        telemetry.addData("Current Power", PowerMultiplier);

        // Y values need to be inverted
        double y = -gp.right_stick_y;
        double x = gp.right_stick_x;
        // double rx = -gp.left_stick_y;
        double rx = 0;

        if (gp.right_bumper) {
            rx = -1;
        } else if (gp.left_bumper) {
            rx = 1;
        }

        if(gp.right_trigger > 0) {
            rx = Math.max(-gp.right_trigger + 0.4, 1);
        }
        if(gp.left_trigger > 0) {
            rx = Math.max(gp.left_trigger + 0.4, 1);
        }

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontRightPower = (y - x - rx) / denominator;
        double frontLeftPower = (y + x + rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;

        if (!prev.IsFrontRightZero() && curr.IsFrontRightZero()) {
            if (correctStateCount[0] == 0) {
                correctStateCount[0] = CORRECTSTATELEN;
                correctState.frontRight = -1 * prev.frontRight;
            }
            correctStateCount[0]--;
            frontRightPower = correctState.frontRight;
        }

        if (!prev.IsFrontLeftZero() && curr.IsFrontLeftZero()){
            if (correctStateCount[1] == 0) {
                correctStateCount[1] = CORRECTSTATELEN;
                correctState.frontLeft = -1 * prev.frontLeft;
            }
            correctStateCount[1]--;
            frontLeftPower = correctState.frontLeft;
        }

        if(!prev.IsBackRightZero() && curr.IsBackRightZero()) {
            if (correctStateCount[2] == 0) {
                correctStateCount[2] = CORRECTSTATELEN;
                correctState.backRight = -1 * prev.backRight;
            }
            correctStateCount[2]--;
            backRightPower = correctState.backRight;
        }

        if(!prev.IsBackLeftZero() && curr.IsBackLeftZero()) {
            if (correctStateCount[3] == 0) {
                correctStateCount[3] = CORRECTSTATELEN;
                correctState.backLeft = -1 * prev.backLeft;
            }
            correctStateCount[3]--;
            backLeftPower = correctState.backLeft;
        }

        if(correctStateCount[0] == 0) correctState.frontRight = -2;
        if(correctStateCount[1] == 0) correctState.frontLeft = -2;
        if(correctStateCount[2] == 0) correctState.backRight = -2;
        if(correctStateCount[3] == 0) correctState.backLeft = -2;

        // frontRightMotor.setPower(correctState.frontRight != -2 ? correctState.frontRight : frontRightPower * PowerMultiplier);
        // frontLeftMotor.setPower(correctState.frontLeft != -2 ? correctState.frontLeft : frontLeftPower * PowerMultiplier);
        // backRightMotor.setPower(correctState.backRight != -2 ? correctState.backRight : backRightPower * PowerMultiplier);
        // backLeftMotor.setPower(correctState.backLeft != -2 ? correctState.backLeft : backLeftPower * PowerMultiplier);

        frontRightMotor.setPower(frontRightPower * PowerMultiplier);
        frontLeftMotor.setPower(frontLeftPower * PowerMultiplier);
        backRightMotor.setPower(backRightPower * PowerMultiplier);
        backLeftMotor.setPower(backLeftPower * PowerMultiplier);
    }
}
