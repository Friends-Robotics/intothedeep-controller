package org.firstinspires.ftc.teamcode.helpers;
import com.qualcomm.robotcore.hardware.Servo;
public class Arm {
    private final Servo RightExtendServo;
    private final Servo LeftExtendServo;
    public final Claw Claw;
    private boolean isExtended;

    public Arm(Servo rightExtensionServo, Servo leftExtensionServo,
               Servo rightArmServo, Servo leftArmServo, Servo clawServo) {
        RightExtendServo = rightExtensionServo;
        LeftExtendServo = leftExtensionServo;
        Claw = new Claw(rightArmServo,leftArmServo,clawServo);
        isExtended = false;
        RightExtendServo.setPosition(0);
        LeftExtendServo.setPosition(0);
    }

    public void ToggleExtension() {
        if(!isExtended){
            RightExtendServo.setPosition(0.4);
            LeftExtendServo.setPosition(0.4);
            isExtended = true;
        }
        else {
            RightExtendServo.setPosition(0);
            LeftExtendServo.setPosition(0);
            isExtended = false;
        }
    }

    public Claw getClaw() {
        return Claw;
    }
}
