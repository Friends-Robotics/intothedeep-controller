package digitalPancakes.helpers;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {

    private final Servo rightArmServo;
    private final Servo leftArmServo;
    private final Servo clawServo;
    private boolean isDown;
    private boolean isOpen;

    public Claw(Servo right_arm_servo, Servo left_arm_servo, Servo claw_servo) {
        rightArmServo = right_arm_servo;
        leftArmServo = left_arm_servo;
        clawServo = claw_servo;
        isDown = false;
        isOpen = false;
        rightArmServo.setPosition(0);
        leftArmServo.setPosition(0);
        clawServo.setPosition(0.9);
    }

    public void toggleRotation() {
        if(!isDown) {
            rightArmServo.setPosition(1);
            leftArmServo.setPosition(1);
        } else {
            rightArmServo.setPosition(0);
            leftArmServo.setPosition(0);
        }
        isDown = !isDown;
    }

    public void armDown() {
        rightArmServo.setPosition(0);
        leftArmServo.setPosition(0);
    }

    public void armUp() {
        rightArmServo.setPosition(1);
        leftArmServo.setPosition(1);
    }

    public void toggleClaw() {
        if(!isOpen) {
            clawServo.setPosition(0.9);
            isOpen = true;
        } else {
            clawServo.setPosition(0.5);
            isOpen = false;
        }
    }

    public void clawClose() {
        clawServo.setPosition(0.5);
    }

    public void clawOpen() {
        clawServo.setPosition(0.8);
    }
}