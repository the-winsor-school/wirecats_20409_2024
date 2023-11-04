package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Servo;

public class Box {
    Servo servo;

    public Box(Servo servo)  {
        this.servo = servo;
    }

    public void boxControls(BoxPos position) {
        switch (position) {
            case UP:
                servo.setPosition(1);
                break;
            case DOWN:
                servo.setPosition(-1);
                break;
        }
    }

    public void adjustBox(int position) {
        //code will depend on CR servo or Servo
    }

    public enum BoxPos {
        UP,
        DOWN
    }

}
