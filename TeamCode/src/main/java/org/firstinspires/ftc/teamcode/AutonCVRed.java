package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;

@Autonomous(name="auton CV: ONLY WORKS IN ZONE 2")
public class AutonCVRed extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        robot.arm.resetEncoders();
        waitForStart();
        if (opModeIsActive()) {
            robot.driving.vertical(1);
            sleep(1150);
            robot.driving.stop();
            telemetry.addLine("\n----------------CLAW-------------------------");
            telemetry.addData("right servo: ", robot.arm.claw.getClawPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getClawPower("left"));
            telemetry.update();
            robot.driving.vertical(-1);
            sleep(600);
            robot.driving.stop();
            robot.driving.horizontal(1);
            sleep(1800);
            robot.driving.stop();
            //robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);

        }
    }
}
