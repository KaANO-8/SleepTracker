package com.kaano8.sleeptracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

        val arrayAdapter: ArrayAdapter<*>
        val list = arrayOf(
            getString(R.string.sleep_data)
        )

        arrayAdapter = ArrayAdapter(
            context as DashboardActivity,
            android.R.layout.simple_list_item_1, list
        )
        _binding.list.adapter = arrayAdapter

        _binding.startButton.setOnClickListener {
            _binding.clearButton.isEnabled = false
            _binding.startButton.isEnabled = false
            showLoader()
        }

       _binding.stopButton.setOnClickListener {
           hideLoader()
           (activity as DashboardActivity).supportFragmentManager.beginTransaction()
               .replace(R.id.container, SleepQualityFragment.newInstance())
               .commitNow()
       }

        _binding.clearButton.setOnClickListener {

        }

    }

    private fun showLoader() {
        _binding.loader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        _binding.loader.visibility = View.GONE

    }

    companion object {
        fun newInstance() = SleepTrackerFragment()
    }

}