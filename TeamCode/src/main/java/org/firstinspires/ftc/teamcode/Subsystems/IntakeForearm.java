package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeForearm {
    private final Servo IntakeForearm;
    public State state = State.OUT;
    public boolean isTargetReached = false;
    public static IntakeForearm instance;
    private double inPos = 0.75
            ;
    private double outPos = 0;
    private double middlePos = 0.4;
    private double samplePickPos = 0.15;
    private double submersibleEdgePos = 0.6;

    public enum State {
        IN, OUT, STOP, MIDDLE, SAMPLEPICK, SUBMERSIBLEEDGE
    }

    public IntakeForearm(HardwareMap hardwareMap) {
        IntakeForearm = hardwareMap.get(Servo.class, "InTakeForearm");

        instance = this;
    }

    public void update() {
        switch (state) {
            case IN:
                IntakeForearm.setPosition(inPos);
                break;

            case MIDDLE:
                IntakeForearm.setPosition(middlePos);
                break;

            case SAMPLEPICK:
                IntakeForearm.setPosition(samplePickPos);
                break;

            case SUBMERSIBLEEDGE:
                IntakeForearm.setPosition(submersibleEdgePos);
                break;

        }

        if (state == State.IN && IntakeForearm.getPosition() == inPos){
            isTargetReached = true;
        } else if (state == State.OUT && IntakeForearm.getPosition() == outPos) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && IntakeForearm.getPosition() == middlePos) {
            isTargetReached = true;
        } else if (state == State.SAMPLEPICK && IntakeForearm.getPosition() == samplePickPos) {
            isTargetReached = true;
        } else if (state == State.SUBMERSIBLEEDGE && IntakeForearm.getPosition() == submersibleEdgePos) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getArmTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Intake Forearm Position = " + IntakeForearm.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
