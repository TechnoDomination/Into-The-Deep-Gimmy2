package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Util.PIDFController;
import org.firstinspires.ftc.teamcode.Util.PIDFParams;

public class VertSlides {
    public DcMotorEx RightVertSlide;
    public DcMotorEx LeftVertSlide;
    public State state = State.IDLE;

    public PIDFController controller = new PIDFController(new PIDFParams(0.0075,0.0,0.0,0.0));

    public boolean isTargetReached = false;
    public static VertSlides instance;

    public static int highBasketTarget = 2250;
    public static int lowBasketTarget = 1000;
    public static int specimenAlignDownTarget = 450;
    public static int specimenAlignUpTarget = 1300;
    public static int specimenPullTarget = 450;
    public static int fullDownTarget = 0;
    public static int hangingTarget = 1450;

    //For auto only
    public static int autoSpecimenAlignTarget = 440;
    public static int autoSpecimenPullDownTarget = 50;//700;

    public enum State {
        AUTOSPECIMENALIGN(autoSpecimenAlignTarget),
        HIGHBASKETSAMPLEDROP(highBasketTarget),
        LOWBASKETSAMPLEDROP(lowBasketTarget),
        SPECIMENALIGNDOWN(specimenAlignDownTarget),
        SPECIMENPULL(specimenPullTarget),
        SPECIMENALIGNUP(specimenAlignUpTarget),
        FULLDOWN(fullDownTarget),

        AUTOSPECIMENALIGNTEST(autoSpecimenAlignTarget),
        AUTOSPECIMENPULLDOWNTEST(autoSpecimenPullDownTarget),

        HANGING(hangingTarget),

        IDLE(0);
        public final int target;
        State(int Target) {
            this.target = Target;
        }
    }

    public VertSlides(HardwareMap hardwareMap){

        RightVertSlide = hardwareMap.get(DcMotorEx.class, "RightVertSlideMotor");
        LeftVertSlide = hardwareMap.get(DcMotorEx.class, "LeftVertSlideMotor");
        LeftVertSlide.setDirection(DcMotor.Direction.REVERSE);
        RightVertSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftVertSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //SlideMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RightVertSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightVertSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightVertSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        instance = this;

    }
    double ticksPerRev = 384.5;

    public void update() {
        int encoder = RightVertSlide.getCurrentPosition();

        double motorPower = controller.calculate(state.target - encoder);
        //SlideMotor1.setPower(Range.clip(motorPower * .75,-0.75,0.75));
        //SlideMotor2.setPower(Range.clip(motorPower * .75,-0.75,0.75));

        RightVertSlide.setPower(motorPower);
        LeftVertSlide.setPower(motorPower);


        /*if (state == State.HIGHBASKETSAMPLEDROP && (Math.abs(highBasketTarget-encoder) < 50)) {
            isTargetReached = true;
        } else if (state == State.SPECIMENALIGNUP && (Math.abs(specimenAlignUpTarget-encoder) < 50)) {
            isTargetReached = true;
        } else if (state == State.SPECIMENALIGNDOWN && (Math.abs(specimenAlignDownTarget-encoder) < 30)) {
            isTargetReached = true;
        } else if (state == State.SPECIMENPULL && (Math.abs(specimenPullTarget-encoder) < 50)) {
            isTargetReached = true;
        } else if (state == State.LOWBASKETSAMPLEDROP && (Math.abs(lowBasketTarget-encoder) < 50)) {
            isTargetReached = true;
        } else if (state == State.FULLDOWN && (Math.abs(fullDownTarget-encoder) < 50)) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }*/

        if (Math.abs(state.target-encoder) < 50) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }


        if ((state == VertSlides.State.FULLDOWN) && ((RightVertSlide.getCurrent(CurrentUnit.AMPS) > 5 || LeftVertSlide.getCurrent(CurrentUnit.AMPS) > 5))){
            RightVertSlide.setPower(0);
            LeftVertSlide.setPower(0);

            RightVertSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightVertSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightVertSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }


    }


    public String getSlidesTelemetry(){

        String telemetry = "";
        telemetry = telemetry + "\n Current Position = " + RightVertSlide.getCurrentPosition();
        telemetry = telemetry + "\n State current = " + state;
        telemetry = telemetry + "\n State Target = " + state.target;
        telemetry = telemetry + "\n Is Target Reached? --> " + isTargetReached;
        telemetry = telemetry + "\n Vert Slides current AMPS --> " + RightVertSlide.getCurrent(CurrentUnit.AMPS);

        telemetry = telemetry + "\n ";

        return telemetry;
    }

    public int getPosition() {
        return RightVertSlide.getCurrentPosition();
    }

}
