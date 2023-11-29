package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Arm.*;

import org.firstinspires.ftc.teamcode.driving.GridDrive;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;

public class Robot {

    //wheels
    //rf, rb, lf, lb
    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;

    private DcMotor cascadeMotor;
    private DcMotor clawAngleMotor;
    private CRServo rightServo;
    private CRServo leftServo;

    public IDriving driving;
    public FullArm arm;


    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;
        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");

        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.REVERSE);

        //arm
        cascadeMotor = map.tryGet(DcMotor.class, "cascade");
        clawAngleMotor = map.tryGet(DcMotor.class, "clawAngle");
        rightServo = map.tryGet(CRServo.class, "right");
        leftServo = map.tryGet(CRServo.class, "left");

        cascadeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        clawAngleMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        driving = new StrafeDrive(rf, rb, lf, lb);
        arm = new FullArm(cascadeMotor, clawAngleMotor, rightServo, leftServo);
    }

}
