package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    private final Servo IntakeWrist;
    public State state = State.MIDDLE;
    public boolean isTargetReached = false;
    public static IntakeWrist instance;
    private double inPos = 1;
    private double outPos = 0;
    private double stopPos = 0;
    private double middlePos = 0.4;

    public enum State {
        IN, OUT, STOP, MIDDLE
    }

    public IntakeWrist(HardwareMap hardwareMap) {
        IntakeWrist = hardwareMap.get(Servo.class, "InTakeClawRotator");

        instance = this;
    }

    public void update() {
        switch (state) {
            case IN:
                IntakeWrist.setPosition(inPos);
                break;

            case OUT:
                IntakeWrist.setPosition(outPos);
                break;

            case STOP:
                IntakeWrist.setPosition(stopPos);
                break;
            case MIDDLE:
                IntakeWrist.setPosition(middlePos);
                break;
        }

        if (state == State.IN && IntakeWrist.getPosition() == inPos){
            isTargetReached = true;
        } else if (state == State.OUT && IntakeWrist.getPosition() == outPos) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && IntakeWrist.getPosition() == middlePos) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getIntakeWristTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Intake Wrist Position = " + IntakeWrist.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
