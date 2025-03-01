package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {
    private final Servo IntakeClawServo;
    public State state = State.OPEN;
    public boolean isTargetReached = false;
    public static IntakeClaw instance;
    private double openPos = 0.5;
    private double closePos = 1;
    private double stopPos = 0;
    private double middlePos = 0.5;

    public enum State {
        CLOSE, OPEN, STOP, MIDDLE
    }

    public IntakeClaw(HardwareMap hardwareMap) {
        IntakeClawServo = hardwareMap.get(Servo.class, "InTakeClaw");

        instance = this;
    }

    public void update() {
        switch (state) {
            case CLOSE:
                IntakeClawServo.setPosition(closePos);
                break;
            case OPEN:
                IntakeClawServo.setPosition(openPos);
                break;

            case STOP:
                IntakeClawServo.setPosition(stopPos);
                break;
            case MIDDLE:
                IntakeClawServo.setPosition(middlePos);
                break;
        }

        if (state == State.CLOSE && IntakeClawServo.getPosition() == closePos){
            isTargetReached = true;
        } else if (state == State.OPEN && IntakeClawServo.getPosition() == openPos) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && IntakeClawServo.getPosition() == middlePos) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getClawTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Intake Claw Position = " + IntakeClawServo.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n Intake Claw State = " + state;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
