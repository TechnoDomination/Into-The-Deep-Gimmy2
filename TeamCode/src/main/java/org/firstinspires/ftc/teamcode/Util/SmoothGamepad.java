package org.firstinspires.ftc.teamcode.Util;

public class SmoothGamepad {

    public double smoothGamepad(double gamepadInput){

        return Math.pow(gamepadInput, 2.2);
    }



}
