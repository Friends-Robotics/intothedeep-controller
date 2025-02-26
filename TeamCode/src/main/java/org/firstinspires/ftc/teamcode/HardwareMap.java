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
        | RO                | Right Odometer        | Control Hub Encoder 2   |
        --------------------+-----------------------+--------------------------
        | LO                | Left Odometer         | Control Hub Encoder 0   |
        --------------------+-----------------------+--------------------------
        | CO                | Centre Odometer       | Control Hub Encoder 1   |
        -----------------------------------------------------------------------
        | RVM               | Right Viper Motor     | Extension Hub Motor 0   |
        -----------------------------------------------------------------------
        | LVM               | Left Viper Motor      | Extension Hub Motor 1   |
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

    public DcMotorSimple FrontRightMotor;
    public DcMotorSimple FrontLeftMotor;
    public DcMotorSimple BackRightMotor;
    public DcMotorSimple BackLeftMotor;

    public DcMotorEx RightOdometerMotor;
    public DcMotorEx LeftOdometerMotor;
    public DcMotorEx CentreOdometerMotor;

    public DcMotorEx RightViperMotor;
    public DcMotorEx LeftViperMotor;

    public Servo RightExtendServo;
    // public Servo LeftExtendServo;

    public Servo ViperBucketServo;
    public Servo RightArmServo;
    public Servo LeftArmServo;
    public Servo ClawServo;

    public HardwareMap(com.qualcomm.robotcore.hardware.HardwareMap hardwaremap) {

        FrontRightMotor = hardwaremap.get(DcMotorSimple.class, "FRW");
        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontLeftMotor = hardwaremap.get(DcMotorSimple.class, "FLW");
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BackRightMotor = hardwaremap.get(DcMotorSimple.class, "BRW");
        BackRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BackLeftMotor = hardwaremap.get(DcMotorSimple.class, "BLW");
        BackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

//        LeftOdometerMotor = hardwaremap.get(DcMotorEx.class, "LO");
//        LeftOdometerMotor.setDirection((DcMotorSimple.Direction.REVERSE));
//        RightOdometerMotor = hardwaremap.get(DcMotorEx.class, "RO");
//        RightOdometerMotor.setDirection((DcMotorSimple.Direction.REVERSE));
//        CentreOdometerMotor = hardwaremap.get(DcMotorEx.class, "CO");
//        CentreOdometerMotor.setDirection((DcMotorSimple.Direction.REVERSE));
//
        RightViperMotor = hardwaremap.get(DcMotorEx.class, "RVM");
        RightViperMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightViperMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftViperMotor = hardwaremap.get(DcMotorEx.class, "LVM");
        LeftViperMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        LeftViperMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RightExtendServo = hardwaremap.get(Servo.class, "RES");
        // LeftExtendServo = hardwaremap.get(Servo.class, "LES");

        ViperBucketServo = hardwaremap.get(Servo.class, "VBS");
        RightArmServo = hardwaremap.get(Servo.class, "RAS");
        LeftArmServo = hardwaremap.get(Servo.class, "LAS");
        ClawServo = hardwaremap.get(Servo.class, "CLS");
    }
}
