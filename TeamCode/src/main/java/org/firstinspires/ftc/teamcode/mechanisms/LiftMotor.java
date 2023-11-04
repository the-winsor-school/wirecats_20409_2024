package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftMotor {
    int gearRatio;
    double powerUsed;
    DcMotor motor;
    int armTolerance;


    public LiftMotor(DcMotor motor, int gearRatio, double powerUsed, int armTolerance) {
        this.motor = motor;
        this.gearRatio = gearRatio;
        this.powerUsed = powerUsed;
        this.armTolerance = armTolerance;
    }

    public void resetEncoders() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void moveArm(int targetPosition) { //targetPosition in rotation
        Thread moveArm = new MoveArm(targetPosition);
        moveArm.start();
    }

    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public void setMotorPower(double power) {
        motor.setPower(power);
    }

    public void brake() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void changePower(int power) {
        powerUsed += power;
    }

    public void changePosition(int rotations) {
        moveArm(getCurrentPosition() + rotations);
    }

    public class MoveArm extends Thread { //make multiple of these cant happen at the same time
        private int targetPosition;

        public MoveArm (int targetPosition) {
            this.targetPosition = targetPosition;
        }

        public void run() {
            while (Math.abs(getCurrentPosition() - targetPosition) > armTolerance) {//motor is high
                motor.setTargetPosition(targetPosition);
                motor.setPower(powerUsed);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
