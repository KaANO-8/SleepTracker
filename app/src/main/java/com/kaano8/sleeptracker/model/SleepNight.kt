package com.kaano8.sleeptracker.model

import android.text.Spannable
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.toSpannable
import java.text.SimpleDateFormat
import java.util.Locale

data class SleepNight(
    val nightId: Int? = null,
    val startTimeMilli: Long,
    val endTimeMilli: Long,
    val sleepQuality: Int
) {
    fun getFormattedString(): Spannable {
        return SpannableStringBuilder()
            .bold { append("Start: ") }
            .append(getDateAndTime(startTimeMilli))
            .append("\n")
            .bold { append("End: ") }
            .append(getDateAndTime(endTimeMilli))
            .append("\n")
            .bold { append("Quality: ") }
            .append(sleepQuality.toString())
            .append("\n")
            .bold { append("Sleep time: ") }
            .append(getDuration())
            .append("\n")
            .toSpannable()
    }

    private fun getDateAndTime(timeMilli: Long): String =
        SimpleDateFormat(
            "EEE, MMM dd, yyyy, hh:mm:ss aaa",
            Locale.getDefault()
        ).format(timeMilli)

    private fun getDuration(): String {
        val timeDifference = endTimeMilli - startTimeMilli
        val hours = timeDifference / (1000 * 60 * 60)
        val minutes = (timeDifference / (1000 * 60)) - (hours * 60)
        val seconds = (timeDifference / 1000) - ((hours * 60 * 60) + minutes * 60)
        return "$hours h, $minutes m, $seconds s"
    }
}