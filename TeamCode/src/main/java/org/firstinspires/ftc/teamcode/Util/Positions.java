package org.firstinspires.ftc.teamcode.Util;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.Actions.P2P;


public enum Positions {
    //Samples on ground
    //LeftSample1(new Vector2d(-42, -42), 0.0),
    //LeftSample2(new Vector2d(-52, -42), 0.0),
    //LeftSample3(new Vector2d(-46, 0.0), -PI/2), //original position
    LeftSample3pt2(new Vector2d(-50, -34), -PI*0.25), //a little forward
    LeftSample3(new Vector2d(-44, -44), -PI * 0.25),
    TestNeg(new Vector2d(-40, -40), 0.0),

    //Basket related movement
    //Basket(new Vector2d(-43, -48),-PI*0.72),
    //Basket2(new Vector2d(-42.5, -47.5),-PI*0.72),
    //GoFrontSample(new Vector2d(-35, -53), 0.0),

    //Specmien related movement

    GoBackSpecimen(new Vector2d(8, -50), 0.0),

    SpecimenObZone(new Vector2d(20, -52), PI * 0.5),
    SpecimenObZoneTiny(new Vector2d(28, -52), PI * 0.5),

    ObserservationZoneParkingSpecimen(new Vector2d(48, -63), 0.0),
    ObserservationZoneParkingSample(new Vector2d(50, -63), 0.0),


    //Gimmy 2.0 Specimen related movement
    HighRung1(new Vector2d(-4, -37), 0.0),
    HighRungTiny1(new Vector2d(-4, -28.5), 0.0),
    //HighRungTiny1_2(new Vector2d(-2, -27.3), 0.0),
    HighRung2(new Vector2d(-2, -37), 0.0),
    HighRungTiny2(new Vector2d(-2, -28.5), 0.0),
    HighRung3(new Vector2d(0, -37), 0.0),
    HighRungTiny3(new Vector2d(0, -28.5), 0.0),
    ObsZonePickupSpecimen(new Vector2d(48, -40), 0.0),
    ObsZonePickupSpecimenTiny(new Vector2d(48, -61), 0.0),
    Parking(new Vector2d(48, -60), 0.0),
    PrepareForPushStep1(new Vector2d(35, -45), 0.0),
    PrepareForPushStep2(new Vector2d(35, -10), 0.0),
    PrepareForPushStep3(new Vector2d(48, -10), 0.0),
    PrepareForPushStep4(new Vector2d(45, -10), 0.0),
    PrepareForPushStep5(new Vector2d(60, -10), 0.0),
    PushSample1(new Vector2d(48, -55), 0.0),
    PushSample2(new Vector2d(62, -55), -PI*0.1),

    //Gimmy 2.0 Sample related movement
    Basket(new Vector2d(-51, -56),PI*0.25),
    GoFrontSample(new Vector2d(-35, -50), 0.0),
    LeftSample1(new Vector2d(-42, -51), 0.0),
    LeftSample2(new Vector2d(-55, -49), 0.0),
    LeftSample2Alt(new Vector2d(-55, -35), 0.0),


    GoFrontTinySpecimenTest(new Vector2d(6, -35), 0.0),
    HighRung2Test(new Vector2d(-3, -47), 0.0),
    GoFrontTiny2SpecimenTest(new Vector2d(-3, -35), 0.0),
    HighRung3Test(new Vector2d(4, -47), 0.0),
    GoFrontTiny3SpecimenTest(new Vector2d(4, -35), 0.0),
    HighRung4Test(new Vector2d(2, -47), 0.0),
    GoFrontTiny4SpecimenTest(new Vector2d(2, -35), 0.0),
    FirstColorSampleTest(new Vector2d(32, -36),PI * 0.25),
    SecondColorSampleTest(new Vector2d(42, -37.5), PI * 0.25),
    DropColorSampleTest(new Vector2d(32, -47), PI * 0.75),

    Test(new Vector2d(0.0,23.0),0.0),
    Test2(new Vector2d(0.0,24),0.0),
    TestStart(new Vector2d(0.0,0.0),0.0),
    TestTurning(new Vector2d(24,24),PI * 0.5);

    Positions(Vector2d vector, Double rotation) {
        runToExact = new P2P(vector, rotation);
    }

    public final P2P runToExact;
}

