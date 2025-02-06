package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OutakeForearm {
    private final Servo OutakeForearm;
    public State state = State.IN;
    public boolean isTargetReached = false;
    public static OutakeForearm instance;

    public enum State {
        IN, OUT, STOP, MIDDLE,SCORING,TRANSFER,SPECIMENPICK
    }

    public OutakeForearm(HardwareMap hardwareMap) {
        OutakeForearm = hardwareMap.get(Servo.class, "OutTakeForearm");

        instance = this;
    }

    public void update() {
        //switch case
        switch (state) {
            case IN:
                OutakeForearm.setPosition(0.175);
                break;

            case OUT:
                OutakeForearm.setPosition(1);
                break;

            case STOP:
                OutakeForearm.setPosition(0);
                break;

            case MIDDLE:
                OutakeForearm.setPosition(0.5);
                break;

            case SCORING:
                OutakeForearm.setPosition(0.75);
                break;

            case TRANSFER:
                OutakeForearm.setPosition(0.2);
                break;

            case SPECIMENPICK:
                OutakeForearm.setPosition(0.85);
                break;
        }

        if (state == State.IN && OutakeForearm.getPosition() == 1){
            isTargetReached = true;
        } else if (state == State.OUT && OutakeForearm.getPosition() == 0) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && OutakeForearm.getPosition() == 0.5) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getClawTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Outtake Forearm Position = " + OutakeForearm.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
