package digitalPancakes.helpers;

import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    private final Servo rightExtendServo;
    private final Servo leftExtendServo;
    private final Claw claw;
    private boolean isExtended;

    public Arm(Servo right_extension_servo, Servo left_extension_servo,
               Servo right_arm_servo, Servo left_arm_servo, Servo claw_servo) {
        rightExtendServo = right_extension_servo;
        leftExtendServo = left_extension_servo;
        claw = new Claw(right_arm_servo,left_arm_servo,claw_servo);
        isExtended = false;
        rightExtendServo.setPosition(0);
        leftExtendServo.setPosition(0);
    }

    public void toggleExtension(float percentage) {
        if(!isExtended) {
            rightExtendServo.setPosition(0.4 * percentage);
            leftExtendServo.setPosition(0.4 * percentage);
        } else {
            rightExtendServo.setPosition(0);
            leftExtendServo.setPosition(0);
        }
        isExtended = !isExtended;
    }

    public void extensionOut() {
        rightExtendServo.setPosition(1);
        leftExtendServo.setPosition(1);
    }

    public void extensionIn() {
        rightExtendServo.setPosition(0.4);
        leftExtendServo.setPosition(0.4);
    }

    public Claw getClaw() {
        return claw;
    }
}