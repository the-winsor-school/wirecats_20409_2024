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
    public DcMotor cascadeMotor;
    public DcMotor intakeMotor;
    public CRServo boxServo;

    public void runOpMode() throws InterruptedException {
        arm = new Arm(cascadeMotor, intakeMotor, boxServo);
        robot = new Robot(this);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            //driving
            float x = gamepad1.right_stick_x;
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;
            telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);
            robot.driving.joystickDrive(x, y, t);
            telemetry.update();

            if (gamepad1.a) {
                arm.resetEncoders();
            }

            // ------------MECH CONTROLLER--------------

            //______________________________
            //         CASCADE
            //______________________________


            //NEEDS TESTING
            //going up
            if (gamepad2.x)
                arm.moveArmToLevel("medium");
            //going down
            if (gamepad2.y)
                arm.moveArmToLevel("gj");

            //power controlled arm
            if (!(gamepad2.a || gamepad2.b || gamepad2.x || gamepad2.y || gamepad2.dpad_up || gamepad2.dpad_down)) //if any button for arm is not being pressed
                arm.setArmPower(gamepad2.right_stick_y);

            //time based arm controls
            if (gamepad2.dpad_up) { //arm up
                arm.cascade.setPower(1);
                sleep(100);
                arm.cascade.setPower(0);
                int finalPos = arm.cascade.getCurrentPosition();
                //numbers are wrong NEEDS TESTING
                if (finalPos < -17500) {
                    telemetry.addData("too far", finalPos);
                    arm.cascade.setPower(0);
                    telemetry.update();
                }
            }
            if (gamepad2.dpad_down) { //arm down
                arm.cascade.setPower(-1);
                sleep(100);
                arm.cascade.setPower(0);
            }


            //______________________________
            //         INTAKE
            //______________________________

                //random number right now
                //NEEDS TESTING
            if (gamepad2.right_bumper)
                arm.rotateIntake(450);

            //______________________________
            //         BOX
            //______________________________

            if (gamepad2.a)
                arm.boxControls("open");
            if (gamepad2.b)
                arm.boxControls("close");
            }
        }
    }