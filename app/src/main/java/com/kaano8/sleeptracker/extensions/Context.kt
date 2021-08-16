package com.kaano8.sleeptracker.extensions

import android.content.Context
import androidx.core.content.edit

private const val SLEEP_TRACKER_FILE_NAME = "SleepTrackerAppData"
private const val START_TIME_MILLI = "startTimeMilli"

/**
 * Persist start time in shared preferences
 */
fun Context.startSleepTracking(startTime: Long) {
    this.getSharedPreferences(SLEEP_TRACKER_FILE_NAME, Context.MODE_PRIVATE).edit {
        putLong(START_TIME_MILLI, startTime)
        apply()
    }
}

/**
 * Get start time and reset shared preferences
 */
fun Context.getStartTimeAndReset(): Long {
    this.getSharedPreferences(SLEEP_TRACKER_FILE_NAME, Context.MODE_PRIVATE).apply {
        val startTime = getLong(START_TIME_MILLI, 0)
        edit {
            clear()
            apply()
        }
        return startTime
    }
}

/**
 * Check if sleep is already being tracked.
 */
fun Context.checkSleepTrackingStatus(): Boolean =
    this.getSharedPreferences(SLEEP_TRACKER_FILE_NAME, Context.MODE_PRIVATE).contains(START_TIME_MILLI)
