
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
/*
import org.firstinspires.ftc.teamcode.libraries.AutonLibrary;
import org.firstinspires.ftc.teamcode.libraries.DrivingLibrary;
 */
import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
/*
import org.firstinspires.ftc.teamcode.libraries.AutonLibrary;
import org.firstinspires.ftc.teamcode.libraries.DrivingLibrary;
import org.firstinspires.ftc.teamcode.libraries.SimpleDriving;
import org.firstinspires.ftc.teamcode.libraries.Robot;
*/

@Autonomous
public class OpenCV extends LinearOpMode {
    Robot robot;
    LinearOpMode opMode;
    OpenCvCamera webcam;
    SamplePipeline pipeline;

    @Override
    public void runOpMode() {
        //drivingLibrary = new DrivingLibrary(this);
        //drivingLibrary.setSpeed(1);
        // drivingMode = 0;
        //drivingLibrary.setMode(drivingMode);
        /*
        autonLibrary = new AutonLibrary(this);
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new SamplePipeline();
        webcam.setPipeline(pipeline);
        /*
        simpleDriving = new SimpleDriving(this);
        */
        robot = new Robot(this);


        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            public void onOpened()
            {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT); //size of whole camera frame
            }
            public void onError(int errorCode)
            {

            }
        });
        webcam.resumeViewport();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
