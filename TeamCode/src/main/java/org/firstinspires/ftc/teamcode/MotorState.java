package org.firstinspires.ftc.teamcode;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.hardwaremaps.DeepHardwareMap;

public class MotorState {
    @Nullable
    public double frontRight;
    public boolean frontRightToggle = false;
    @Nullable
    public double frontLeft;
    public boolean frontLeftToggle = false;
    @Nullable
    public double backRight;
    public boolean backRightToggle = false;
    @Nullable
    public double backLeft;
    public boolean backLeftToggle = false;

    public MotorState(DeepHardwareMap hardwareMap) {
        frontRight = hardwareMap.FrontRightMotor.getPower();
        frontLeft = hardwareMap.FrontLeftMotor.getPower();
        backRight = hardwareMap.BackRightMotor.getPower();
        backLeft = hardwareMap.BackLeftMotor.getPower();
    }

    public MotorState(double fr, double fl, double br, double bl) {
        frontRight = fr;
        frontLeft = fl;
        backRight = br;
        backLeft = bl;
    }

    public MotorState(double fr, double fl, double br, double bl, boolean fr_toggle, boolean fl_toggle, boolean br_toggle, boolean bl_toggle) {
        frontRight = fr;
        frontLeft = fl;
        backRight = br;
        backLeft = bl;
        frontRightToggle = fr_toggle;
        frontLeftToggle = fl_toggle;
        backRightToggle = br_toggle;
        backLeftToggle = bl_toggle;
    }

    public void update(DeepHardwareMap hardwareMap) {
        frontRight = hardwareMap.FrontRightMotor.getPower();
        frontLeft = hardwareMap.FrontLeftMotor.getPower();
        backRight = hardwareMap.BackRightMotor.getPower();
        backLeft = hardwareMap.BackLeftMotor.getPower();
    }

    public MotorState copy() {
        return new MotorState(frontRight, frontLeft, backRight, backLeft);
    }

    public void ToggleFrontRight() {
        frontRightToggle = !frontRightToggle;
    }
    public void ToggleFrontLeft() {
        frontLeftToggle = !frontLeftToggle;
    }
    public void ToggleBackRight() {
        backRightToggle = !backRightToggle;
    }
    public void ToggleBackLeft() {
        backLeftToggle = !backLeftToggle;
    }

    public boolean IsFrontRightZero() {
        return frontRight == 0;
    }

    public boolean IsFrontLeftZero() {
        return frontLeft == 0;
    }

    public boolean IsBackRightZero() {
        return backRight == 0;
    }

    public boolean IsBackLeftZero() {
        return backLeft == 0;
    }
}
