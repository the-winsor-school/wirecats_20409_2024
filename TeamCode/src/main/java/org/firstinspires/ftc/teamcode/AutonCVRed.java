package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;

@Autonomous(name="auton CV: ONLY WORKS IN ZONE 2")
public class AutonCVRed extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        robot.arm.resetEncoders();
        waitForStart();
        if (opModeIsActive()) {
            robot.driving.vertical(1);
            sleep(600);
            robot.arm.moveArmToPosition(FullArm.ArmPosition.PICKINGUP);
            robot.arm.cascadeLift.brake();
            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            robot.driving.vertical(-1);
            sleep(600);
            robot.driving.horizontal(1);
            sleep(2000);
            //robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
        }
    }
}
