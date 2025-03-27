package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = .0009;
        ThreeWheelConstants.strafeTicksToInches = .001;
        ThreeWheelConstants.turnTicksToInches = .0009;
        ThreeWheelConstants.leftY = 4.143;
        ThreeWheelConstants.rightY = -4.143;
        ThreeWheelConstants.strafeX = -5.361;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "BRW";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "FLW";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "FRW";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.REVERSE;
    }
}




