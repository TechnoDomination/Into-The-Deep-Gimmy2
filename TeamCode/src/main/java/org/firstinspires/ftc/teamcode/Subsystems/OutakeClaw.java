package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OutakeClaw {
    private final Servo OutakeClawServo;
    public State state = State.CLOSE;
    public boolean isTargetReached = false;
    public static OutakeClaw instance;
    private double openPos = 1;
    private double closePos = 0;
    private double stopPos = 0;
    private double middlePos = 0.5;

    public enum State {
        CLOSE, OPEN, STOP, MIDDLE
    }

    public OutakeClaw(HardwareMap hardwareMap) {
        OutakeClawServo = hardwareMap.get(Servo.class, "OutTakeClaw");

        instance = this;
    }

    public void update() {
        switch (state) {
            case CLOSE:
                OutakeClawServo.setPosition(closePos);
                break;
            case OPEN:
                OutakeClawServo.setPosition(openPos);
                break;

            case STOP:
                OutakeClawServo.setPosition(stopPos);
                break;
            case MIDDLE:
                OutakeClawServo.setPosition(middlePos);
                break;
        }

        if (state == State.CLOSE && OutakeClawServo.getPosition() == closePos){
            isTargetReached = true;
        } else if (state == State.OPEN && OutakeClawServo.getPosition() == openPos) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && OutakeClawServo.getPosition() == middlePos) {
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
