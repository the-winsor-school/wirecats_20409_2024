package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FullArm {

    public ArmJoint cascadeLift;
    public ArmJoint clawAngleJoint;

    public Claw claw;

    public FullArm(DcMotor cascadeMotor, DcMotor clawAngleMotor, CRServo rightServo, CRServo leftServo) {
        cascadeLift = new ArmJoint(cascadeMotor,0.75f, 100);
        clawAngleJoint = new ArmJoint(clawAngleMotor,0.25f, 20);

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
        //TODO find rotations for each Arm position
        switch (pos) {
            case RESET: //init position
                cascadeLift.setTargetPosition(0);
                clawAngleJoint.setTargetPosition(0);
                break;

            case PICKINGUP: //picking up
                cascadeLift.setTargetPosition(-200);
                clawAngleJoint.setTargetPosition(0);
                break;

            case PLACING:
                cascadeLift.setTargetPosition(-2650);
                clawAngleJoint.setTargetPosition(-155);
                break;

        }
    }

    public enum ArmPosition {
        RESET,
        PICKINGUP,
        PLACING,
    }
}
