package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.driving.*;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Simple")
public class TeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {

        robot = new Robot(this);
        robot.arm.resetEncoders();

        waitForStart();

        while (opModeIsActive()) {

            //_______________________________________________
            //             MAIN CONTROLLER
            //_______________________________________________

            float x = gamepad1.right_stick_x;
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            //make wheels go faster
            if (gamepad1.dpad_up)
                robot.driving.adjustSpeed(0.05f);

            //make wheels speed slower
            if(gamepad1.dpad_down)
                robot.driving.adjustSpeed(-0.05f);

            //_______________________________________________
            //             MECH CONTROLLER
            //_______________________________________________

            //arm manual controls
            if (gamepad2.dpad_up)
                robot.arm.cascadeLift.changeTargetPosition(-200);
            if (gamepad2.dpad_down)
                robot.arm.cascadeLift.changeTargetPosition(200);
            if (gamepad2.dpad_right)
                robot.arm.clawAngleJoint.changeTargetPosition(50);
            if (gamepad2.dpad_left)
                robot.arm.clawAngleJoint.changeTargetPosition(-50);

            //claw controls
            if (gamepad2.right_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            if (gamepad2.left_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);

            //arm encoder controls
            if (gamepad2.x)
                robot.arm.resetEncoders();
            if (gamepad2.y)
                robot.arm.moveArmToPosition(FullArm.ArmPosition.RESET);
            if (gamepad2.a)
                robot.arm.moveArmToPosition(FullArm.ArmPosition.PICKINGUP);
            if (gamepad2.b)
                robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACINGLOW);
            //if (gamepad2.right_trigger)
                //robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACINGHIGH);

            robot.arm.cascadeLift.armLoop();
            robot.arm.clawAngleJoint.armLoop();


            //telemetry
            /*telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);
            */

            telemetry.addLine("\n----------------ARM-------------------------");

            //arm current position
            telemetry.addData("cascade lift: ", robot.arm.cascadeLift.getCurrentPosition());
            telemetry.addData("claw angle joint: ", robot.arm.clawAngleJoint.getCurrentPosition());

            //arm directions
            telemetry.addData("cascade lift: ", robot.arm.cascadeLift.getDirection());
            telemetry.addData("claw angle joint: ", robot.arm.clawAngleJoint.getDirection());

            telemetry.addData("target cascade lift: ", robot.arm.cascadeLift.targetPosition);
            telemetry.addData("target claw angle joint: ", robot.arm.clawAngleJoint.targetPosition);

            telemetry.addLine("\n----------------CLAW-------------------------");

            telemetry.addData("right servo: ", robot.arm.claw.getPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getPower("left"));

            telemetry.update();

        }
    }
}