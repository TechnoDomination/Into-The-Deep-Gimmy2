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
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.HorizSlides;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeWrist;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.VertSlides;
import org.firstinspires.ftc.teamcode.Util.Positions;


@Autonomous(name = "Auto Left Sample", group = "TestOpModes")
public class AutoSample extends LinearOpMode {

    public static double p = 0.15, i = 0.0001, d = 0.03;
    public static double p2 = 0.06,i2 = 0.0001, d2 = 0.03;
    public static double p3 = 1.2,i3 = 0.0,d3 = 0.08;


    @Override
    public void runOpMode() {
        //telemetry = FtcDashboard.getInstance().getTelemetry();

        Localizer localizer = new Localizer(hardwareMap, new Poses(-35,-63,0.0));
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
                                //Go to basket - drop sample 1
                                customActions.openIntakeClaw,
                                new SleepAction(0.25),
                                customActions.closeOutakeClaw,
                                new SleepAction(0.25),

                                Positions.GoFrontSample.runToExact,
                                new SleepAction(0.5),
                                Positions.Basket.runToExact,
                                new SleepAction(0.5),
                                new ParallelAction(
                                        Positions.Basket.runToExact,
                                        customActions.prepareHighBasket
                                ),
                                customActions.stopDrive,
                                new SleepAction(1),
                                customActions.intakeForeArmSubEdge,
                                new SleepAction(0.25),
                                customActions.outakeForeArmBasketScoring,
                                new SleepAction(1),
                                customActions.openOutakeClaw,
                                new SleepAction(0.25),

                                //Pick sample 2
                                customActions.outakeForeArmSpecimenScoring,
                                new SleepAction(0.5),
                                customActions.prepareOutakeTransfer,
                                new SleepAction(0.25),
                                Positions.LeftSample1.runToExact,
                                customActions.stopDrive,
                                new ParallelAction(
                                        Positions.LeftSample1.runToExact,
                                        customActions.prepareSamplePick
                                ),
                                customActions.stopDrive,
                                new SleepAction(0.5),
                                customActions.intakeForeArmSamplePick,
                                new SleepAction(0.5),
                                customActions.closeIntakeClaw,
                                new SleepAction(0.5),
                                customActions.intakeForeArmSubEdge,
                                new SleepAction(0.25),
                                customActions.prepareIntakeTransfer,
                                new SleepAction(0.6),
                                customActions.intakeForeArmIn,
                                new SleepAction(0.6),
                                customActions.outakeForeArmTransfer,
                                new SleepAction(0.5),
                                customActions.closeOutakeClaw,
                                new SleepAction(0.25),
                                customActions.openIntakeClaw,
                                new SleepAction(0.25),

                                //Drop sample 2
                                Positions.Basket.runToExact,
                                new SleepAction(0.5),
                                new ParallelAction(
                                        Positions.Basket.runToExact,
                                        customActions.prepareHighBasket
                                ),
                                customActions.stopDrive,
                                new SleepAction(1),
                                customActions.intakeForeArmSubEdge,
                                new SleepAction(0.25),
                                customActions.outakeForeArmBasketScoring,
                                new SleepAction(1),
                                customActions.openOutakeClaw,
                                new SleepAction(0.25),
                                customActions.outakeForeArmSpecimenScoring,
                                new SleepAction(0.5),
                                customActions.prepareOutakeTransfer,
                                new SleepAction(0.25),

                                //Pick sample 3
                                Positions.LeftSample2.runToExact,
                                customActions.stopDrive,
                                new ParallelAction(
                                        Positions.LeftSample2.runToExact,
                                        customActions.prepareSamplePick
                                ),
                                customActions.stopDrive,
                                new SleepAction(0.75),
                                customActions.intakeForeArmSamplePick,
                                new SleepAction(0.5),
                                customActions.closeIntakeClaw,
                                new SleepAction(.5),
                                customActions.intakeForeArmSubEdge,
                                new SleepAction(0.25),
                                customActions.prepareIntakeTransfer,
                                new SleepAction(0.6),
                                customActions.intakeForeArmIn,
                                new SleepAction(0.6),
                                customActions.outakeForeArmTransfer,
                                new SleepAction(0.5),
                                customActions.closeOutakeClaw,
                                new SleepAction(0.25),
                                customActions.openIntakeClaw,
                                new SleepAction(0.25),

                                //Drop sample 3
                                Positions.Basket.runToExact,
                                new SleepAction(0.5),
                                new ParallelAction(
                                        Positions.Basket.runToExact,
                                        customActions.prepareHighBasket
                                ),
                                customActions.stopDrive,
                                new SleepAction(1),
                                customActions.intakeForeArmSubEdge,
                                new SleepAction(0.25),
                                customActions.outakeForeArmBasketScoring,
                                new SleepAction(1),
                                customActions.openOutakeClaw,
                                new SleepAction(0.25),
                                customActions.outakeForeArmSpecimenScoring,
                                new SleepAction(0.5),
                                customActions.prepareOutakeTransfer,
                                new SleepAction(0.25),

                                customActions.stopDrive

                        )

                )
        );

    }
}



