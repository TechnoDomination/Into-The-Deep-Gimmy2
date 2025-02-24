package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.OutakeForearm;


@TeleOp(name="ServoTest", group="TestOpModes")
public class ServoTest extends LinearOpMode {

    private  Servo fingers;
    private  Servo wrist;
    private  Servo forearm;

    @Override
    public void runOpMode() throws InterruptedException {
        //initalization phase
        //fingers = hardwareMap.get(Servo.class, "Fingers");
        //wrist = hardwareMap.get(Servo.class, "Wrist");
        //forearm = hardwareMap.get(Servo.class, "Forearm");
        OutakeForearm outakeForearm = new OutakeForearm(hardwareMap);
        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            outakeForearm.update();

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

           // telemetry.addData("Servo1 position: " , fingers.getPosition());
            //telemetry.addData("Servo2 position: " , wrist.getPosition());
            //telemetry.addData("Servo3 position: " , forearm.getPosition());
            telemetry.update();

        }

    }
}
