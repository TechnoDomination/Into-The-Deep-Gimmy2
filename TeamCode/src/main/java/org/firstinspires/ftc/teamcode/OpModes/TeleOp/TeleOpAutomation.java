//PLEASE WORK PLEASEEEEE
package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

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
import org.firstinspires.ftc.teamcode.Util.SmoothGamepad;

import java.util.ArrayList;
import java.util.List;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOpAutomation",group = "TeleOp")
public class TeleOpAutomation extends LinearOpMode {

    private List<Action> runningActions = new ArrayList<>();
    public static DistanceSensor DistanceSensor;
    private double distanceInch;
    private boolean isSample = false;
    private boolean isSpecimen = false;


    @Override
    public void runOpMode() {

        Localizer localizer = new Localizer(hardwareMap, new Poses(0.0, 0.0, 0.0));
        Drive drive = new Drive(hardwareMap);
        IntakeClaw intakeClaw = new IntakeClaw(hardwareMap);
        OutakeClaw outakeClaw = new OutakeClaw(hardwareMap);
        IntakeForearm intakeForearm = new IntakeForearm(hardwareMap);
        OutakeForearm outakeForearm = new OutakeForearm(hardwareMap);
        IntakeWrist intakeWrist = new IntakeWrist(hardwareMap);
        HorizSlides horizSlides = new HorizSlides(hardwareMap);
        VertSlides vertSlides = new VertSlides(hardwareMap);
        //Arm arm = new Arm(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);
        SmoothGamepad smoothGamepad = new SmoothGamepad();

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            localizer.update();
            horizSlides.update();
            vertSlides.update();
            intakeClaw.update();
            outakeClaw.update();
            intakeForearm.update();
            outakeForearm.update();
            intakeWrist.update();
            //arm.update();
            customActions.update();

            telemetry.addData("X pos", Localizer.pose.getX());
            telemetry.addData("Y pos", Localizer.pose.getY());
            telemetry.addData("Heading pos", Localizer.pose.getHeading());

            if (gamepad1.start)
            {
                isSpecimen = true;
                isSample = false;
            }

            if(gamepad1.back)
            {
                isSpecimen = false;
                isSample = true;
            }

            //Drive Controls
           // drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
           drive.update(smoothGamepad.smoothGamepad( -gamepad1.left_stick_y), smoothGamepad.smoothGamepad(gamepad1.left_stick_x), smoothGamepad.smoothGamepad(gamepad1.right_stick_x));

            //intakeWrist.state = IntakeWrist.State.MIDDLE;

            //Intake Claw Controls
            if (gamepad1.right_bumper) {
                intakeClaw.state = IntakeClaw.State.CLOSE;
            }
            if (gamepad1.left_bumper) {
                intakeClaw.state = IntakeClaw.State.OPEN;
            }

            telemetry.addData("Intake Claw Telemetry = ", intakeClaw.getClawTelemetry());

            //Intake Forearm Controls
            if (gamepad1.a) {
                intakeForearm.state = IntakeForearm.State.IN;
            }
            if (gamepad1.y) {
                intakeForearm.state = IntakeForearm.State.SAMPLEPICK;
            }
            if (gamepad1.b) {
                intakeForearm.state = IntakeForearm.State.SUBMERSIBLEEDGE;
            }

            telemetry.addData("Outtake Forearm Telemetry = ", outakeForearm.getArmTelemetry());

            //IntakeWrist Controls
            if (gamepad1.dpad_right) {
                intakeWrist.state = IntakeWrist.State.IN;
            }
            if (gamepad1.dpad_left) {
                intakeWrist.state = IntakeWrist.State.MIDDLE;
            }
            telemetry.addData("Intake Wrist Telemetry = ", intakeWrist.getIntakeWristTelemetry());

            //Outtake Forearm Controls
            if (gamepad2.b) {
                outakeForearm.state = OutakeForearm.State.SPECIMENSCORING;
            }
            if (gamepad2.a) {
                outakeForearm.state = OutakeForearm.State.IN;
            }
            if (gamepad2.y) {
                outakeForearm.state = OutakeForearm.State.BASKETSCORING;
            }
            if (gamepad2.x) {
                outakeForearm.state = OutakeForearm.State.SPECIMENPICK;
            }
            telemetry.addData("Outtake Forearm Telemetry = ", outakeForearm.getArmTelemetry());


            //Outtake Claw Controls
            if (gamepad2.right_bumper) {
                //intakeClaw.state = IntakeClaw.State.OUT;
                outakeClaw.state = OutakeClaw.State.CLOSE;
            }
            if (gamepad2.left_bumper) {
                outakeClaw.state = OutakeClaw.State.OPEN;
            }
            telemetry.addData("Outtake Claw Telemetry = ", outakeClaw.getClawTelemetry());

            //Horiz Slides Controls
            if (gamepad1.dpad_down) {
                horizSlides.state = HorizSlides.State.TRANSFER;
            } else if (gamepad1.dpad_up) {
                horizSlides.state = HorizSlides.State.FULLOUT;
            }


            telemetry.addData("HorizSlides Telemetry = ", horizSlides.getSlidesTelemetry());

            if (gamepad2.dpad_down) {
                vertSlides.state = VertSlides.State.FULLDOWN;
            }

            //Vert Slides Controls
            if (gamepad2.dpad_up) {
                vertSlides.state = VertSlides.State.HIGHBASKETSAMPLEDROP;
            } else if (gamepad2.dpad_down) {
                vertSlides.state = VertSlides.State.FULLDOWN;
            } else if (gamepad2.dpad_right) {
                vertSlides.state = VertSlides.State.SPECIMENALIGNDOWN;
            } else if (gamepad2.dpad_left) {
                vertSlides.state = VertSlides.State.SPECIMENALIGNUP;
            }



            telemetry.addData("VertSlides Telemetry = ", vertSlides.getSlidesTelemetry());

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

            if (gamepad1.dpad_up){
                telemetry.addData("In 1Dpad Up ",gamepad1.dpad_up );
                runningActions.add(new SequentialAction(
                        customActions.openOutakeClaw,
                        customActions.outakeForeArmSpecimenScoring,
                        customActions.intakeWristMiddle,
                        customActions.intakeForeArmSubEdge,
                        new SleepAction(0.25),
                        customActions.prepareSamplePick,
                        new SleepAction(.25)

                ));

            }

            if (gamepad1.dpad_down && isSample){
                telemetry.addData("In 1Dpad Down ",gamepad1.dpad_down );
                runningActions.add(new SequentialAction(
                        customActions.openOutakeClaw,
                        customActions.intakeForeArmSubEdge,
                        new SleepAction(0.25),
                        customActions.prepareIntakeTransfer,
                        new SleepAction(.5),
                        customActions.intakeWristMiddle,
                        new SleepAction(.5),
                        customActions.intakeForeArmIn,
                        new SleepAction(.5),
                        customActions.OutakeClawDown,
                        new SleepAction(.5),
                        customActions.closeOutakeClaw,
                        new SleepAction(.5),
                        customActions.openIntakeClaw,
                        new SleepAction(.5),

                        customActions.outakeArmSpecimenScore
                ));

            }

            if (gamepad1.dpad_down && isSpecimen){
                telemetry.addData("In 1Dpad Down ",gamepad1.dpad_down );
                runningActions.add(new SequentialAction(
                        customActions.openOutakeClaw,
                        customActions.intakeForeArmSubEdge,
                        new SleepAction(0.25),
                        customActions.prepareIntakeTransfer,
                        new SleepAction(.5),
                        customActions.intakeWristMiddle,
                        new SleepAction(.5),
                        customActions.intakeForeArmIn,
                        new SleepAction(.5),

                        customActions.outakeArmSpecimenScore
                ));

            }


            if (gamepad2.dpad_up){
                telemetry.addData("In 2Dpad Up ",gamepad2.dpad_up );
                runningActions.add(new SequentialAction(
                        customActions.prepareHighBasket,
                        new SleepAction(1),
                        customActions.outakeForeArmBasketScoring,
                        new SleepAction(.5),
                        customActions.openOutakeClaw,
                        new SleepAction(0.5),
                        customActions.outakeForeArmSpecimenScoring,
                        new SleepAction(0.5),
                        customActions.prepareOutakeTransfer

                ));

            }

            if (gamepad2.dpad_left){
                telemetry.addData("In 2Dpad Left ",gamepad2.dpad_left );
                runningActions.add(new SequentialAction(
                        customActions.prepareHighRung,
                        customActions.intakeForeArmMiddle,
                        new SleepAction(0.25)

                ));

            }

            if (gamepad2.dpad_right){
                telemetry.addData("In 2Dpad Right ",gamepad2.dpad_right );
                runningActions.add(new SequentialAction(
                        customActions.intakeForeArmMiddle,
                        customActions.vertSlidesSpecimenAlignUp,
                        new SleepAction(0.75),
                        customActions.openOutakeClaw,
                        new SleepAction(0.5),
                        customActions.prepareSpecimenPickup

                ));

            }



            telemetry.update();



        }
    }
}