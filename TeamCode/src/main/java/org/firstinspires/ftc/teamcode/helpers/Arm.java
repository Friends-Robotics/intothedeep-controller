package org.firstinspires.ftc.teamcode.helpers;
import com.qualcomm.robotcore.hardware.Servo;
public class Arm {
    private Servo RightExtendServo;
    private Servo LeftExtendServo;
    public Claw Claw;
    private boolean isExtended;
    public Arm(Servo RES, Servo LES, Servo RAS, Servo LAS, Servo CLS)
    {
        RightExtendServo = RES;
        LeftExtendServo = LES;
        Claw = new Claw(RAS,LAS,CLS);
        isExtended = false;
        RightExtendServo.setPosition(0);
        LeftExtendServo.setPosition(0);
    }

    public void ToggleExtension()
    {
        if(!isExtended){
            RightExtendServo.setPosition(0.4);
            LeftExtendServo.setPosition(0.4);
            isExtended = true;
        }else{
            RightExtendServo.setPosition(0);
            LeftExtendServo.setPosition(0);
            isExtended = false;
        }
    }
}
