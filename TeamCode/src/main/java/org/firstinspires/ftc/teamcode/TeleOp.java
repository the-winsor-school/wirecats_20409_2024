package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Arm;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Simple")
public class TeleOp extends LinearOpMode {
    Arm arm;
    Robot robot;
    public boolean open = true;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        arm = new Arm(robot);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            //driving
            float x = gamepad1.right_stick_x;
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            //arm levels
            if (gamepad2.y)
                arm.moveToLevel(Arm.ArmLevel.PLACE);
            if (gamepad2.b)
                arm.moveToLevel(Arm.ArmLevel.RESET);
            if (gamepad2.a)
                arm.moveToLevel(Arm.ArmLevel.PICKINGUP);

            //manual arm controls
            if (gamepad2.dpad_up)
                arm.changeLift(200);
            if (gamepad2.dpad_down)
                arm.changeLift(-200);

            //telemetry
            telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);
            telemetry.update();

        }
    }
}