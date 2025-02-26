package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OutakeForearm {
    private final Servo OutakeForearm;
    public State state = State.IN;
    public boolean isTargetReached = false;
    public static OutakeForearm instance;
    private double inPos = 1;
    private double outPos = 0;
    private double stopPos = 0;
    private double middlePos = 0.5 ;
    private double basketScoringPos = 0.3;
    private double specimenScoringPos = 0.65;
    private double specimenPickPos = 0;
    private double armTest = 0.3;


    public enum State {
        IN, OUT, STOP, MIDDLE, BASKETSCORING, SPECIMENSCORING,SPECIMENPICK, ARMTEST
    }

    public OutakeForearm(HardwareMap hardwareMap) {
        OutakeForearm = hardwareMap.get(Servo.class, "OutTakeForearm");

        instance = this;
    }

    public void update() {
        switch (state) {
            case IN:
                OutakeForearm.setPosition(inPos);
                break;

            case OUT:
                OutakeForearm.setPosition(outPos);
                break;

            case STOP:
                OutakeForearm.setPosition(stopPos);
                break;

            case BASKETSCORING:
                OutakeForearm.setPosition(basketScoringPos);
                break;

            case SPECIMENSCORING:
                OutakeForearm.setPosition(specimenScoringPos);
                break;

            case SPECIMENPICK:
                OutakeForearm.setPosition(specimenPickPos);
                break;

            case ARMTEST:
                OutakeForearm.setPosition(armTest);
                break;
        }

        if (state == State.IN && OutakeForearm.getPosition() == inPos){
            isTargetReached = true;
        } else if (state == State.OUT && OutakeForearm.getPosition() == outPos) {
            isTargetReached = true;
        } else if (state == State.MIDDLE && OutakeForearm.getPosition() == middlePos) {
            isTargetReached = true;
        } else if (state == State.BASKETSCORING && OutakeForearm.getPosition() == basketScoringPos) {
            isTargetReached = true;
        } else if (state == State.SPECIMENSCORING && OutakeForearm.getPosition() == specimenScoringPos) {
            isTargetReached = true;
        } else if (state == State.SPECIMENPICK && OutakeForearm.getPosition() == specimenPickPos) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }


    public String getArmTelemetry(){
        String telemetry = "";
        telemetry = telemetry + "\n Outtake Forearm Position = " + OutakeForearm.getPosition();
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
