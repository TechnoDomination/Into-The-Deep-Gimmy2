package org.firstinspires.ftc.teamcode.Actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.HorizSlides;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeWrist;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeClaw;
import org.firstinspires.ftc.teamcode.Subsystems.OutakeForearm;
import org.firstinspires.ftc.teamcode.Subsystems.VertSlides;

import java.util.Arrays;
import java.util.List;

public class CustomActions {
    public IntakeClaw intakeClaw = IntakeClaw.instance;
    public OutakeClaw outakeClaw = OutakeClaw.instance;
    public IntakeForearm intakeForearm = IntakeForearm.instance;
    public OutakeForearm outakeForearm = OutakeForearm.instance;
    public IntakeWrist intakeWrist = IntakeWrist.instance;
    public HorizSlides horizSlides = HorizSlides.instance;
    public VertSlides vertSlides = VertSlides.instance;
    public Drive drive = Drive.instance;
    public boolean sampleDropped = false;
    public static CustomActions instance;
    public ElapsedTime runTime = new ElapsedTime();
    boolean timerStarted;
    boolean reset = timerStarted;

    public CustomActions(HardwareMap hardwareMap){
         instance = this;
    }

    public void update(){
        horizSlides.update();
        vertSlides.update();
        intakeClaw.update();
        outakeClaw.update();
        intakeForearm.update();
        outakeForearm.update();
        intakeWrist.update();
    }

    public List<String> getTelemetry(){
        return Arrays.asList("Intake Claw = "+ intakeClaw.getClawTelemetry(),
                "Outake Claw = "+ outakeClaw.getClawTelemetry(),
                "Intake Wrist = "+intakeWrist.getIntakeWristTelemetry(),
                "Intake Arm = "+ intakeForearm.getArmTelemetry(),
                "Outake Arm = "+ outakeForearm.getArmTelemetry(),
                "Horizontal Slides = "+ horizSlides.getSlidesTelemetry(),
                "Vertical Slides = "+ vertSlides.getSlidesTelemetry());
    }

    public Action resestTimer = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            timerStarted = false;

            return false;
        }
    };



    public Action stopDrive = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            drive.stopDrive();

            return false;
        }
    };

    public Action intakeWristMiddle = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            intakeWrist.state = IntakeWrist.State.MIDDLE;

            return !intakeWrist.isTargetReached;
        }
    };

    public Action closeIntakeClaw = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            intakeClaw.state = IntakeClaw.State.CLOSE;

            return !intakeClaw.isTargetReached;
        }
    };

    public Action openIntakeClaw = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            intakeClaw.state = IntakeClaw.State.OPEN;

            return !intakeClaw.isTargetReached;
        }
    };

    public Action closeOutakeClaw = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            outakeClaw.state = OutakeClaw.State.CLOSE;

            return !outakeClaw.isTargetReached;
        }
    };

    public Action OutakeClawDown = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

           outakeForearm.state = OutakeForearm.State.IN;

            return !outakeClaw.isTargetReached;
        }
    };

    public Action openOutakeClaw = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            outakeClaw.state = OutakeClaw.State.OPEN;

            return !outakeClaw.isTargetReached;
        }
    };

    public Action intakeForeArmIn = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            intakeForearm.state = IntakeForearm.State.IN;

            return !intakeForearm.isTargetReached;
        }
    };

    public Action intakeForeArmSubEdge = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            intakeForearm.state = IntakeForearm.State.SUBMERSIBLEEDGE;

            return !intakeForearm.isTargetReached;
        }
    };

    public Action intakeForeArmSamplePick = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            intakeForearm.state = IntakeForearm.State.SAMPLEPICK;

            return !intakeForearm.isTargetReached;
        }
    };

    public Action intakeForeArmMiddle = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            intakeForearm.state = IntakeForearm.State.MIDDLE;

            return !intakeForearm.isTargetReached;
        }
    };

    public Action outakeForeArmBasketScoring = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            outakeForearm.state = OutakeForearm.State.BASKETSCORING;

            return !outakeForearm.isTargetReached;
        }
    };

    public Action outakeForeArmSpecimenScoring = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            outakeForearm.state = OutakeForearm.State.SPECIMENSCORING;

            return !outakeForearm.isTargetReached;
        }
    };

    public Action outakeForeArmTransfer = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            outakeForearm.state = OutakeForearm.State.IN;

            return !outakeForearm.isTargetReached;
        }
    };



    public Action prepareHighRung = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            outakeForearm.state = OutakeForearm.State.SPECIMENSCORING;
            vertSlides.state = VertSlides.State.SPECIMENALIGNDOWN;

            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !vertSlides.isTargetReached && !outakeForearm.isTargetReached;

        }
    };

    public Action prepareSpecimenPickup = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            outakeForearm.state = OutakeForearm.State.SPECIMENPICK;
            vertSlides.state = VertSlides.State.FULLDOWN;

            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !vertSlides.isTargetReached && !outakeForearm.isTargetReached;

        }
    };



    public Action vertSlidesSpecimenAlignUp = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            vertSlides.state = VertSlides.State.SPECIMENALIGNUP;


            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !vertSlides.isTargetReached;

        }
    };

    public Action prepareObsZoneParking = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            vertSlides.state = VertSlides.State.FULLDOWN;
            outakeForearm.state = OutakeForearm.State.IN;


            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !vertSlides.isTargetReached && !outakeForearm.isTargetReached;

        }
    };

    public Action prepareHighBasket = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            vertSlides.state = VertSlides.State.HIGHBASKETSAMPLEDROP;
            outakeForearm.state = OutakeForearm.State.SPECIMENSCORING;


            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !vertSlides.isTargetReached && !outakeForearm.isTargetReached;

        }
    };

    public Action prepareOutakeTransfer = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            outakeForearm.state = OutakeForearm.State.SPECIMENSCORING;
            vertSlides.state = VertSlides.State.FULLDOWN;



            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !vertSlides.isTargetReached && !outakeForearm.isTargetReached;

        }
    };

    public Action outakeArmSpecimenScore = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }
            outakeForearm.state = OutakeForearm.State.SPECIMENSCORING;


            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !vertSlides.isTargetReached && !outakeForearm.isTargetReached;

        }
    };

    public Action prepareSamplePick = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            horizSlides.state = HorizSlides.State.FULLOUT;
            intakeForearm.state = IntakeForearm.State.MIDDLE;
            intakeWrist.state = IntakeWrist.State.MIDDLE;


            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !horizSlides.isTargetReached && !intakeForearm.isTargetReached;

        }
    };

    public Action prepareIntakeTransfer = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            horizSlides.state = HorizSlides.State.FULLIN;
            intakeForearm.state = IntakeForearm.State.SUBMERSIBLEEDGE;
            intakeWrist.state = IntakeWrist.State.MIDDLE;


            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !horizSlides.isTargetReached && !intakeForearm.isTargetReached;

        }
    };



    public Action vertSlidesDown = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            if (!timerStarted) {
                runTime.reset();
                timerStarted = true;
            }

            vertSlides.state = VertSlides.State.HIGHBASKETSAMPLEDROP;


            if (runTime.time() > 3) {
                timerStarted = false;
                return false;
            }

            return !horizSlides.isTargetReached && !intakeForearm.isTargetReached;

        }
    };



}
