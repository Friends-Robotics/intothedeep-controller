package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Class to provide a helpful abstraction layer for accessing the HardwareMap
 */
public class HardwareMap {
    /*
        -----------------------------------------------------------------------
        | FRW               | Front Right Wheel     | Control Hub Motor 3     |
        --------------------+-----------------------+--------------------------
        | FLW               | Front Left Wheel      | Control Hub Motor 0     |
        --------------------+-----------------------+--------------------------
        | BRW               | Back Right Wheel      | Control Hub Motor 2     |
        --------------------+-----------------------+--------------------------
        | BLW               | Back Left Wheel       | Control Hub Motor 1     |
        --------------------+-----------------------+--------------------------
        | RO                | Right Odometer        | Control Hub Encoder 0   |
        --------------------+-----------------------+--------------------------
        | LO                | Left Odometer         | Control Hub Encoder 1   |
        --------------------+-----------------------+--------------------------
        | CO                | Centre Odometer       | Control Hub Encoder 2   |
        -----------------------------------------------------------------------
        | RVM               | Right Viper Motor     | Extension Hub Motor X   |
        -----------------------------------------------------------------------
        | LVM               | Left Viper Motor      | Extension Hub Motor X   |
        -----------------------------------------------------------------------
        | VBS               | Viper Bucket Servo    | Control Hub Servo 1     |
        -----------------------------------------------------------------------
        | RES               | Right Extend Servo    | Control Hub Servo 0     |
        -----------------------------------------------------------------------
        | LES               | Left Extend Servo     | Control Hub Servo 2     |
        -----------------------------------------------------------------------
        | RAS               | Right Arm Servo       | Control Hub Servo 3     |
        -----------------------------------------------------------------------
        | LAS               | Left Arm Servo        | Control Hub Servo 4     |
        -----------------------------------------------------------------------
        | CLS               | Claw Servo            | Control Hub Servo 5     |
        -----------------------------------------------------------------------
     */

    private final com.qualcomm.robotcore.hardware.HardwareMap hardwareMap;

    // Setup and configure all drive motors
    // public DcMotorSimple FrontRightMotor;
//    public DcMotorSimple FrontLeftMotor;
//    public DcMotorSimple BackRightMotor;
//    public DcMotorSimple BackLeftMotor;

    // Setup and configure all odometers
    public DcMotorEx RightOdometerMotor;
    public DcMotorEx LeftOdometerMotor ;
    public DcMotorEx CentreOdometerMotor ;

    public DcMotorSimple RightViperMotor;
    public DcMotorSimple LeftViperMotor;

    public Servo RightExtendServo;
    // public Servo LeftExtendServo;

    public Servo ViperBucketServo;
    public Servo RightArmServo;
    public Servo LeftArmServo;
    public Servo ClawServo;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap hardwaremap) {
//        FrontRightMotor = hardwaremap.get(DcMotorSimple.class, "FRW");
//        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        FrontLeftMotor = hardwaremap.get(DcMotorSimple.class, "FLW");
//        FrontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        BackRightMotor = hardwaremap.get(DcMotorSimple.class, "BRW");
//        BackRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        BackLeftMotor = hardwaremap.get(DcMotorSimple.class, "BLW");
//        BackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
        // LeftOdometerMotor = hardwaremap.get(DcMotorEx.class, "LO");
        // LeftOdometerMotor.setDirection((DcMotorSimple.Direction.REVERSE));
        // RightOdometerMotor = hardwaremap.get(DcMotorEx.class, "RO");
        // RightOdometerMotor.setDirection((DcMotorSimple.Direction.REVERSE));
        // CentreOdometerMotor = hardwaremap.get(DcMotorEx.class, "CO");
        // CentreOdometerMotor.setDirection((DcMotorSimple.Direction.REVERSE));

        RightViperMotor = hardwaremap.get(DcMotorSimple.class, "RVM");
        RightViperMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftViperMotor = hardwaremap.get(DcMotorSimple.class, "LVM");
        LeftViperMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        RightExtendServo = hardwaremap.get(Servo.class, "RES");
        // LeftExtendServo = hardwaremap.get(Servo.class, "LES");

        ViperBucketServo = hardwaremap.get(Servo.class, "VBS");
        RightArmServo = hardwaremap.get(Servo.class, "RAS");
        RightArmServo.setDirection(Servo.Direction.REVERSE);
        LeftArmServo = hardwaremap.get(Servo.class, "LAS");
        RightArmServo.setDirection(Servo.Direction.FORWARD);
        ClawServo = hardwaremap.get(Servo.class, "CLS");
        hardwareMap = hardwaremap;
        initialise();
    }

    public void initialise() {
        // Any additional configuration options here
        // FrontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    private DcMotorSimple ConfigureMovementMotor(String name, DcMotorSimple.Direction dir) {
        DcMotorSimple motor = hardwareMap.get(DcMotorSimple.class, name);
        motor.setDirection(dir);
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
