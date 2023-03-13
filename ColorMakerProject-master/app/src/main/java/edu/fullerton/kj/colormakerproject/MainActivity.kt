package edu.fullerton.kj.colormakerproject

import android.graphics.Color.rgb
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var resetButton: Button
    private lateinit var colorView: View
    private lateinit var redSwitch: SwitchCompat
    private lateinit var greenSwitch: SwitchCompat
    private lateinit var blueSwitch: SwitchCompat
    private lateinit var redSeekBar: SeekBar
    private lateinit var blueSeekBar: SeekBar
    private lateinit var greenSeekBar: SeekBar
    private lateinit var redEditText: EditText
    private lateinit var greenEditText: EditText
    private lateinit var blueEditText: EditText
    private lateinit var headerText: TextView
    var rescaledValue = 0.00392156862
    var newValue: Double = 0.00
    var startNum = 0
    var endNum = 0
    var colorRed = 0
    var colorBlue = 0
    var colorGreen = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectViewPointers()
        resetButtonCallback()
        redSwitchCallback()
        blueSwitchCallback()
        greenSwitchCallback()
        redSeekBarCallback()
        greenSeekBarCallback()
        blueSeekBarCallback()
        redEditTextCallback()
        greenEditTextCallback()
        blueEditTextCallback()
        redSeekBar.isEnabled = false
        greenSeekBar.isEnabled = false
        blueSeekBar.isEnabled = false
        redEditText.isEnabled = false
        blueEditText.isEnabled = false
        greenEditText.isEnabled = false
    }

    private fun connectViewPointers() {
        resetButton = this.findViewById(R.id.reset_button)
        headerText = this.findViewById(R.id.header_text)
        colorView = this.findViewById(R.id.color_view)
        redSwitch = this.findViewById(R.id.red_switch)
        redSeekBar = this.findViewById(R.id.red_seekBar)
        redEditText = this.findViewById(R.id.red_editTextNumberDecimal)
        greenSwitch = this.findViewById(R.id.green_switch)
        greenSeekBar = this.findViewById(R.id.green_seekBar)
        greenEditText = this.findViewById(R.id.green_editTextNumberDecimal)
        blueSwitch = this.findViewById(R.id.blue_switch)
        blueSeekBar = this.findViewById(R.id.blue_seekBar)
        blueEditText = this.findViewById(R.id.blue_editTextNumberDecimal)
    }

    private fun resetButtonCallback() {
        resetButton.setOnClickListener {
            if(redSwitch.isChecked)
                redSwitch.toggle()
            if(greenSwitch.isChecked)
                greenSwitch.toggle()
            if(blueSwitch.isChecked)
                blueSwitch.toggle()
            redSeekBar.progress = 0
            greenSeekBar.progress = 0
            blueSeekBar.progress = 0
            redEditText.setText("")
            greenEditText.setText("")
            blueEditText.setText("")
            colorView.background = resources.getDrawable(R.drawable.color_background)
        }
    }

    private fun redSwitchCallback() {
        redSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                redSeekBar.isEnabled = true
                redEditText.isEnabled = true
                if(redSeekBar.progress == 0)
                    redEditText.setText("0.0")
                var getRedColorValue = redEditText.text
                if(getRedColorValue!=null && getRedColorValue.isNotEmpty()){
                    colorRed = (getRedColorValue.toString()?.toDouble()?.times(255))?.roundToInt()?: 0
                }
                colorView.setBackgroundColor(rgb(colorRed, colorGreen, colorBlue))
                headerText.setTextColor(rgb(this.colorRed,this.colorGreen,this.colorBlue))
            } else {
                redSeekBar.isEnabled = false
                redEditText.isEnabled = false
                this.colorRed = 0
                colorView.setBackgroundColor(rgb(colorRed,this.colorGreen,this.colorBlue))
                headerText.setTextColor(rgb(colorRed,this.colorGreen,this.colorBlue))
            }
        }
    }

    private fun greenSwitchCallback() {
        greenSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                greenSeekBar.isEnabled = true
                greenEditText.isEnabled = true
                if(greenSeekBar.progress == 0)
                    greenEditText.setText("0.0")
                var getGreenColorValue = greenEditText.text
                if(getGreenColorValue!=null && getGreenColorValue.isNotEmpty()){
                    colorGreen = (getGreenColorValue.toString()?.toDouble()?.times(255))?.roundToInt()?: 0
                }
                colorView.setBackgroundColor(rgb(this.colorRed, this.colorGreen, this.colorBlue))
                headerText.setTextColor(rgb(this.colorRed,this.colorGreen,this.colorBlue))
            } else {
                greenSeekBar.isEnabled = false
                greenEditText.isEnabled = false
                this.colorGreen = 0
                colorView.setBackgroundColor(rgb(this.colorRed,colorGreen,this.colorBlue))
                headerText.setTextColor(rgb(this.colorRed,colorGreen,this.colorBlue))
            }
        }
    }

    private fun blueSwitchCallback() {
        blueSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                blueSeekBar.isEnabled = true
                blueEditText.isEnabled = true
                if(blueSeekBar.progress == 0)
                    blueEditText.setText("0.0")
                var getBlueColorValue = blueEditText.text
                if(getBlueColorValue!=null && getBlueColorValue.isNotEmpty()){
                    colorBlue = (getBlueColorValue.toString()?.toDouble()?.times(255))?.roundToInt()?: 0
                }
                colorView.setBackgroundColor(rgb(this.colorRed,this.colorGreen,this.colorBlue))
                headerText.setTextColor(rgb(this.colorRed,this.colorGreen,this.colorBlue))
            } else {
                blueSeekBar.isEnabled = false
                blueEditText.isEnabled = false
                this.colorBlue = 0
                colorView.setBackgroundColor(rgb(this.colorRed,this.colorGreen,colorBlue))
                headerText.setTextColor(rgb(this.colorRed,this.colorGreen,colorBlue))
            }
        }
    }

    private fun redSeekBarCallback() {
        redSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                newValue = p1 * rescaledValue
                val roundOffValue = (newValue * 1000.0).roundToInt() / 1000.0
                redEditText.setText(roundOffValue.toString())
                colorRed = p1
                colorView.setBackgroundColor(rgb(colorRed, colorGreen, colorBlue))
                headerText.setTextColor(rgb(colorRed, colorGreen, colorBlue))
                print(p2)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                startNum = redSeekBar.progress
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                endNum = redSeekBar.progress
            }
        })
    }

    private fun greenSeekBarCallback() {
        greenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                newValue = p1*rescaledValue
                val roundOffValue = (newValue * 100.0).roundToInt() / 100.0
                greenEditText.setText(roundOffValue.toString())
                colorGreen = p1
                colorView.setBackgroundColor(rgb(colorRed,colorGreen,colorBlue))
                headerText.setTextColor(rgb(colorRed,colorGreen,colorBlue))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                startNum = greenSeekBar.progress
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                endNum = greenSeekBar.progress
            }
        })
    }

    private fun blueSeekBarCallback() {
        blueSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                newValue = p1*rescaledValue
                val roundOffValue = (newValue * 100.0).roundToInt() / 100.0
                blueEditText.setText(roundOffValue.toString())
                colorBlue = p1
                colorView.setBackgroundColor(rgb(colorRed,colorGreen,colorBlue))
                headerText.setTextColor(rgb(colorRed,colorGreen,colorBlue))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                startNum = blueSeekBar.progress
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                endNum = blueSeekBar.progress
            }
        })
    }

    private fun redEditTextCallback() {
        redEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                redEditText.filters = arrayOf(InputFilter.LengthFilter(5), CustomRangeInputFilter(0.0, 1.0))
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s!=null && s.isNotEmpty()){
                    redSeekBar.progress = (s.toString()?.toDouble()?.times(255))?.roundToInt()?: 0
                }
                colorView.setBackgroundColor(rgb(colorRed, colorGreen, colorBlue))
                headerText.setTextColor(rgb(colorRed,colorGreen,colorBlue))
            }
        })
    }

    private fun blueEditTextCallback() {
        blueEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                blueEditText.filters = arrayOf(InputFilter.LengthFilter(5), CustomRangeInputFilter(0.0, 1.0))

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s!=null && s.isNotEmpty()){
                    blueSeekBar.progress = (s.toString()?.toDouble()?.times(255))?.roundToInt()?: 0
                }
                colorView.setBackgroundColor(rgb(colorRed, colorGreen, colorBlue))
                headerText.setTextColor(rgb(colorRed,colorGreen,colorBlue))
            }
        })
    }

    private fun greenEditTextCallback() {
        greenEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                greenEditText.filters = arrayOf(InputFilter.LengthFilter(5), CustomRangeInputFilter(0.0, 1.0))
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s!=null && s.isNotEmpty()){
                    greenSeekBar.progress = (s.toString()?.toDouble()?.times(255))?.roundToInt()?: 0
                }
                colorView.setBackgroundColor(rgb(colorRed, colorGreen, colorBlue))
                headerText.setTextColor(rgb(colorRed,colorGreen,colorBlue))
            }
        })
    }
}

//class DecimalDigitsInputFilter:InputFilter {
//    override fun filter(
//        p0: CharSequence?,
//        p1: Int,
//        p2: Int,
//        p3: Spanned?,
//        p4: Int,
//        p5: Int
//    ): CharSequence {
//        Log.d("Pattern", p0?.toString() ?: "")
//        var pattern = Regex("(0\\.\\d+)|(1\\.0)")
//        if (p0 != null) {
//            if(pattern.matches(p0))
//                return p0
//        }
//        return ""
//    }
