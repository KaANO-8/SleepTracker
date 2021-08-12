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
import com.kaano8.sleeptracker.extensions.disable
import com.kaano8.sleeptracker.extensions.enable

class SleepTrackerFragment : Fragment() {

    private lateinit var arrayAdapter: ArrayAdapter<String>

    private lateinit var list: List<String>

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
        list = resources.getStringArray(R.array.sleep_data).toList()
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        _binding.list.adapter = arrayAdapter

        _binding.startButton.setOnClickListener {
            _binding.stopButton.enable()
            _binding.startButton.disable()
        }

       _binding.stopButton.setOnClickListener {
           (activity as DashboardActivity).supportFragmentManager.beginTransaction()
               .replace(R.id.container, SleepQualityFragment.newInstance())
               .commitNow()
       }

        _binding.clearButton.setOnClickListener {
            //clear data to be implemented later
        }

    }

    companion object {
        fun newInstance() = SleepTrackerFragment()
    }

}