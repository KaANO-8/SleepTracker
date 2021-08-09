package com.kaano8.sleeptracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaano8.sleeptracker.DashboardActivity
import com.kaano8.sleeptracker.R
import com.kaano8.sleeptracker.databinding.SleepTrackerFragmentBinding

class SleepTrackerFragment : Fragment() {

    private lateinit var _binding: SleepTrackerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SleepTrackerFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       _binding.stopButton.setOnClickListener {
           (activity as DashboardActivity).supportFragmentManager.beginTransaction()
               .replace(R.id.container, SleepQualityFragment.newInstance())
               .commitNow()
       }

    }

    companion object {
        fun newInstance() = SleepTrackerFragment()
    }

}