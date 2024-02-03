package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FullArm {

    public ArmJoint cascadeLift;
    public ArmJoint clawAngleJoint;

    public Claw claw;

    public FullArm(DcMotor cascadeMotor, DcMotor clawAngleMotor, CRServo rightServo, CRServo leftServo) {
        cascadeLift = new ArmJoint(cascadeMotor,1f, 50);
        clawAngleJoint = new ArmJoint(clawAngleMotor,0.5f, 5);
        clawAngleJoint.setStopBehaviour(DcMotor.ZeroPowerBehavior.FLOAT);
        claw = new Claw (rightServo, leftServo);
    }

    /**
     * resets encoders for both joints
     */
    public void resetEncoders() {
        cascadeLift.resetEncoders();
        clawAngleJoint.resetEncoders();
    }

    public void moveArmToPosition(ArmPosition pos) {
        switch (pos) {
            case RESET: //init position
                cascadeLift.setTargetPosition(0);
                clawAngleJoint.setTargetPosition(0);
                break;

            case PICKINGUP: //picking up
                cascadeLift.setTargetPosition(-300);
                clawAngleJoint.setTargetPosition(0);
                break;

            case PLACING:
                cascadeLift.setTargetPosition(-4900);
                clawAngleJoint.setTargetPosition(330);
                break;

        }
    }

    public enum ArmPosition {
        RESET,
        PICKINGUP,
        PLACING,
    }
}
