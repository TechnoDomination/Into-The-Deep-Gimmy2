package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeForearm {
    private final Servo IntakeForearm;
    public State state = State.OUT;
    public boolean isTargetReached = false;
    public static IntakeForearm instance;

    public enum State {
        IN, OUT, STOP, MIDDLE
    }

    public IntakeForearm(HardwareMap hardwareMap) {
        IntakeForearm = hardwareMap.get(Servo.class, "InTakeForearm");

        instance = this;
    }

    public void update() {
        switch (state) {
            case IN:
                IntakeForearm.setPosition(1);
                break;

            case OUT:
                IntakeForearm.setPosition(0);
                break;

            case STOP:
                IntakeForearm.setPosition(0);
                break;
            case MIDDLE:
                IntakeForearm.setPosition(0.5);
                break;
        }

        if (state == State.IN && IntakeForearm.getPosition() == 1){
            isTargetReached = true;
        } else if (state == State.OUT && IntakeForearm.getPosition() == 0) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && IntakeForearm.getPosition() == 0.5) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getClawTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Intake Forearm Position = " + IntakeForearm.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
