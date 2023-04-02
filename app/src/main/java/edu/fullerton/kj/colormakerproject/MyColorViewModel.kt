package edu.fullerton.kj.colormakerproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import kotlin.math.roundToInt

private const val TAG = "ViewModel"

class MyColorViewModel: ViewModel() {

    private var redSwitchState: Boolean = false
    private var greenSwitchState: Boolean = false
    private var blueSwitchState: Boolean = false
    private var redSeekBarValue: Int = 0
    private var greenSeekBarValue: Int = 0
    private var blueSeekBarValue: Int = 0
    private var redEditTextValue: Double = 0.0
    private var greenEditTextValue: Double = 0.0
    private var blueEditTextValue: Double = 0.0
    private var redColorValue: Int = 0
    private var greenColorValue: Int = 0
    private var blueColorValue: Int = 0


    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "View Model instance about to be destroyed")
    }

    private val prefs = PreferencesRepository.getRepository()

    private fun saveRedSwitchState() {
        viewModelScope.launch {
            prefs.saveRedSwitchState(redSwitchState)
            Log.d(TAG, "Done saving switch State $redSwitchState")
        }
    }

    private fun saveGreenSwitchState() {
        viewModelScope.launch {
            prefs.saveGreenSwitchState(greenSwitchState)
            Log.d(TAG, "Done saving switch State $greenSwitchState")
        }
    }

    private fun saveBlueSwitchState() {
        viewModelScope.launch {
            prefs.saveBlueSwitchState(blueSwitchState)
            Log.d(TAG, "Done saving switch State $blueSwitchState")
        }
    }

    private fun saveRedSeekBarValue() {
        viewModelScope.launch {
            prefs.saveRedSeekBarValue(redSeekBarValue)
            Log.d(TAG, "Done saving seekbar State $redSeekBarValue")
        }
    }

    private fun saveGreenSeekBarValue() {
        viewModelScope.launch {
            prefs.saveGreenSeekBarValue(greenSeekBarValue)
            Log.d(TAG, "Done saving seekbar State $greenSeekBarValue")
        }
    }

    private fun saveBlueSeekBarValue() {
        viewModelScope.launch {
            prefs.saveBlueSeekBarValue(blueSeekBarValue)
            Log.d(TAG, "Done saving seekbar State $blueSeekBarValue")
        }
    }

    private fun saveRedEditTextValue() {
        viewModelScope.launch {
            prefs.saveRedEditTextValue(redEditTextValue)
        }
    }

    private fun saveGreenEditTextValue() {
        viewModelScope.launch {
            prefs.saveGreenEditTextValue(greenEditTextValue)
        }
    }

    private fun saveBlueEditTextValue() {
        viewModelScope.launch {
            prefs.saveBlueEditTextValue(blueEditTextValue)
        }
    }

    private fun saveRedColorValue() {
        viewModelScope.launch {
            prefs.saveRedColorValue(redColorValue)
        }
    }

    private fun saveGreenColorValue() {
        viewModelScope.launch {
            prefs.saveGreenColorValue(greenColorValue)
        }
    }

    private fun saveBlueColorValue() {
        viewModelScope.launch {
            prefs.saveBlueColorValue(blueColorValue)
        }
    }
    fun loadState(act: MainActivity) {
        viewModelScope.launch {
            prefs.redSwitchState.collectLatest {
                act.redSwitch.isChecked = it
                Log.v(TAG, "Done collecting state")
            }
        }
        viewModelScope.launch {
            prefs.greenSwitchState.collectLatest {
                act.greenSwitch.isChecked = it
                Log.v(TAG, "Done collecting state")
            }
        }
        viewModelScope.launch {
            prefs.blueSwitchState.collectLatest {
                act.blueSwitch.isChecked = it
                Log.v(TAG, "Done collecting state")
            }
        }
        sleep(1000)
    }

    fun loadRedSeekBarValue() {
        GlobalScope.launch {
            prefs.redSeekBarValue.collectLatest {
                redSeekBarValue = it
            }
        }
        sleep(1000)
    }

    fun loadGreenSeekBarValue() {
        GlobalScope.launch {
            prefs.greenSeekBarValue.collectLatest {
                greenSeekBarValue = it
            }
        }
    }

    fun loadBlueSeekBarValue() {
        GlobalScope.launch {
            prefs.blueSeekBarValue.collectLatest {
                blueSeekBarValue = it
            }
        }
    }

    fun loadRedEditTextValue() {
        GlobalScope.launch {
            prefs.redEditTextValue.collectLatest {
                redEditTextValue = it
            }
        }
        sleep(1000)
    }

    fun loadGreenEditTextValue() {
        GlobalScope.launch {
            prefs.greenEditTextValue.collectLatest {
                greenEditTextValue = it
            }
        }
    }

    fun loadBlueEditTextValue() {
        GlobalScope.launch {
            prefs.blueEditTextValue.collectLatest {
                blueEditTextValue = it
            }
        }
    }

    fun loadRedColorValue() {
        GlobalScope.launch {
            prefs.redColorValue.collectLatest {
                redColorValue = it
            }
        }
    }

    fun loadGreenColorValue() {
        GlobalScope.launch {
            prefs.greenColorValue.collectLatest {
                greenColorValue = it
            }
        }
    }

    fun loadBlueColorValue() {
        GlobalScope.launch {
            prefs.blueColorValue.collectLatest {
                blueColorValue = it
            }
        }
    }

    fun setRedSwitchState(state: Boolean) {
        this.redSwitchState = state
        saveRedSwitchState()
    }

    fun setGreenSwitchState(state: Boolean) {
        this.greenSwitchState = state
        saveGreenSwitchState()
    }
    fun setBlueSwitchState(state: Boolean) {
        this.blueSwitchState = state
        saveBlueSwitchState()
    }

    fun setRedSeekBarState(value: Int) {
        this.redSeekBarValue = value
        saveRedSeekBarValue()
    }
    fun getRedSeekBarValue(): Int {
        return this.redSeekBarValue
    }
    fun setGreenSeekBarState(value: Int) {
        this.greenSeekBarValue = value
        saveGreenSeekBarValue()
    }
    fun getGreenSeekBarValue(): Int {
        return this.greenSeekBarValue
    }
    fun setBlueSeekBarState(value: Int) {
        this.blueSeekBarValue = value
        saveBlueSeekBarValue()
    }
    fun getBlueSeekBarValue(): Int {
        return this.blueSeekBarValue
    }

    fun setRedEditTextValue(value: Double) {
        this.redEditTextValue = value
        saveRedEditTextValue()
    }

    fun getRedEditTextValue(): Double {
        return this.redEditTextValue
    }

    fun setGreenEditTextValue(value: Double) {
        this.greenEditTextValue = value
        saveGreenEditTextValue()
    }

    fun getGreenEditTextValue(): Double {
        return this.greenEditTextValue
    }

    fun setBlueEditTextValue(value: Double) {
        this.blueEditTextValue = value
        saveBlueEditTextValue()
    }

    fun getBlueEditTextValue(): Double {
        return this.blueEditTextValue
    }

    fun setRedColorValue(value: Int) {
        this.redColorValue = value
        saveRedColorValue()
    }

    fun getRedColorValue(): Int {
        return this.redColorValue
    }

    fun setGreenColorValue(value: Int) {
        this.greenColorValue = value
        saveGreenColorValue()
    }

    fun getGreenColorValue(): Int {
        return this.greenColorValue
    }

    fun setBlueColorValue(value: Int) {
        this.blueColorValue = value
        saveBlueColorValue()
    }

    fun getBlueColorValue(): Int {
        return this.blueColorValue
    }
    fun resetBackgroundImage(): Int {
        return R.drawable.color_background
    }

     fun convertEditTextColorValue(value: Any) : Int {
        return value.toString().toDouble().times(255).roundToInt()
    }
}