package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;

@Autonomous(name="class1")
public class class1 extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        waitForStart();
        if (opModeIsActive()) {
            robot.driving.horizontal(1);
            sleep(75);
            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            sleep(500);
            telemetry.addLine("claw is open");
            robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);
            telemetry.addLine("claw is closed");
            sleep(500);
            robot.driving.vertical(1);
            sleep(75);
            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            sleep(500);
            telemetry.addLine("claw is open");
            robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);
            sleep(500);
            telemetry.addLine("claw is closed");
            robot.driving.horizontal(-1);
            sleep(75);
            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            sleep(500);
            telemetry.addLine("claw is open");
            robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);
            sleep(500);
            telemetry.addLine("claw is closed");
            robot.driving.vertical(-0.75f);
            sleep(75);
            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            sleep(500);
            telemetry.addLine("claw is open");
            robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);
            sleep(500);
            telemetry.addLine("claw is closed");
        }
    }
}
