package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardwaremaps.DeepHardwareMap;

/**
 * A helper class that provides commonly used functions, primarily for OpModes
 */
public class Helper {
    /**
     * Enum for storing a colour's state
     */
    public enum GamepadColour {
        RED(1, 0, 0),
        GREEN(0, 1, 0),
        BLUE(0, 0, 1),
        BLACK(0, 0, 0),
        WHITE(1, 1, 1),
        CYAN(0, 1, 1),
        PURPLE(0.5, 0, 1),
        YELLOW(1, 1, 0),
        ORANGE(1, 0.5, 0),
        PINK(1, 0, 0.5),

        DARK_RED(0.4, 0, 0),
        DARK_YELLOW(0.4, 0.4, 0),
        DARK_GREEN(0, 0.4, 0),
        DARK_BLUE(0, 0, 0.4),
        DARK_CYAN(0, 0.4, 0.4),

        LIGHT_RED(1, 0.6, 0.6),
        LIGHT_YELLOW(1, 1, 0.6),
        LIGHT_GREEN(0.6, 1, 0.6),
        LIGHT_CYAN(0.6, 1, 1),
        LILAC(0.6, 0.6, 1),
        LIGHT_PINK(1, 0.6, 0.8);

        public static GamepadColour[] AllColours = new GamepadColour[]{
            RED, GREEN, BLUE, CYAN, PURPLE, YELLOW, ORANGE, PINK
        };

        private final float red;
        private final float green;
        private final float blue;

        /**
         * Set up colour
         * @param r Red Value
         * @param g Green Value
         * @param b Blue Value
         */
        GamepadColour(float r, float g, float b) {
            this.red = r;
            this.green = g;
            this.blue = b;
        }

    }

    /**
     * Switch for if Gamepad should rumble on certain actions.
     * IE. Colour switch
     */
    public static boolean GamepadRumble = true;

    /**
     * Logs operational state of all motors
     * @param hardwareMap The DeepHardwareMap being used
     * @param telemetry Telemetry object from OpMode for logging
     */
    public static void ReportDriveMotorStatus(DeepHardwareMap hardwareMap, Telemetry telemetry) {
        telemetry.addData("Front Right Motor", hardwareMap.FrontRightMotor == null ? "Fault" : "Operational");
        telemetry.addData("Front Left Motor", hardwareMap.FrontLeftMotor == null ? "Fault" : "Operational");
        telemetry.addData("Back Right Motor", hardwareMap.BackRightMotor == null ? "Fault" : "Operational");
        telemetry.addData("Back Left Motor", hardwareMap.BackLeftMotor == null ? "Fault" : "Operational");
        telemetry.update();
    }

    /**
     * Logs current power of all drive motors in hardware map
     * @param hardwareMap DeepHardwareMap currently in use
     * @param telemetry Telemetry object from OpMode for logging
     */
    public static void ReportAllMotorSpeed(DeepHardwareMap hardwareMap, Telemetry telemetry) {
        telemetry.addData("Front Right Motor Power", hardwareMap.FrontRightMotor.getPower());
        telemetry.addData("Front Left Motor Power", hardwareMap.FrontLeftMotor.getPower());
        telemetry.addData("Back Right Motor Power", hardwareMap.BackRightMotor.getPower());
        telemetry.addData("Back Left Motor Power", hardwareMap.BackLeftMotor.getPower());
        telemetry.update();
    }

    /**
     * Will add all strings passed to telemetry if it is not null
     * @param telemetry Telemtry object to be passed
     * @param args List of Strings to be logged
     */
    public static void ReportIfTelemetry(Telemetry telemetry, String... args) {
        if(telemetry == null) return;
        for(String arg: args) {
            telemetry.addLine(arg);
        }
        telemetry.update();
    }

    public static void ReportCurrentMotorDirection(Telemetry telemetry, int direction) {
        telemetry.addData("Current motor direction", direction == 1 ? "Forward" : "Backward");
    }


    /**
     * Sets light on gamepad and rumbles
     * @param gp Gamepad to modify colour
     * @param col Colour to change gamepad to
     */
    public static void SetGamepadLight(Gamepad gp, GamepadColour col) {
        if(GamepadRumble) gp.rumble(200);
        gp.setLedColor(col.red, col.green, col.blue, Gamepad.LED_DURATION_CONTINUOUS);
    }

    public static void SetGamepadLight(Gamepad gp, GamepadColour col, boolean rumble) {
        if(rumble) gp.rumble(200);
        gp.setLedColor(col.red, col.green, col.blue, Gamepad.LED_DURATION_CONTINUOUS);
    }

    /**
     * Gets all of the RHS buttons from the gamepad in order of ABXY
     * @param gp The gamepad to get the state from
     * @return Array of boolean values signifying each buttons' state
     */
    public static boolean[] CopyButtonsFromGamepad(Gamepad gp) {
        return new boolean[] {gp.a, gp.b, gp.x, gp.y};
    }

    private static int PrimaryColours = 8;

    public static int RainbowLeds(Gamepad gamepad, int iter) {
        if(iter % 2 != 0) {
            return ++iter;
        }
        if(iter > (PrimaryColours * 2)) {
            iter = 0;
        }
        GamepadColour col = GamepadColour.AllColours[iter / 2];
        SetGamepadLight(gamepad, col, false);
        return ++iter;
    }

}
