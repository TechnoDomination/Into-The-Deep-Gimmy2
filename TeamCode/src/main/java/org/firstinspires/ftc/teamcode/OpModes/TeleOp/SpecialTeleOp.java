package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.VertSlides;
import org.firstinspires.ftc.teamcode.Subsystems.HorizSlides;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeForearm;

@TeleOp(name="SpecialTeleOp", group="TeleOp")
public class SpecialTeleOp extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        VertSlides vertSlides = new VertSlides(hardwareMap);
        HorizSlides horizSlides = new HorizSlides(hardwareMap);
        OutakeForearm outakeForearm = new OutakeForearm (hardwareMap);
        IntakeForearm intakeForearm = new IntakeForearm (hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            //if (gamepad1.left_bumper) {
            intakeForearm.update();
            //vertSlides.LeftVertSlide.setPower(1);
            //vertSlides.RightVertSlide.setPower(1);
            outakeForearm.state = OutakeForearm.State.SPECIMENSCORING;
            new SleepAction(0.5);
            horizSlides.horizSlide.setPower(-1);
            vertSlides.LeftVertSlide.setPower(-1);
            vertSlides.RightVertSlide.setPower(-1);
            intakeForearm.state = IntakeForearm.State.IN;
            //}
        }
    }
}
