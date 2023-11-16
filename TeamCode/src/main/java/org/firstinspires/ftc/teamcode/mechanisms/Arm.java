package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot;

public class Arm {

    public LiftMotor rightLift;
    public LiftMotor leftLift;

    public Box box;
    public DcMotor intake;

    //arm constants
    int powerUsed = 1;

    int armTolerance;

    public Arm(Robot robot) {
        rightLift = new LiftMotor(robot.rightLift, 0, powerUsed, armTolerance);
        leftLift = new LiftMotor(robot.leftLift, 0, powerUsed, armTolerance);
        intake = robot.intake;
        box = new Box(robot.box);
    }

    public void moveLift(int position) {
        rightLift.moveArm(position);
        leftLift.moveArm(position);
    }

    public int getLiftPosition() {
        return ((rightLift.getCurrentPosition() + leftLift.getCurrentPosition()) / 2); //gets average position
    }

    public void changeLift(int rotations) {
        moveLift(getLiftPosition() + rotations); //which position should i get - make new object combinging both motors
    }

    public void moveToLevel(ArmLevel level) {
        switch (level) {
            case RESET: //init position
                moveLift(100);
                break;

            case PLACE: //placing on board
                moveLift(100);
                break;
            case PICKINGUP: //picking up
                moveLift(100);
                break;
        }
    }
    public enum ArmLevel {
        RESET,
        PLACE,
        PICKINGUP,
    }
}