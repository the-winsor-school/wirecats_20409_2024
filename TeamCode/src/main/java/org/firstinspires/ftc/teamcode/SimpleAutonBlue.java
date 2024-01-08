package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;

@Autonomous(name="blue parking")
public class SimpleAutonBlue extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        waitForStart();
        if (opModeIsActive()) {
            robot.driving.horizontal(-1);
            sleep(2000);
            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            telemetry.addLine("claw open");
        }
    }
}
