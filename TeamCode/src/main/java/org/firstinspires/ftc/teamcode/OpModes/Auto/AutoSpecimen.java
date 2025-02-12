package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.CustomActions;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.HorizSlides;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeWrist;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.VertSlides;
import org.firstinspires.ftc.teamcode.Util.Positions;


@Autonomous(name = "Auto Right Specimen", group = "TestOpModes")
public class AutoSpecimen extends LinearOpMode {

    public static double p = 0.15, i = 0.0001, d = 0.03;
    public static double p2 = 0.06,i2 = 0.0001, d2 = 0.03;
    public static double p3 = 1.2,i3 = 0.0,d3 = 0.08;


    @Override
    public void runOpMode() {
        //telemetry = FtcDashboard.getInstance().getTelemetry();

        Localizer localizer = new Localizer(hardwareMap, new Poses(8.0,-63.0,0.0));
        Drive drive = new Drive(hardwareMap);
        IntakeClaw intakeClaw = new IntakeClaw(hardwareMap);
        OutakeClaw outakeClaw = new OutakeClaw(hardwareMap);
        IntakeForearm intakeForearm = new IntakeForearm(hardwareMap);
        OutakeForearm outakeForearm = new OutakeForearm(hardwareMap);
        IntakeWrist intakeWrist = new IntakeWrist(hardwareMap);
        HorizSlides horizSlides = new HorizSlides(hardwareMap);
        VertSlides vertSlides = new VertSlides(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);
        customActions.update();

        waitForStart();

        Actions.runBlocking(
                new ParallelAction(
                        telemetryPacket -> {
                            localizer.update();
                            customActions.update();
                            telemetry.addData("X pos", Localizer.pose.getX());
                            telemetry.addData("Y pos", Localizer.pose.getY());
                            telemetry.addData("Heading pos", Localizer.pose.getHeading());


                            for(String string: customActions.getTelemetry()) telemetry.addLine(string);
                            telemetry.update();
                            return true;
                        },
                        new SequentialAction(
                                //Go to rung - drop specimen 1
                                customActions.openIntakeClaw,
                                new SleepAction(0.25),
                                customActions.closeOutakeClaw,
                                new SleepAction(0.25),
                                customActions.prepareHighRung,
                                customActions.resestTimer,
                                new SleepAction(0.25),
                                customActions.intakeForeArmSubEdge,
                                new SleepAction(0.25),
                                Positions.HighRung1.runToExact,
                                new SleepAction(.25),
                                Positions.HighRungTiny1.runToExact,
                                customActions.stopDrive,
                                new ParallelAction(
                                        Positions.HighRungTiny1.runToExact,
                                        customActions.vertSlidesSpecimenAlignUp
                                ),

                                new SleepAction(.5),
                                customActions.openOutakeClaw,
                                new SleepAction(0.25),
                                customActions.prepareSpecimenPickup,
                                Positions.PrepareForPushStep1.runToExact,
                                new SleepAction(.25),
                                customActions.stopDrive,
                                Positions.PrepareForPushStep2.runToExact,
                                new SleepAction(.25),
                                Positions.PrepareForPushStep3.runToExact,
                                new SleepAction(.25),
                                Positions.PushSample1.runToExact,
                                new SleepAction(.25),
                                Positions.PrepareForPushStep4.runToExact,
                                new SleepAction(.25),
                                Positions.PrepareForPushStep5.runToExact,
                                new SleepAction(.25),
                                Positions.PushSample2.runToExact,
                                new SleepAction(.25),


                                //pick up specimen 2
                                Positions.ObsZonePickupSpecimen.runToExact,
                                new SleepAction(.25),
                                Positions.ObsZonePickupSpecimenTiny.runToExact,
                                new SleepAction(.25),
                                customActions.stopDrive,
                                customActions.closeOutakeClaw,
                                new SleepAction(0.25),
                                customActions.prepareHighRung,
                                new SleepAction(0.25),
                                Positions.HighRung2.runToExact,
                                new SleepAction(.25),
                                Positions.HighRungTiny2.runToExact,
                                new SleepAction(.25),
                                customActions.stopDrive,
                                new ParallelAction(
                                        Positions.HighRungTiny2.runToExact,
                                        customActions.vertSlidesSpecimenAlignUp
                                ),

                                new SleepAction(.5),
                                customActions.openOutakeClaw,
                                new SleepAction(0.25),

                                //Pickup specimen 3
                                customActions.prepareSpecimenPickup,
                                Positions.ObsZonePickupSpecimen.runToExact,
                                new SleepAction(.25),
                                Positions.ObsZonePickupSpecimenTiny.runToExact,
                                new SleepAction(.25),
                                customActions.stopDrive,
                                customActions.closeOutakeClaw,
                                new SleepAction(0.25),
                                customActions.prepareHighRung,
                                new SleepAction(0.25),
                                Positions.HighRung3.runToExact,
                                new SleepAction(.25),
                                Positions.HighRungTiny3.runToExact,
                                new SleepAction(.25),
                                customActions.stopDrive,
                                new ParallelAction(
                                        Positions.HighRungTiny3.runToExact,
                                        customActions.vertSlidesSpecimenAlignUp
                                ),

                                new SleepAction(.5),
                                customActions.openOutakeClaw,
                                new SleepAction(0.25),

                                new ParallelAction(
                                        Positions.Parking.runToExact,
                                        customActions.prepareObsZoneParking
                                ),

                                customActions.stopDrive


                        )

                )
        );

    }
}



