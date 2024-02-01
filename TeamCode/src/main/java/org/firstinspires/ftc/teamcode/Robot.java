package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Arm.*;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;
import org.firstinspires.ftc.teamcode.AutonMovements;
import org.firstinspires.ftc.teamcode.Launcher;

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

    private CRServo launchServo;

    public IDriving driving;
    public FullArm arm;

    public Launcher launcher;

    public ColorSensor color;
    public AutonMovements auton;


    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;
        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");
        color = map.tryGet(ColorSensor.class, "color");

        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.REVERSE);

        //arm
        cascadeMotor = map.tryGet(DcMotor.class, "cascade");
        clawAngleMotor = map.tryGet(DcMotor.class, "clawAngle");
        rightServo = map.tryGet(CRServo.class, "right");
        leftServo = map.tryGet(CRServo.class, "left");
        launchServo = map.tryGet(CRServo.class, "launcher");
        driving = new StrafeDrive(rf, rb, lf, lb);
        auton = new AutonMovements(opMode, this);
        arm = new FullArm(cascadeMotor, clawAngleMotor, rightServo, leftServo);
        launcher = new Launcher(launchServo);
    }

    public boolean checkRedTape() {
        if (color.red()  > 500)
            return true;
        return false;
    }

    public boolean checkBlueTape() {
        if (color.blue() > 500)
            return true;
        return false;
    }

    public boolean checkTape() {
        if (checkBlueTape() || checkRedTape())
            return true;
        return false;
    }

    public String printColorValues() {
        return "red: " + color.red() + "\nblue: " + color.blue();
    }

}


