package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="ServoTest", group="TestOpModes")
public class ServoTest extends LinearOpMode {

    private  Servo fingers;
    private  Servo wrist;
    private  Servo forearm;

    @Override
    public void runOpMode() throws InterruptedException {
        //initalization phase
        fingers = hardwareMap.get(Servo.class, "Fingers");
        wrist = hardwareMap.get(Servo.class, "Wrist");
        forearm = hardwareMap.get(Servo.class, "Forearm");
        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad2.y){
               fingers.setPosition(0);
            }

            if (gamepad2.a){
                fingers.setPosition(1);
            }

            if (gamepad2.x){
                wrist.setPosition(0);
            }

            if (gamepad2.b){
                wrist.setPosition(0.5);
            }

            if (gamepad2.left_bumper){
                forearm.setPosition(0.25);
            }

            if (gamepad2.right_bumper){
                forearm.setPosition(0.9);
            }

            telemetry.addData("Servo1 position: " , fingers.getPosition());
            telemetry.addData("Servo2 position: " , wrist.getPosition());
            telemetry.addData("Servo3 position: " , forearm.getPosition());
            telemetry.update();

        }

    }
}
