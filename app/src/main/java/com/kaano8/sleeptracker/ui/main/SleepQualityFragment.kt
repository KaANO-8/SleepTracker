package com.kaano8.sleeptracker.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kaano8.sleeptracker.R
import com.kaano8.sleeptracker.databinding.SleepQualityFragmentBinding
import com.kaano8.sleeptracker.extensions.gone
import com.kaano8.sleeptracker.extensions.visible
import com.kaano8.sleeptracker.model.SleepNight

class SleepQualityFragment : Fragment() {

    private lateinit var _binding: SleepQualityFragmentBinding

    private val viewModel by viewModels<SleepQualityViewModel>()

    private val args: SleepQualityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SleepQualityFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(_binding) {
            qualityZeroImage.setOnClickListener {
                submitSleepDataAndNavigateBack(0)
            }

            qualityOneImage.setOnClickListener {
                submitSleepDataAndNavigateBack(1)
            }

            qualityTwoImage.setOnClickListener {
                submitSleepDataAndNavigateBack(2)
            }

            qualityThreeImage.setOnClickListener {
                submitSleepDataAndNavigateBack(3)
            }

            qualityFourImage.setOnClickListener {
                submitSleepDataAndNavigateBack(4)
            }

            qualityFiveImage.setOnClickListener {
                submitSleepDataAndNavigateBack(5)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { inProgress ->
            with(_binding) {
                if(inProgress) {
                    Toast.makeText(context, "Uploading sleep data, Please wait..", Toast.LENGTH_SHORT).show()
                    qualityContainer.gone()
                    progressBar.visible()
                } else {
                    // TODO: Move to failure
                    qualityContainer.visible()
                    progressBar.gone()
                }
            }
        }
    }

    private fun submitSleepDataAndNavigateBack(sleepQuality: Int) {
        val sleepData = SleepNight(
            startTimeMilli = args.startTime,
            endTimeMilli = args.stopTime,
            sleepQuality = sleepQuality
        )
        viewModel.registerSleepData(sleepData)

        // Mimic API call by delaying this call after sometime
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(context, "Data Logged successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_sleepQualityFragment_to_sleepTrackerFragment)
        }, 2000)
    }
}