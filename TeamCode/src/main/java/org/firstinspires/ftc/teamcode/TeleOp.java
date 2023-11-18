package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Simple")
public class TeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        robot.arm.resetEncoders();

        while (opModeIsActive()) {

            //_______________________________________________
            //             MAIN CONTROLLER
            //_______________________________________________

            float x = gamepad1.right_stick_x;
            float y = gamepad1.right_stick_y;
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            if (gamepad1.a)
                robot.arm.resetEncoders();

            //_______________________________________________
            //             MECH CONTROLLER
            //_______________________________________________

            //arm levels
            if (gamepad2.y)
                robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACINGHIGH);
            if(gamepad2.b)
                robot.arm.moveArmToPosition(FullArm.ArmPosition.RESET);
            if(gamepad2.a)
                robot.arm.moveArmToPosition(FullArm.ArmPosition.PICKINGUP);
            if(gamepad2.x)
                robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACINGLOW);

            //arm manual controls
            //TODO test for these rotations values
            if (gamepad2.dpad_up)
                robot.arm.cascadeLift.changeTargetPosition(200);
            if(gamepad2.dpad_down)
                robot.arm.cascadeLift.changeTargetPosition(-200);
            if (gamepad2.dpad_left)
                robot.arm.clawAngleJoint.changeTargetPosition(200);
            if(gamepad2.dpad_right)
                robot.arm.clawAngleJoint.changeTargetPosition(-200);

            //claw controls
            if (gamepad2.right_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            if (gamepad2.left_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);

            //_______________________________________________
            //             PRINT STATEMENTS
            //_______________________________________________

            //joystick inputs
            //telemetry.addData("x: ", x);
            //telemetry.addData("y: ", y);
            //telemetry.addData("t: ", t);

            //wheels powers
            //robot.printWheelPowers();

            telemetry.addLine("----------------ARM-------------------------");

            //arm current position
            telemetry.addData("cascade lift: ", robot.arm.cascadeLift.getCurrentPosition());
            telemetry.addData("claw angle joint: ", robot.arm.clawAngleJoint.getCurrentPosition());

            //arm directions
            telemetry.addData("cascade lift: ", robot.arm.cascadeLift.getDirection());
            telemetry.addData("claw angle joint: ", robot.arm.clawAngleJoint.getDirection());

            telemetry.addData("target cascade lift: ", robot.arm.cascadeLift.targetPosition);
            telemetry.addData("target claw angle joint: ", robot.arm.clawAngleJoint.targetPosition);

            telemetry.addLine("----------------CLAW-------------------------");

            telemetry.addData("right servo: ", robot.arm.claw.getPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getPower("left"));


            robot.arm.cascadeLift.armLoop();
            robot.arm.clawAngleJoint.armLoop();

            telemetry.update();


        }
    }
}