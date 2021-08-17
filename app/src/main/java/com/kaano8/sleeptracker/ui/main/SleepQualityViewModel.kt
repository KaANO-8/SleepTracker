package com.kaano8.sleeptracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaano8.sleeptracker.model.SleepNight

class SleepQualityViewModel: ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * Logs the sleep data
     */
    fun registerSleepData(sleepData: SleepNight) {
        _isLoading.value = true

        // Post sleep data here
    }
}