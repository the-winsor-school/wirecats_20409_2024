package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Arm.Claw;

public class Launcher {
    CRServo launcher;
    public Launcher (CRServo launchServo) {
        launcher = launchServo;
    }
    public void launchAirplane(LaunchPos pos) {
        switch (pos) {
            case OPEN:
                launcher.setPower(-0.6);
                break;

            case CLOSE:
                launcher.setPower(0.6);
                break;
        }
    }
    public double getLauncherPower() {
        return launcher.getPower();
    }

    public enum LaunchPos {
        OPEN,
        CLOSE
    }
}
