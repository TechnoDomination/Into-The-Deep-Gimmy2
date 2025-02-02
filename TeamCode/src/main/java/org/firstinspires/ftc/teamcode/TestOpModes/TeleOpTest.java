package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.teamcode.Subsystems.Slides;

@TeleOp(name="TeleOpTest", group="TestOpModes")
public class TeleOpTest extends LinearOpMode {

    public DcMotorEx horizSlide;
    public DcMotorEx rightVertSlide;
    public DcMotorEx leftVertSlide;
    private Servo intakeClaw;
    private  Servo intakeClawRotator;
    private  Servo intakeArm;
    private Servo outakeClaw;
    private Servo outakeArm;

    @Override
    public void runOpMode() throws InterruptedException {

        horizSlide = hardwareMap.get(DcMotorEx.class, "HorizSlide");
        horizSlide.setDirection(DcMotor.Direction.REVERSE);
        rightVertSlide = hardwareMap.get(DcMotorEx.class, "RightVertSlideMotor");
        leftVertSlide = hardwareMap.get(DcMotorEx.class, "LeftVertSlideMotor");
        rightVertSlide.setDirection(DcMotor.Direction.REVERSE);

        intakeClaw = hardwareMap.get(Servo.class, "InTakeClaw");
        intakeClawRotator = hardwareMap.get(Servo.class, "InTakeClawRotator");
        intakeArm = hardwareMap.get(Servo.class, "InTakeArm");
        outakeClaw = hardwareMap.get(Servo.class, "OutTakeClaw");
        outakeArm = hardwareMap.get(Servo.class, "OutTakeArm");
        horizSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        horizSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeArm.setPosition(0.35);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            if(gamepad2.dpad_up) {
                horizSlide.setPower(1);
            }
            if(gamepad2.dpad_down) {
                horizSlide.setPower(-1);
            }
            if(gamepad2.dpad_right) {
                horizSlide.setPower(0);
            }
            if (gamepad2.y){
                intakeClaw.setPosition(0);
            }

            if (gamepad2.a){
                intakeClaw.setPosition(1);
            }

            if (gamepad2.x){
                intakeClawRotator.setPosition(0);
            }

            if (gamepad2.b){
                intakeClawRotator.setPosition(0.5);
            }

            if (gamepad2.left_bumper){
                intakeArm.setPosition(0.35);
            }

            if (gamepad2.right_bumper){
                intakeArm.setPosition(0.9);
            }
            if (gamepad1.right_bumper){
                outakeClaw.setPosition(0);
            }
            if (gamepad1.left_bumper){
                outakeClaw.setPosition(1);
            }
            if (gamepad1.a){
                outakeArm.setPosition(0.9);
            }
            if (gamepad1.b){
                outakeArm.setPosition(0.5);
            }
            if (gamepad1.y){
                outakeArm.setPosition(0.1);
            }
            if (gamepad1.dpad_up){
                rightVertSlide.setPower(-1);
                leftVertSlide.setPower(-1);
            }
            if (gamepad1.dpad_down){
                rightVertSlide.setPower(1);
                leftVertSlide.setPower(1);
            }
            if (gamepad1.dpad_right){
                rightVertSlide.setPower(0);
                leftVertSlide.setPower(0);
            }
            telemetry.addData("fingers position: " , intakeClaw.getPosition());
            telemetry.addData("wrist position: " , intakeClawRotator.getPosition());
            telemetry.addData("forearm position: " , intakeArm.getPosition());
            telemetry.addData("fingers position: " , outakeClaw.getPosition());

            telemetry.addData("middle intake slide position: " , horizSlide.getCurrentPosition());
            telemetry.update();
        }
    }
}
