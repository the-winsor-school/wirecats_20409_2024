package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;

public class arm {

    LinearOpMode linearOpMode;
    OpMode opMode;

    /*
    Claw Claw;
*/

    //motors + servos
    public DcMotor cascade;
    public DcMotor intake;
    public CRServo box;

    //arm constants
    int powerUsed = 1;

    //goes from inches to rotations
    int conversionFactor = 144; //for every 1 inch up

    public arm(DcMotor cascadeMotor, DcMotor intakeMotor, CRServo boxServo) {
        cascade = cascadeMotor;
        intake = intakeMotor;
        box = boxServo;
    }

    //______________________________
    //         CASCADE
    //______________________________

    public void setArmPower(float y) {
        cascade.setPower(y);
    }

    public void resetEncoders() {
        cascade.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cascade.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void moveArmRotations(int rotations) {
        cascade.setTargetPosition(rotations);
        cascade.setPower(powerUsed);
        cascade.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveArmToCm(double cm) {
        cascade.setTargetPosition(cmToRotations(cm));
        cascade.setPower(powerUsed);
        cascade.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int cmToRotations(double cm) {
        return (int) (cm * conversionFactor);
    }

    public double rotationsToCm(int rotations) {
        return (rotations * conversionFactor);
    }


//NEED TO TEST ARM LEVEL VALUES
    public void moveArmToLevel(String level) {
        if (level == "gj") //ground junction
            moveArmRotations(0);
        else if (level == "low") //low pole
            moveArmRotations(-7000);
        else if (level == "medium") //medium pole
            moveArmRotations(-12300);
        else if (level == "high") //high pole
            moveArmRotations(-17500);
        else
            throw new RuntimeException("trying to go an arm level that doesn't exist");
    }


    //______________________________
    //         INTAKE
    //______________________________
    public void rotateIntake(float x){
        intake.setPower(x);
    }




    //______________________________
    //         BOX
    //______________________________
    public class Box {
        public CRServo servo;

        public Box(CRServo servoInit) {
            servo = servoInit;
        }

        public void moveDegrees(float degrees) {
            double position = degrees / 360;
            servo.setPower(position);
        }
    }

    public void clawControls(String pos) {
        if (pos == "open") { //open
            box.setPower(0.5);
        } else if (pos == "close") { //close
            box.setPower(0.2);
        }
    }
}