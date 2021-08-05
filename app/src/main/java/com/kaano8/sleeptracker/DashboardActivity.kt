package com.kaano8.sleeptracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaano8.sleeptracker.ui.main.SleepTrackerFragment

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SleepTrackerFragment.newInstance())
                .commitNow()
        }
    }
}