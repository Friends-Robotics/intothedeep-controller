package org.firstinspires.ftc.teamcode.hardwaremaps;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Class to provide a helpful abstraction layer for accessing the HardwareMap
 */
public class DeepHardwareMap {
    /*
        -----------------------------------------------------------------------
        | FRW               | Front Right Wheel     | Control Hub Motor 3     |
        --------------------+-----------------------+--------------------------
        | FLW               | Front Left Wheel      | Control Hub Motor 2     |
        --------------------+-----------------------+--------------------------
        | BRW               | Back Right Wheel      | Control Hub Motor 0     |
        --------------------+-----------------------+--------------------------
        | BLW               | Back Left Wheel       | Control Hub Motor 1     |
        --------------------+-----------------------+--------------------------
        | RIGHT_ODOMETER    | Right Odometer        | None                    |
        --------------------+-----------------------+--------------------------
        | LEFT_ODOMETER     | Left Odometer         | None                    |
        --------------------+-----------------------+--------------------------
        | CENTRE_ODOMETER   | Centre Odometer       | None                    |
        -----------------------------------------------------------------------
     */

    private final HardwareMap hardwareMap;

    // Setup and configure all drive motors
    public DcMotorSimple FrontRightMotor;
    public DcMotorSimple FrontLeftMotor;
    public DcMotorSimple BackRightMotor;
    public DcMotorSimple BackLeftMotor;

    // Setup and configure all odometers
    // public DcMotorEx RightOdometerMotor;
    // public DcMotorEx LeftOdometerMotor;
    // public DcMotorEx CentreOdometerMotor;

    public DeepHardwareMap(HardwareMap hardwaremap) {
        hardwareMap = hardwaremap;
        initialise();
    }

    public void initialise() {
        FrontRightMotor = ConfigureMovementMotor(FrontRightMotor, "FRW");
        FrontLeftMotor = ConfigureMovementMotor(FrontLeftMotor, "FLW");
        BackRightMotor = ConfigureMovementMotor(BackRightMotor, "BRW");
        BackLeftMotor = ConfigureMovementMotor(BackLeftMotor, "BLW");

        // Any additional configuration options here
        BackRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    private DcMotorSimple ConfigureMovementMotor(DcMotorSimple motor , String name) {
        motor = hardwareMap.get(DcMotorSimple.class, name);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        return motor;
    }

    private DcMotorEx ConfigureOdometerMotor(String name) {
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, name);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        return motor;
    }
}
