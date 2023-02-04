package com.example.firstnetworkapi.views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.firstnetworkapi.R
import com.example.firstnetworkapi.databinding.FragmentSchoolDetailsBinding
import com.example.firstnetworkapi.model.SatScoresItem
import com.example.firstnetworkapi.viewmodel.SchoolsViewModel
import com.example.starwarsmvvm.utils.SchoolViewModelFactory
import javax.inject.Inject

class SchoolDetailsFragment : Fragment() {

    private val binding by lazy {
        FragmentSchoolDetailsBinding.inflate(layoutInflater)
    }
    lateinit var schoolsViewModelFactory: SchoolViewModelFactory

    private val schoolsViewModel: SchoolsViewModel by lazy {
        ViewModelProvider(requireActivity(), schoolsViewModelFactory)[SchoolsViewModel::class.java]
    }

    @SuppressLint("StringFormatInvalid")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        schoolsViewModel.satScores.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<*> -> {
                    Log.d(TAG, "onCreateView: ")
                    val temp = state.response as List<SatScoresItem>
                    binding.schoolName.text = temp.last().schoolName
                    binding.numOfSatTestTakers.text = getString(
                        R.string.sat_score_test_takers_number,
                        temp.last().numOfSatTestTakers
                    )
                    binding.satMathAvgScore.text =
                        getString(R.string.math_average_score, temp.last().satMathAvgScore)
                    binding.satCriticalReadingAvgScore.text = getString(
                        R.string.critical_reading_average_score,
                        temp.last().satCriticalReadingAvgScore
                    )
                    binding.satWritingAvgScore.text =
                        getString(R.string.writing_average_score, temp.last().satWritingAvgScore)
                }
                is UIState.ERROR -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error occurred")
                        .setMessage(state.error.localizedMessage)
                        .setPositiveButton("RETRY") { dialog, _ ->
                            schoolsViewModel.getSchools()
                            dialog.dismiss()
                        }
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }

        }
        return binding.root
    }
}


