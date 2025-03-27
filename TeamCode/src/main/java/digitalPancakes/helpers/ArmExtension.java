package digitalPancakes.helpers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class ArmExtension {

    private final DcMotorEx RightViper;
    private final DcMotorEx LeftViper;

    public ArmExtension(DcMotorEx Right, DcMotorEx Left) {
        RightViper = Right;
        LeftViper = Left;

        RightViper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftViper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        RightViper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        LeftViper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        RightViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        LeftViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RightViper.setTargetPosition(0);
        LeftViper.setTargetPosition(0);

        RightViper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftViper.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        RightViper.setVelocity(2000);
        LeftViper.setVelocity(2000);
    }

    public void Extend() {
        RightViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightViper.setVelocity(200);
        LeftViper.setVelocity(200);
    }

    public void Dextend() {
        RightViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightViper.setVelocity(-200);
        LeftViper.setVelocity(-200);
    }

    public void Nextend() {
        RightViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftViper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightViper.setVelocity(0);
        LeftViper.setVelocity(0);
    }

    // MAX 6000
    public void Textend(int pos) {
        RightViper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftViper.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        RightViper.setVelocity(2000);
        LeftViper.setVelocity(2000);

        RightViper.setTargetPosition(pos);
        LeftViper.setTargetPosition(pos);
    }
}
