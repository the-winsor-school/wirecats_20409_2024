package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Close Park")
public class ClosePark extends LinearOpMode {

    Robot robot;
    AutonMovements autonMovements;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AutonMovements(this, robot);
        waitForStart();

        if (opModeIsActive())  {
            autonMovements.ClosePark();
        }
    }
}
