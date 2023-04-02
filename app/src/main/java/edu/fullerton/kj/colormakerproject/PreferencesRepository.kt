package edu.fullerton.kj.colormakerproject

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class PreferencesRepository private constructor(private val dataStore: DataStore<Preferences>) {

    private val REDSWITCH = booleanPreferencesKey("redSwitch")
    private val GREENSWITCH = booleanPreferencesKey("greenSwitch")
    private val BLUESWITCH = booleanPreferencesKey("blueSwitch")
    private val REDSEEKBAR = intPreferencesKey("redSeekBar")
    private val GREENSEEKBAR = intPreferencesKey("greenSeekBar")
    private val BLUESEEKBAR = intPreferencesKey("blueSeekBar")
    private val REDTEXT = doublePreferencesKey("redEditText")
    private val GREENTEXT = doublePreferencesKey("greenEditText")
    private val BLUETEXT = doublePreferencesKey("blueEditText")
    private val REDCOLOR = intPreferencesKey("redColor")
    private val GREENCOLOR = intPreferencesKey("greenColor")
    private val BLUECOLOR = intPreferencesKey("blueColor")

    val redEditTextValue: Flow<Double> = this.dataStore.data.map { prefs ->
        prefs[REDTEXT] ?: 0.0
    }.distinctUntilChanged()

    val greenEditTextValue: Flow<Double> = this.dataStore.data.map { prefs ->
        prefs[GREENTEXT] ?: 0.0
    }.distinctUntilChanged()

    val blueEditTextValue: Flow<Double> = this.dataStore.data.map { prefs ->
        prefs[BLUETEXT] ?: 0.0
    }.distinctUntilChanged()

    val redColorValue: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[REDCOLOR] ?: 0
    }.distinctUntilChanged()

    val greenColorValue: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[GREENCOLOR] ?: 0
    }.distinctUntilChanged()

    val blueColorValue: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[BLUECOLOR] ?: 0
    }.distinctUntilChanged()

    val redSwitchState: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[REDSWITCH] ?: false
    }.distinctUntilChanged()

    val greenSwitchState: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[GREENSWITCH] ?: false
    }.distinctUntilChanged()

    val blueSwitchState: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[BLUESWITCH] ?: false
    }.distinctUntilChanged()

    val redSeekBarValue: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[REDSEEKBAR] ?: 0
    }.distinctUntilChanged()

    val greenSeekBarValue: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[GREENSEEKBAR] ?: 0
    }.distinctUntilChanged()

    val blueSeekBarValue: Flow<Int> = this.dataStore.data.map { prefs ->
        prefs[BLUESEEKBAR] ?: 0
    }.distinctUntilChanged()

    private suspend fun saveColorValue(key: Preferences.Key<Int>, value: Int) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun saveEditTextValue(key: Preferences.Key<Double>, value: Double) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun saveSeekBarState(key: Preferences.Key<Int>, value: Int) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun saveSwitchState(key: Preferences.Key<Boolean>, value: Boolean) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }

    suspend fun saveRedEditTextValue(value: Double) {
        saveEditTextValue(REDTEXT, value)
    }

    suspend fun saveGreenEditTextValue(value: Double) {
        saveEditTextValue(GREENTEXT, value)
    }

    suspend fun saveBlueEditTextValue(value: Double) {
        saveEditTextValue(BLUETEXT, value)
    }

    suspend fun saveRedColorValue(value: Int) {
        saveColorValue(REDCOLOR, value)
    }

    suspend fun saveGreenColorValue(value: Int) {
        saveColorValue(GREENCOLOR, value)
    }

    suspend fun saveBlueColorValue(value: Int) {
        saveColorValue(BLUECOLOR, value)
    }

    suspend fun saveRedSeekBarValue(value: Int) {
        saveSeekBarState(REDSEEKBAR, value)
    }
    suspend fun saveGreenSeekBarValue(value: Int) {
        saveSeekBarState(GREENSEEKBAR, value)
    }
    suspend fun saveBlueSeekBarValue(value: Int) {
        saveSeekBarState(BLUESEEKBAR, value)
    }
    suspend fun saveRedSwitchState(value: Boolean) {
        saveSwitchState(REDSWITCH, value)
    }
    suspend fun saveGreenSwitchState(value: Boolean) {
        saveSwitchState(GREENSWITCH, value)
    }
    suspend fun saveBlueSwitchState(value: Boolean) {
        saveSwitchState(BLUESWITCH, value)
    }

    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "settings"
        private var INSTANCE: PreferencesRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                }
                INSTANCE = PreferencesRepository(dataStore)
            }
        }
        fun getRepository(): PreferencesRepository {
            return INSTANCE ?: throw IllegalStateException("AppPreferencesRepository not initialized yet")
        }
    }
}