/*
        while (opModeIsActive()) {
            telemetry.addData("Type", pipeline.getType());
            telemetry.addData("Average", pipeline.getAverage());
            telemetry.update();
            sleep(50);
        }

 */

        if (opModeIsActive()) {
            telemetry.addData("debug", "start");
            telemetry.update();
            sleep(3000);
            SamplePipeline.TYPE zone = pipeline.getType();
            int[] averages1 = pipeline.getAverage1();
            int[] averages2 = pipeline.getAverage2();
            int[] averages3 = pipeline.getAverage3();
            telemetry.addData("Type", zone);
            telemetry.addData("Average Red 1", averages1[0]);
            telemetry.addData("Average Green 1", averages1[1]);
            telemetry.addData("Average Blue 1", averages1[2]);
            telemetry.addData("Average Red 2", averages2[0]);
            telemetry.addData("Average Green 2", averages2[1]);
            telemetry.addData("Average Blue 2", averages2[2]);
            telemetry.addData("Average Red 3", averages3[0]);
            telemetry.addData("Average Green 3", averages3[1]);
            telemetry.addData("Average Blue 3", averages3[2]);
            sleep(3000);
            telemetry.update();

            if (zone == SamplePipeline.TYPE.ZONE1)
            {
                //driving/camera part
                telemetry.addLine("Zone 1");
                robot.driving.vertical(0.75f);
                telemetry.addLine("driving vertically");
                sleep(1200);
                robot.driving.stop();
                robot.driving.horizontal(-1);
                telemetry.addLine("driving horizontally");
                sleep(500);
                robot.driving.stop();
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
                sleep(1000);
                robot.driving.stop();
                
                //parking part
                robot.driving.vertical(-0.75f);
                sleep(300);
                robot.driving.horizontal(-1);
                telemetry.addLine("driving horizontally");
                sleep(1500);
                robot.driving.stop();
                //robot.arm.claw.controlClaw(Claw.ClawPos.STOP);
                //sleep(200);
            }
            else if (zone == SamplePipeline.TYPE.ZONE2){
                //signalPark(2,Location.RedTop);
                /*
                autonLibrary.strafingSigmoid(0,-1,0,15, this, simpleDriving); //moves forward one square mat
                sleep (50);
                 */
                //driving/camera part
                telemetry.addLine("Zone 2");
                robot.driving.vertical(1);
                telemetry.addLine("driving vertically");
                sleep(1025);
                robot.driving.stop();
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
                sleep(1000);
                robot.driving.stop();
                
                //parking part
                robot.driving.vertical(-0.75f);
                sleep(300);
                robot.driving.horizontal(-1);
                telemetry.addLine("driving horizontally");
                sleep(1700);
                robot.driving.stop();
            }
            else if (zone == SamplePipeline.TYPE.ZONE3){
                //signalPark(2,Location.RedTop);
                /*
                autonLibrary.strafingSigmoid(0,-1,0,5, this, simpleDriving); //moves forward one square mat
                sleep (50);
                autonLibrary.strafingSigmoid(1,0,0,700,this,simpleDriving); //moves right one square
                sleep (500);
                 */
                //driving/camera part
                telemetry.addLine("Zone 3");
                robot.driving.vertical(0.75f);
                telemetry.addLine("driving vertically");
                sleep(1350);
                robot.driving.stop();
                robot.driving.horizontal(0.75f);
                telemetry.addLine("driving horizontally");
                sleep(900);
                robot.driving.stop();
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
                sleep(1000);
                robot.driving.stop();
                
                //parking part
                robot.driving.vertical(-0.75f);
                sleep(100);
                robot.driving.horizontal(-1);
                telemetry.addLine("driving horizontally");
                sleep(2300);
                robot.driving.stop();
            }
            else if (zone == SamplePipeline.TYPE.ZONE4)
            {
                //camera part
                telemetry.addLine("Zone 4");
                robot.driving.vertical(0.75f);
                telemetry.addLine("driving vertically");
                sleep(1325);
                robot.driving.stop();
                robot.driving.horizontal(-1);
                telemetry.addLine("driving horizontally");
                sleep(575);
                robot.driving.stop();
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
                sleep(1000);
                robot.driving.stop();

                //parking part
                robot.driving.vertical(-0.75f);
                telemetry.addLine("driving vertically");
                sleep(125);
                robot.driving.horizontal(1);
                telemetry.addLine("driving horizontally");
                sleep(2300);
                robot.driving.stop();
                //robot.arm.claw.controlClaw(Claw.ClawPos.STOP);
                //sleep(200);
            }
            else if (zone == SamplePipeline.TYPE.ZONE5){
                //signalPark(2,Location.RedTop);
                /*
                autonLibrary.strafingSigmoid(0,-1,0,15, this, simpleDriving); //moves forward one square mat
                sleep (50);
                 */
                //driving/camera part
                telemetry.addLine("Zone 5");
                robot.driving.vertical(1);
                telemetry.addLine("driving vertically");
                sleep(900);
                robot.driving.stop();
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
                sleep(1000);
                robot.driving.stop();

                //parking part
                robot.driving.vertical(-0.75f);
                telemetry.addLine("driving vertically");
                sleep(500);
                robot.driving.horizontal(1);
                telemetry.addLine("driving horizontally");
                sleep(1700);
                robot.driving.stop();
            }
            else if (zone == SamplePipeline.TYPE.ZONE6){
                //signalPark(2,Location.RedTop);
                /*
                autonLibrary.strafingSigmoid(0,-1,0,5, this, simpleDriving); //moves forward one square mat
                sleep (50);
                autonLibrary.strafingSigmoid(1,0,0,700,this,simpleDriving); //moves right one square
                sleep (500);
                 */
                //driving/camera part
                telemetry.addLine("Zone 6");
                robot.driving.vertical(0.75f);
                telemetry.addLine("driving vertically");
                sleep(1200);
                robot.driving.stop();
                robot.driving.horizontal(0.75f);
                telemetry.addLine("driving horizontally");
                sleep(825);
                robot.driving.stop();
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
                sleep(1000);
                robot.driving.stop();

                //parking part
                robot.driving.vertical(-0.75f);
                telemetry.addLine("driving vertically");
                sleep(500);
                robot.driving.horizontal(1);
                telemetry.addLine("driving horizontally");
                sleep(1500);
                robot.driving.stop();
            }
            /*autonLibrary.strafingSigmoid(1,0,0,2100, this, drivingLibrary); //strafes to the left for 2100 ms
            sleep (2100);*/

            telemetry.addData("debug", "strafing complete");
            sleep(10000000);

        }
    }

    public static class SamplePipeline extends OpenCvPipeline {
        private static final Scalar BLUE = new Scalar(0, 0, 255);
        private static final Scalar RED = new Scalar(255, 0, 0);
        private static final Scalar GREEN = new Scalar(0, 255, 0);

        //CHANGE THRESHOLD BASED ON SLEEVE COLORS
        private static final int THRESHOLD1 = 125; // yellow < 107 < white
        private static final int THRESHOLD2 = 140; // white < 140 < purple
        private static final int RED1 = 20; // yellow < 107 < white
        private static final int GREEN1 = 20; // white < 140 < purple

        //CHANGE DIMENSIONS BASED ON POSITOIN FROM START OF AUTON
        Point topLeft1 = new Point(0, 110);
        Point bottomRight1 = new Point(40, 150);

        Point topLeft2 = new Point(135, 100);
        Point bottomRight2 = new Point(175, 140);

        Point topLeft3 = new Point(270, 95);
        Point bottomRight3 = new Point(310, 135);

        /*
        Point topLeftZone1 = new Point(0, 0);
        Point bottomRightZone1 = new Point(266, 448);

         */

        Mat region1_Cb;
        Mat region1_Cg;
        Mat region1_Cr;

        Mat region2_Cb;
        Mat region2_Cg;
        Mat region2_Cr;

        Mat region3_Cb;
        Mat region3_Cg;
        Mat region3_Cr;
        //Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        Mat Cg = new Mat();
        Mat Cr = new Mat();

        private volatile int averageRed1;
        private volatile int averageBlue1;
        private volatile int averageGreen1;

        private volatile int averageRed2;
        private volatile int averageBlue2;
        private volatile int averageGreen2;

        private volatile int averageRed3;
        private volatile int averageBlue3;
        private volatile int averageGreen3;
        private volatile TYPE type = TYPE.ZONE2; //default value

        private void inputToCb(Mat input) {
            ///Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(input, Cb, 2);
            Core.extractChannel(input, Cg, 1);
            Core.extractChannel(input, Cr, 0);
        }

        @Override
        public void init(Mat input) {
            inputToCb(input);

            region1_Cb = Cb.submat(new Rect(topLeft1, bottomRight1)); //setting region dimensions
            region1_Cg = Cg.submat(new Rect(topLeft1, bottomRight1)); //setting region dimensions
            region1_Cr = Cr.submat(new Rect(topLeft1, bottomRight1)); //setting region dimensions
            region2_Cb = Cb.submat(new Rect(topLeft2, bottomRight2)); //setting region dimensions
            region2_Cg = Cg.submat(new Rect(topLeft2, bottomRight2)); //setting region dimensions
            region2_Cr = Cr.submat(new Rect(topLeft2, bottomRight2)); //setting region dimensions
            region3_Cb = Cb.submat(new Rect(topLeft3, bottomRight3)); //setting region dimensions
            region3_Cg = Cg.submat(new Rect(topLeft3, bottomRight3)); //setting region dimensions
            region3_Cr = Cr.submat(new Rect(topLeft3, bottomRight3)); //setting region dimensions
        }

        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);

            averageBlue1 = (int) Core.mean(region1_Cb).val[0]; // red average values
            averageGreen1 = (int) Core.mean(region1_Cg).val[0]; // blue average values
            averageRed1 = (int) Core.mean(region1_Cr).val[0]; // green average values
            averageBlue2 = (int) Core.mean(region2_Cb).val[0]; // red average values
            averageGreen2 = (int) Core.mean(region2_Cg).val[0]; // blue average values
            averageRed2 = (int) Core.mean(region2_Cr).val[0]; // green average values
            averageBlue3 = (int) Core.mean(region3_Cb).val[0]; // red average values
            averageGreen3 = (int) Core.mean(region3_Cg).val[0]; // blue average values
            averageRed3 = (int) Core.mean(region3_Cr).val[0]; // green average values
            Imgproc.rectangle(input, topLeft1, bottomRight1, BLUE, 2);
            Imgproc.rectangle(input, topLeft2, bottomRight2, BLUE, 2);
            Imgproc.rectangle(input, topLeft3, bottomRight3, BLUE, 2);

            if (averageRed1 < averageBlue1 && averageGreen1 < averageBlue1 && averageBlue1 < averageBlue2 && averageBlue1 <= averageBlue3) {
                type = TYPE.ZONE1;
            }
            else if (averageRed2 < averageBlue2 && averageGreen2 < averageBlue2 && averageBlue2 < averageBlue1 && averageBlue2 < averageBlue3) {
                type = TYPE.ZONE2;
            }
            else if (averageRed3 < averageBlue3 && averageGreen3 < averageBlue3 && averageBlue1 > averageBlue3 && averageBlue2 > averageBlue3) {
                type = TYPE.ZONE3;
            }
            else if (averageBlue1 < averageRed1 && averageGreen1 < averageRed1 && averageRed1 < averageRed2 && averageRed1 < averageRed3) {
                type = TYPE.ZONE4;
            }
            else if (averageBlue2 < averageRed2 && averageGreen2 < averageRed2 && averageRed1 > averageRed2  && averageRed2 < averageRed3) {
                type = TYPE.ZONE5;
            }
            else if (averageBlue3 < averageRed3 && averageGreen3 < averageRed3 && averageRed1 > averageRed3 && averageRed2 > averageRed3 && averageRed1 > averageRed2) {
                type = TYPE.ZONE6;
            }
            else {
                type = null;
            }

            return input;
        }

        public TYPE getType() {
            return type;
        }

        public int[] getAverage1() {
            int[] averages1 = {averageRed1, averageGreen1, averageBlue1};
            return averages1;
        }
        public int[] getAverage2() {
            int[] averages2 = {averageRed2, averageGreen2, averageBlue2};
            return averages2;
        }
        public int[] getAverage3() {
            int[] averages3 = {averageRed3, averageGreen3, averageBlue3};
            return averages3;
        }

        public enum TYPE {
            //zone 1-3 = blue
            //zone 4-6 = red
            ZONE1, ZONE2, ZONE3, ZONE4, ZONE5, ZONE6
        }
    }
    /*
    public void signalPark(int signal, Location location) {
        float direction = 0;
        if (location == Location.RedTop || location == Location.BlueBottom) {
            direction = autonDrivingSpeed; //signal 1 is forward, strafe right
        } else if (location == Location.BlueTop || location == Location.RedBottom) {
            direction = -autonDrivingSpeed; //signal 3 is backwards, strafe left
        }

        //actual auton
        simpleDriving.strafe(direction);
        sleep(100);

        if (signal == 1) { //already in signal 2 location so not if for that one
            simpleDriving.driveForward(direction);
            sleep(300); //test for this
        } else if (signal == 3) {
            simpleDriving.driveForward(-direction);
            sleep(300); //test for this
        }

        simpleDriving.stop();
    }

     */
}