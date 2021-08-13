package com.kaano8.sleeptracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaano8.sleeptracker.R

class SleepTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.sleep_tracker_fragment, container, false)
    }

    companion object {
        fun newInstance() = SleepTrackerFragment()
    }

}