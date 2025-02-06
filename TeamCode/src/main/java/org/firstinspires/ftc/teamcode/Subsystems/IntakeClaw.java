package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {
    private final Servo IntakeClawServo;
    public State state = State.OUT;
    public boolean isTargetReached = false;
    public static IntakeClaw instance;

    public enum State {
        IN, OUT, STOP, MIDDLE
    }

    public IntakeClaw(HardwareMap hardwareMap) {
        IntakeClawServo = hardwareMap.get(Servo.class, "InTakeClaw");

        instance = this;
    }

    public void update() {
        switch (state) {
            case IN:
                IntakeClawServo.setPosition(1);
                break;
            case OUT:
                IntakeClawServo.setPosition(0);
                break;

            case STOP:
                IntakeClawServo.setPosition(0);
                break;
            case MIDDLE:
                IntakeClawServo.setPosition(0.5);
                break;
        }

        if (state == State.IN && IntakeClawServo.getPosition() == 1){
            isTargetReached = true;
        } else if (state == State.OUT && IntakeClawServo.getPosition() == 0) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && IntakeClawServo.getPosition() == 0.5) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getClawTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Intake Claw Position = " + IntakeClawServo.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
