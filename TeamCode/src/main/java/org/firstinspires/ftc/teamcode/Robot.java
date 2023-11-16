package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
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

    public DcMotor leftLift;
    public DcMotor rightLift;
    public DcMotor intake;
    public Servo box;

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
        rightLift = map.tryGet(DcMotor.class, "right lift");
        leftLift = map.tryGet(DcMotor.class, "left lift");

        //intake
        intake = map.tryGet(DcMotor.class, "intake");

        //box
        box = map.tryGet(Servo.class, "box");

    }



}
