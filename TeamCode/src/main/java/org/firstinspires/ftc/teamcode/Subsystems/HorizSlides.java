package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Util.PIDFController;
import org.firstinspires.ftc.teamcode.Util.PIDFParams;

public class HorizSlides {
    public DcMotorEx horizSlide;
    public State state = State.IDLE;

    public PIDFController controller = new PIDFController(new PIDFParams(0.0075,0.0,0.0,0.0));

    public boolean isTargetReached = false;
    public static HorizSlides instance;

    public static int fullOut = 1500;
    public static int fullIn = 0;
    public static int transfer = 450;

    public enum State {
        FULLOUT(fullOut),
        FULLIN(fullIn),
        TRANSFER(transfer),
        IDLE(0);
        public final int target;
        State(int Target) {
            this.target = Target;
        }
    }

    public HorizSlides(HardwareMap hardwareMap){

        horizSlide = hardwareMap.get(DcMotorEx.class, "HorizSlide");
        horizSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        horizSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        horizSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        instance = this;

    }
    double ticksPerRev = 384.5;

    public void update() {
        int encoder = horizSlide.getCurrentPosition();

        double motorPower = controller.calculate(state.target - encoder);

        horizSlide.setPower(motorPower);


        if (Math.abs(state.target-encoder) < 50) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }

    }

    public String getSlidesTelemetry(){

        String telemetry = "";
        telemetry = telemetry + "\n Current Position = " + horizSlide.getCurrentPosition();
        telemetry = telemetry + "\n State current = " + state;
        telemetry = telemetry + "\n State Target = " + state.target;
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n Horiz Slides current AMPS --> " + horizSlide.getCurrent(CurrentUnit.AMPS);

        telemetry = telemetry + "\n ";

        return telemetry;
    }

    public int getPosition() {
        return horizSlide.getCurrentPosition();
    }


}
