package org.firstinspires.ftc.teamcode.Util

class DriveforKotlin {

    private fun driveManual(gamepadInput: ArrayList<Float>) {
        val input = gamepadInput.map { smoothGamepadInput(it.toDouble()) }
        Log.d("f", input.toString())
        val (axial, lateral, turn) = input

        val h = -Localizer.pose.heading
        val rotX = -axial * cos(h) - lateral * sin(h)
        val rotY = -axial * sin(h) + lateral * cos(h)

        //todo add rotational pid

        leftFront.power = (rotY - rotX + turn)
        leftBack.power = (rotY + rotX + turn)
        rightFront.power = (rotY + rotX - turn)
        rightBack.power = (rotY - rotX - turn)
    }


}