package org.firstinspires.ftc.teamcode.TestOpModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.PIDFParams;
import org.firstinspires.ftc.teamcode.Util.Positions;

@Config
@Autonomous(name = "Auto Drive Specimen Test", group = "Tuning OpModes")
public class AutoDriveSpecimenTest extends LinearOpMode {

    public static double p = 0.15, i = 0.0001, d = 0.03;
    public static double p2 = 0.06,i2 = 0.0001, d2 = 0.01;
    public static double p3 = 1.2,i3 = 0.0001,d3 = 0.08;

    @Override
    public void runOpMode() {
        //telemetry = FtcDashboard.getInstance().getTelemetry();

        Localizer localizer = new Localizer(hardwareMap, new Poses(8.0,-63.0,0.0));
        Drive drive = new Drive(hardwareMap);


        waitForStart();

        Actions.runBlocking(
                new ParallelAction(
                        telemetryPacket -> {
                            localizer.update();
                            drive.xPid.setPIDF(new PIDFParams(p,i,d));
                            drive.yPid.setPIDF(new PIDFParams(p2,i2,d2));
                            drive.rPid.setPIDF(new PIDFParams(p3,i3,d3));

                            telemetry.addData("X pos", Localizer.pose.getX());
                            telemetry.addData("Y pos", Localizer.pose.getY());
                            telemetry.update();
                            return true;
                        },
                        new SequentialAction(
                                Positions.HighRungTest.runToExact,
                                new SleepAction(1),
                                Positions.GoFrontTinySpecimenTest.runToExact,
                                new SleepAction(1),

                                Positions.GoBackSpecimen.runToExact,
                                new SleepAction(1),
                                Positions.FirstColorSampleTest.runToExact,
                                new SleepAction(1),

                                Positions.DropColorSampleTest.runToExact,
                                new SleepAction(1),
                                Positions.SpecimenObZone.runToExact,
                                new SleepAction(1),
                                Positions.SecondColorSampleTest.runToExact,
                                new SleepAction(1),
                                Positions.DropColorSampleTest.runToExact,
                                new SleepAction(1),
                                Positions.ObserservationZoneParkingSpecimen.runToExact,
                                Action -> {
                                    drive.stopDrive();
                                    return false;
                                }
                        )
                )
        );

    }
}



