package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OutakeClaw {
    private final Servo OutakeClawServo;
    public State state = State.IN;
    public boolean isTargetReached = false;
    public static OutakeClaw instance;

    public enum State {
        IN, OUT, STOP, MIDDLE
    }

    public OutakeClaw(HardwareMap hardwareMap) {
        OutakeClawServo = hardwareMap.get(Servo.class, "OutTakeClaw");

        instance = this;
    }

    public void update() {
        switch (state) {
            case IN:
                OutakeClawServo.setPosition(1);
                break;
            case OUT:
                OutakeClawServo.setPosition(0);
                break;

            case STOP:
                OutakeClawServo.setPosition(0);
                break;
            case MIDDLE:
                OutakeClawServo.setPosition(0.5);
                break;
        }

        if (state == State.IN && OutakeClawServo.getPosition() == 1){
            isTargetReached = true;
        } else if (state == State.OUT && OutakeClawServo.getPosition() == 0) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && OutakeClawServo.getPosition() == 0.5) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getClawTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Outtake Claw Position = " + OutakeClawServo.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
