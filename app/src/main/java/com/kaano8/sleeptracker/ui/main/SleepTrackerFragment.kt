package com.kaano8.sleeptracker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaano8.sleeptracker.R

class SleepTrackerFragment : Fragment() {

    companion object {
        fun newInstance() = SleepTrackerFragment()
    }

    private lateinit var viewModel: SleepTrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.sleep_tracker_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // view model init
        viewModel = ViewModelProvider(this).get(SleepTrackerViewModel::class.java)
    }

}