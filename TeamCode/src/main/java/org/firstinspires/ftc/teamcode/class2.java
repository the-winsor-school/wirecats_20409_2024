package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "class2")
public class class2 extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {

        robot = new Robot(this);
        robot.arm.resetEncoders();

        waitForStart();

        while (opModeIsActive()) {
            float x = gamepad1.right_stick_x;
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            //make wheels go faster
            if (gamepad1.dpad_up)
                robot.driving.adjustSpeed(0.05f);

            //make wheels speed slower
            if (gamepad1.dpad_down)
                robot.driving.adjustSpeed(-0.05f);

            if (gamepad2.dpad_up)
                robot.arm.cascadeLift.changeTargetPosition(-200);
            if (gamepad2.dpad_down)
                robot.arm.cascadeLift.changeTargetPosition(200);

            robot.arm.cascadeLift.armLoop();
            telemetry.addData("cascade lift: ", robot.arm.cascadeLift.getCurrentPosition());



        }
    }
}