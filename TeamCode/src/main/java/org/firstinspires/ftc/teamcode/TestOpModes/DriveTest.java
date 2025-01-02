package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

@TeleOp(name = "DriveTest",group = "TestOpModes")
public class DriveTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        Localizer localizer = new Localizer(hardwareMap, new Poses(0.0,0.0,0.0));
        Drive drive = new Drive(hardwareMap);

        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            localizer.update();
            telemetry.addData("X pos", Localizer.pose.getX());
            telemetry.addData("Y pos", Localizer.pose.getY());
            telemetry.addData("Heading pos", Localizer.pose.getHeading());
            drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            telemetry.update();
        }
    }
}