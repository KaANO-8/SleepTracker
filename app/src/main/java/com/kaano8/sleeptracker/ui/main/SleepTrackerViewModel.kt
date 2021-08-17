package com.kaano8.sleeptracker.ui.main

import android.text.Spannable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaano8.sleeptracker.model.SleepNight

class SleepTrackerViewModel : ViewModel() {

    private val _sleepData: MutableLiveData<List<Spannable>> = MutableLiveData()
    val sleepData: LiveData<List<Spannable>> = _sleepData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isTracking: MutableLiveData<Boolean> = MutableLiveData()
    val isTracking: LiveData<Boolean> = _isTracking

    init {
        _isLoading.value = true
    }

    fun setTrackingState(isTracking: Boolean) {
        _isTracking.value = isTracking
    }

    private fun getSleepData() = mockSleepData().map { it.getFormattedString() }

    /**
     * Mock sleep data added to mimic API
     */
    private fun mockSleepData() = mutableListOf<SleepNight>().apply {
        repeat(5) {
            add(
                SleepNight(
                    startTimeMilli = System.currentTimeMillis(),
                    endTimeMilli = System.currentTimeMillis() + 10000,
                    sleepQuality = (0..5).random()
                )
            )
        }
    }

    fun populateSleepData() {
        _sleepData.value = getSleepData()
        _isLoading.value = false
    }

    fun clearSleepData() {
        // To be implemented later
    }
}