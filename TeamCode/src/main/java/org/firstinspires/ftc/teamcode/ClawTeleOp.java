package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Claw")
public class ClawTeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {

        robot = new Robot(this);
        robot.arm.resetEncoders();

        waitForStart();

        while (opModeIsActive()) {

            //_______________________________________________
            //             MAIN CONTROLLER
            //_______________________________________________

            //claw controls
            if (gamepad2.right_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            if (gamepad2.left_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);


            telemetry.addLine("\n----------------CLAW-------------------------");

            telemetry.addData("right servo: ", robot.arm.claw.getClawPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getClawPower("left"));
            telemetry.addData("launch servo: ", robot.launcher.getLauncherPower());
            telemetry.update();

        }
    }
}