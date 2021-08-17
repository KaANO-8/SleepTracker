package com.kaano8.sleeptracker.ui.main

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kaano8.sleeptracker.databinding.SleepTrackerFragmentBinding
import com.kaano8.sleeptracker.extensions.disable
import com.kaano8.sleeptracker.extensions.enable

class SleepTrackerFragment : Fragment() {

    private lateinit var arrayAdapter: ArrayAdapter<Spannable>

    private lateinit var _binding: SleepTrackerFragmentBinding

    private val viewModel: SleepTrackerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SleepTrackerFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1)
        _binding.list.adapter = arrayAdapter

        // Initialise tracking state by checking key in shared preferences
        viewModel.setTrackingState(
            isTracking = requireContext().getSharedPreferences(
                SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE
            ).contains(START_TIME_KEY)
        )

        // Adding observers for live data
        viewModel.isTracking.observe(viewLifecycleOwner) { isTracking ->
            if (isTracking) setTrackingState()
            else setNewState()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            with(_binding) {
                loader.visibility = if (isLoading) View.VISIBLE else View.GONE
                list.visibility = if (isLoading) View.GONE else View.VISIBLE
            }
        }

        viewModel.sleepData.observe(viewLifecycleOwner) {
            arrayAdapter.apply {
                clear()
                addAll(it)
                notifyDataSetChanged()
            }
        }

        // Add on click listeners to start, stop and clear button
        with(_binding) {
            startButton.setOnClickListener {
                startSleepTracker()
            }

            stopButton.setOnClickListener {
                stopSleepTracker()
            }

            clearButton.setOnClickListener {
                viewModel.clearSleepData()
            }
        }

        // Mocking API call
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.populateSleepData()
        }, 2000)

    }

    private fun startSleepTracker() {
        viewModel.setTrackingState(isTracking = true)
        requireContext()
            .getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).edit {
                putLong(START_TIME_KEY, System.currentTimeMillis())
                apply()
            }
    }

    private fun stopSleepTracker() {
        viewModel.setTrackingState(isTracking = false)
        with(
            requireContext().getSharedPreferences(
                SHARED_PREFERENCES_FILE_NAME,
                Context.MODE_PRIVATE
            )
        ) {
            val startTime = getLong(START_TIME_KEY, 0)
            edit {
                clear()
                apply()
            }
            val stopTime = System.currentTimeMillis()
            val action = SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(
                startTime = startTime,
                stopTime = stopTime
            )
            findNavController().navigate(action)
        }
    }

    private fun setTrackingState() {
        _binding.apply {
            startButton.disable()
            stopButton.enable()
        }
    }

    private fun setNewState() {
        _binding.apply {
            startButton.enable()
            stopButton.disable()
        }
    }

    companion object {
        fun newInstance() = SleepTrackerFragment()
        const val SHARED_PREFERENCES_FILE_NAME = "SleepTrackerData"
        const val START_TIME_KEY = "sleepStartTime"
    }

}