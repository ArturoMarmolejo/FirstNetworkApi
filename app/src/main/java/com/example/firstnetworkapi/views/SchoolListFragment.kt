package com.example.firstnetworkapi.views

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstnetworkapi.R
import com.example.firstnetworkapi.adapter.SchoolAdapter
import com.example.firstnetworkapi.adapter.SchoolViewHolder
import com.example.firstnetworkapi.databinding.FragmentSchoolListBinding

import com.example.firstnetworkapi.model.SchoolsItem
import com.example.firstnetworkapi.utils.BaseFragment

class SchoolListFragment: BaseFragment() {
    private val binding by lazy {
        FragmentSchoolListBinding.inflate(layoutInflater)
    }
    
    private val schoolAdapter by lazy {
        SchoolAdapter{
            Log.d(TAG, "SchoolAdapter on Clicked: it = ${it.dbn}")
            schoolsViewModel.getSchools(it.dbn)
            findNavController().navigate(R.id.action_SchoolsFragment_to_DetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.schoolRv.apply{
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = schoolAdapter
        }

        schoolsViewModel.schools.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<*> -> {
                    schoolAdapter.updateSchools(state.response as List<SchoolsItem>)
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