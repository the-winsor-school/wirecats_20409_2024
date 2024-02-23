package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;

import org.firstinspires.ftc.teamcode.Arm.FullArm;

@Autonomous(name="practiceFun")
public class PracticeFun extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        waitForStart();
        if (opModeIsActive()) {
            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            sleep(1000);
            telemetry.addLine("claw open");
            telemetry.update();
            robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);
            sleep(1500);
            telemetry.addLine("claw close");
            telemetry.update();

           // robot.arm.moveArmToPosition(FullArm.ArmPosition.PICKINGUP);
            robot.arm.cascadeLift.changeTargetPosition(-1000);
            telemetry.addLine("claw up");
            sleep(2000);
            telemetry.update();

            robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            sleep(1000);
            telemetry.addLine("claw open");
            telemetry.update();
            robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);
            sleep(1000);
            telemetry.addLine("claw close");
            telemetry.update();

            robot.arm.cascadeLift.changeTargetPosition(-200);
            sleep(1000);
            telemetry.addData("cascade up", robot.arm.cascadeLift.getCurrentPosition() );
            telemetry.update();
        }
    }
}
