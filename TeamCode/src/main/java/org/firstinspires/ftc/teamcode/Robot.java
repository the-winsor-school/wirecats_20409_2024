package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.driving.GridDrive;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;

public class Robot {

    //wheels
    //rf, rb, lf, lb
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;

    public DcMotor cascade;
    public DcMotor intake;
    public CRServo box;

    public IDriving driving;


    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;

        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");

        driving = new StrafeDrive(rf, rb, lf, lb);

        //cascade
        cascade = map.tryGet(DcMotor.class, "cascade");

        //intake
        intake = map.tryGet(DcMotor.class, "intake");

        //box
        box = map.tryGet(CRServo.class, "box");

    }

}
