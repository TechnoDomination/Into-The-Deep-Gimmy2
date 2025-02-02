package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    private final Servo IntakeWrist;
    public State state = State.IN;
    public boolean isTargetReached = false;
    public static IntakeWrist instance;

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
                IntakeWrist.setPosition(1);
                break;

            case OUT:
                IntakeWrist.setPosition(0);
                break;

            case STOP:
                IntakeWrist.setPosition(0);
                break;
            case MIDDLE:
                IntakeWrist.setPosition(0.5);
                break;
        }

        if (state == State.IN && IntakeWrist.getPosition() == 1){
            isTargetReached = true;
        } else if (state == State.OUT && IntakeWrist.getPosition() == 0) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && IntakeWrist.getPosition() == 0.5) {
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
