package org.firstinspires.ftc.teamcode.helpers;
import com.qualcomm.robotcore.hardware.Servo;
public class Claw {
    private Servo RightArmServo;
    private Servo LeftArmServo;
    private Servo ClawServo;
    private boolean isDown;
    private boolean isOpen;
    public Claw(Servo RAS, Servo LAS, Servo CLS){
        RightArmServo = RAS;
        LeftArmServo = LAS;
        ClawServo = CLS;
        isDown = false;
        isOpen = false;
        RightArmServo.setPosition(0);
        LeftArmServo.setPosition(0);
        ClawServo.setPosition(0.9);
    }

    public void ToggleRotation(){
        if(!isDown){
            RightArmServo.setPosition(1);
            LeftArmServo.setPosition(1);
            isDown = true;
        }else{
            RightArmServo.setPosition(0);
            LeftArmServo.setPosition(0);
            isDown = false;
        }
    }

    public void ToggleClaw(){
        if(!isOpen){
            ClawServo.setPosition(0.9);
            isOpen = true;
        }else{
            ClawServo.setPosition(0);
            isOpen = false;
        }
    }
}
