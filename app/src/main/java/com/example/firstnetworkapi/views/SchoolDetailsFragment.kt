package com.example.firstnetworkapi.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.firstnetworkapi.databinding.FragmentSchoolDetailsBinding
import com.example.firstnetworkapi.databinding.FragmentSchoolListBinding
import com.example.firstnetworkapi.viewmodel.SchoolsViewModel
import com.example.starwarsmvvm.utils.SchoolViewModelFactory
import javax.inject.Inject

class SchoolDetailsFragment : Fragment() {

    private val binding by lazy {
        FragmentSchoolDetailsBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var schoolsViewModelFactory: SchoolViewModelFactory

    private val schoolsViewModel: SchoolsViewModel by lazy {
        ViewModelProvider(requireActivity(), schoolsViewModelFactory)[SchoolsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }


}


//    private val schoolAdapter by lazy {
//        SchoolAdapter {
//            findNavController().navigate(R.id.action_SchoolsFragment_to_DetailsFragment)
//        }
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        binding.schoolRv.apply {
//            layoutManager = LinearLayoutManager(
//                requireContext(),
//                LinearLayoutManager.VERTICAL,
//                false
//            )
//            adapter = schoolAdapter
//        }
//
//        schoolsViewModel.schools.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is UIState.LOADING -> {
//
//                }
//                is UIState.SUCCESS_SCHOOLSITEM -> {
//                    schoolAdapter.updateSchools(state.response)
//                }
//                is UIState.ERROR -> {
//                    AlertDialog.Builder(requireActivity())
//                        .setTitle("Error occurred")
//                        .setMessage(state.error.localizedMessage)
//                        .setPositiveButton("RETRY") { dialog, _ ->
//                            schoolsViewModel.getSchools()
//                            dialog.dismiss()
//                        }
//                        .setNegativeButton("DISMISS") { dialog, _ ->
//                            dialog.dismiss()
//                        }
//                        .create()
//                        .show()
//                }
//                else -> {
//                    Log.d(TAG, "onCreateView: Not contemplated")
//                }
//            }
//
//        }
//
//        schoolsViewModel.getSchools()
//        return binding.root
//
//    }
//}
