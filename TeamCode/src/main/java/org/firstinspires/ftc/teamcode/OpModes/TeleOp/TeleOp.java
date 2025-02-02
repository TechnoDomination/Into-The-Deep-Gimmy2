package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.HorizSlides;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeWrist;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.VertSlides;

import java.util.ArrayList;
import java.util.List;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp",group = "TeleOp")
public class TeleOp extends LinearOpMode {

    private List<Action> runningActions = new ArrayList<>();
    public static DistanceSensor DistanceSensor;
    private double distanceInch;


    @Override
    public void runOpMode() {

        Localizer localizer = new Localizer(hardwareMap, new Poses(0.0,0.0,0.0));
        Drive drive = new Drive(hardwareMap);
        IntakeClaw intakeClaw = new IntakeClaw(hardwareMap);
        OutakeClaw outakeClaw = new OutakeClaw(hardwareMap);
        IntakeForearm intakeForearm = new IntakeForearm(hardwareMap);
        OutakeForearm outakeForearm = new OutakeForearm(hardwareMap);
       // IntakeWrist intakeWrist = new IntakeWrist(hardwareMap);
        HorizSlides horizSlides = new HorizSlides(hardwareMap);
        VertSlides vertSlides = new VertSlides(hardwareMap);
        //Arm arm = new Arm(hardwareMap);
        //CustomActions customActions = new CustomActions(hardwareMap);

        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            localizer.update();
            horizSlides.update();
            vertSlides.update();
            intakeClaw.update();
            outakeClaw.update();
            intakeForearm.update();
            outakeForearm.update();
           // intakeWrist.update();
            //arm.update();
            //customActions.update();

            telemetry.addData("X pos", Localizer.pose.getX());
            telemetry.addData("Y pos", Localizer.pose.getY());
            telemetry.addData("Heading pos", Localizer.pose.getHeading());

            //Drive Controls
            drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            //Intake Claw Controls
            if (gamepad1.left_bumper) {
                intakeClaw.state = IntakeClaw.State.IN;
            }
            if (gamepad1.right_bumper) {
                intakeClaw.state = IntakeClaw.State.OUT;
            }
            telemetry.addData("Intake Claw Telemetry = ", intakeClaw.getClawTelemetry());

            //Outtake Claw Controls
            if (gamepad2.left_bumper) {
                outakeClaw.state = OutakeClaw.State.IN;
            }
            if (gamepad2.right_bumper) {
                outakeClaw.state = OutakeClaw.State.OUT;
            }
            telemetry.addData("Outtake Claw Telemetry = ", outakeClaw.getClawTelemetry());

            //Intake Forearm Controls
            if (gamepad1.y) {
                intakeForearm.state = IntakeForearm.State.IN;
            }
            if (gamepad1.a) {
                intakeForearm.state = IntakeForearm.State.OUT;
            }
            telemetry.addData("Intake Forearm Telemetry = ", intakeForearm.getClawTelemetry());

            //Outtake Forearm Controls
            if (gamepad1.b) {
                outakeForearm.state = OutakeForearm.State.IN;
            }
            if (gamepad1.x) {
                outakeForearm.state = OutakeForearm.State.OUT;
            }
            telemetry.addData("Intake Forearm Telemetry = ", outakeForearm.getClawTelemetry());

/*
            //IntakeWrist Controls
            if (gamepad2.y) {
                intakeWrist.state = IntakeWrist.State.IN;
            }
            if (gamepad2.a) {
                intakeWrist.state = IntakeWrist.State.OUT;
            }
            telemetry.addData("Intake Wrist Telemetry = ", intakeWrist.getIntakeWristTelemetry());
*/

            //Slides Controls
            if (gamepad1.dpad_down) {
                horizSlides.state = HorizSlides.State.FULLIN;
            } else if (gamepad1.dpad_up) {
                horizSlides.state = HorizSlides.State.FULLOUT;
            }

            telemetry.addData("HorizSlides Telemetry = ", horizSlides.getSlidesTelemetry());

            if (gamepad2.dpad_down) {
                vertSlides.state = VertSlides.State.HIGHBASKETSAMPLEDROP;
            } else if (gamepad2.dpad_up) {
                vertSlides.state = VertSlides.State.FULLDOWN;
            } else if (gamepad2.dpad_right) {
                vertSlides.state = VertSlides.State.SPECIMENALIGNDOWN;
            }

            telemetry.addData("VertSlides Telemetry = ", vertSlides.getSlidesTelemetry());
/*
            Arm Controls
            if (gamepad2.y) {
                arm.state = Arm.State.VERTICAL;
            }
            else if (gamepad2.a) {
                arm.state = Arm.State.SAMPLEPICKING;
            } else if (gamepad2.x) {
                arm.state = Arm.State.SPECIMENPICKING;
            } else if (gamepad2.b) {
                arm.state = Arm.State.SPECIMENPICKING;
            } else if (gamepad2.b && gamepad2.y) {
                arm.state = Arm.State.SAMPLEDEPOSIT;
            } else if (gamepad2.dpad_up) {
                arm.state = Arm.State.HANGINGPOSITION;
            }

            telemetry.addData("Arm Telemetry = ", arm.getArmTelemetry());

            TelemetryPacket packet = new TelemetryPacket();

            // updated based on gamepads

            // update running actions
            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;

            //Sample scoring
            if (gamepad1.dpad_up){
                telemetry.addData("In Dpad Up ",gamepad1.dpad_up );
                runningActions.add(new SequentialAction(
                        customActions.prepareHighBasket,
                        new SleepAction(.25),
                        customActions.armSampleDeposit,
                        new SleepAction(.25),
                        customActions.openClawTeleOpHB,
                        new SleepAction(.25),
                        customActions.armSamplePreperation,
                        new SleepAction(0.25),
                        customActions.slidesFullDown
                ));

            }

            //Specimen scoring
            if (gamepad1.dpad_down) {
                telemetry.addData("In Dpad Down ", gamepad1.dpad_down);
                runningActions.add(new SequentialAction(
                        customActions.prepareHighRungTest,
                        customActions.resestTimer,
                        new SleepAction(0.25),
                        customActions.slidesHighRungDownTest,
                        new SleepAction(0.5),
                        customActions.openClaw,
                        new SleepAction(0.5)
                ));

            }*/

            telemetry.update();


        }
    }
}