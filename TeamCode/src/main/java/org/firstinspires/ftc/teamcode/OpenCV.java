
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
/*
import org.firstinspires.ftc.teamcode.libraries.AutonLibrary;
import org.firstinspires.ftc.teamcode.libraries.DrivingLibrary;
 */
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
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
            int[] averages = pipeline.getAverage();
            telemetry.addData("Type", zone);
            telemetry.addData("Average Red", averages[0]);
            telemetry.addData("Average Green", averages[1]);
            telemetry.addData("Average Blue", averages[2]);
            sleep(3000);
            telemetry.update();

            if (zone == SamplePipeline.TYPE.ZONE1){
                //signalPark(1,Location.RedTop);
                /*
                autonLibrary.strafingSigmoid(0,-1,0,20, this, simpleDriving); //moves forward one square mat
                sleep (50);
                autonLibrary.strafingSigmoid(-1,0,0,850,this,simpleDriving); //moves left one square
                sleep (300); //necessary?
                 */
                telemetry.addData("Zone", zone);
            }
            else if (zone == SamplePipeline.TYPE.ZONE2){
                //signalPark(2,Location.RedTop);
                /*
                autonLibrary.strafingSigmoid(0,-1,0,15, this, simpleDriving); //moves forward one square mat
                sleep (50);
                 */
                telemetry.addData("Zone", zone);
            }
            else if (zone == SamplePipeline.TYPE.ZONE3){
                //signalPark(2,Location.RedTop);
                /*
                autonLibrary.strafingSigmoid(0,-1,0,5, this, simpleDriving); //moves forward one square mat
                sleep (50);
                autonLibrary.strafingSigmoid(1,0,0,700,this,simpleDriving); //moves right one square
                sleep (500);
                 */
                telemetry.addData("Zone", zone);
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

        private volatile int averageRed;
        private volatile int averageBlue;
        private volatile int averageGreen;
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

            averageBlue = (int) Core.mean(region1_Cb).val[0]; // red average values
            averageGreen = (int) Core.mean(region1_Cg).val[0]; // blue average values
            averageRed = (int) Core.mean(region1_Cr).val[0]; // green average values

            Imgproc.rectangle(input, topLeft1, bottomRight1, BLUE, 2);
            Imgproc.rectangle(input, topLeft2, bottomRight2, BLUE, 2);
            Imgproc.rectangle(input, topLeft3, bottomRight3, BLUE, 2);

            if (averageBlue < averageRed && averageGreen < averageRed) {
                type = TYPE.ZONE1;
            }
            else if (averageBlue < averageGreen && averageRed < averageGreen) {
                type = TYPE.ZONE2;
            }
            else if (averageGreen < averageBlue && averageRed < averageBlue) {
                type = TYPE.ZONE3;
            }
            else {
                type = null;
            }

            return input;
        }

        public TYPE getType() {
            return type;
        }

        public int[] getAverage () {
            int[] averages = {averageRed, averageGreen, averageBlue};
            return averages;
        }

        public enum TYPE {
            ZONE1, ZONE2, ZONE3
        }
    }
    public enum Location {
        BlueTop,
        BlueBottom,
        RedTop,
        RedBottom
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