package edu.fullerton.kj.colormakerproject

import android.text.InputFilter
import android.text.Spanned
import android.util.Log


class CustomRangeInputFilter(private val minValue: Double, private val maxValue: Double) :
    InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dStart: Int,
        dEnd: Int
    ): String? {
        try {
            Log.d("Filter_TAG", "Inside filter, source is ${source.toString()}")
            // Remove the string out of destination that is to be replaced
                var newVal = dest.toString().substring(0, dStart) + dest.toString()
                    .substring(dEnd, dest.toString().length)
                newVal = newVal.substring(0, dStart) + source.toString() + newVal.substring(
                    dStart,
                    newVal.length
                )
                val input = newVal.toDouble()
                Log.d("Filter_TAG", "${input.toString()}")

                if (isInRange(minValue, maxValue, input)) {
                    return null
                }

        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}