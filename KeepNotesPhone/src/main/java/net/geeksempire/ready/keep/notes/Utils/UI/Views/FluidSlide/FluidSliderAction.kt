/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/14/20 11:13 AM
 * Last modified 7/14/20 11:11 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package net.geeksempire.ready.keep.notes.Utils.UI.Views.FluidSlide

import android.util.Log

interface FluidSliderActionInterface {
    fun fluidSliderPosition(sliderPosition: Float) {}

    fun fluidSliderStarted() {}
    fun fluidSliderFinished() {}
}

class FluidSliderAction (private val fluidSliderActionInterface: FluidSliderActionInterface) {

    fun startListener(fluidSlider: FluidSlider) {

        fluidSlider.positionListener = { sliderPosition ->
            Log.d(this@FluidSliderAction.javaClass.simpleName, "$sliderPosition")

            fluidSlider.bubbleText = "${0 + (7  * sliderPosition).toInt()}"

            fluidSliderActionInterface.fluidSliderPosition(sliderPosition)
        }

        fluidSlider.beginTrackingListener = {

            fluidSliderActionInterface.fluidSliderStarted()

        }

        fluidSlider.endTrackingListener = {

            fluidSliderActionInterface.fluidSliderFinished()

        }

    }

}