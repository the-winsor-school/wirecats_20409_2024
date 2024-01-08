package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.CRServo;

public class Claw {
    CRServo right;
    CRServo left;

    public Claw(CRServo rightServo, CRServo leftServo) {
        right = rightServo;
        left = leftServo;
    }

    /**
     * moves both claw servos
     * @param pos enum for the claw position
     */
    public void controlClaw(ClawPos pos) {
        switch (pos) {
            case OPEN:
                right.setPower(-0.01);
                left.setPower(0.4);
                break;

            case CLOSE:
                right.setPower(0.7);
                left.setPower(0);
                break;
        }
    }

    /**
     * for telemetry
     * @param side either "right" or "left"
     * @return double of the current power
     */
    public double getClawPower(String side) {
        if (side == "right")
            return right.getPower();
        return left.getPower();
    }

    /**
     * enum for the claw position
     */
    public enum ClawPos {
        OPEN,
        CLOSE,
    }
}


