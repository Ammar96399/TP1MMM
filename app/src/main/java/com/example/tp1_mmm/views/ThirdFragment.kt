package com.example.tp1_mmm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp1_mmm.R
import com.example.tp1_mmm.databinding.FragmentThirdBinding
import com.example.tp1_mmm.viewmodels.RecyclerListViewModel
import com.example.tp1_mmm.vm_factory.DBArgedFragment
import com.example.tp1_mmm.vm_factory.DBArgedViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : DBArgedFragment<RecyclerListViewModel>() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        FragmentThirdBinding.inflate(inflater, container, false).let {
            _binding = it
            it.viewModel = ViewModelProvider(this, DBArgedViewModelFactory<RecyclerListViewModel>((requireActivity() as MainActivity).database))[RecyclerListViewModel::class.java]
            it.lifecycleOwner = this
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonThird.setOnClickListener {
            findNavController().let {
                it.navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }
        }
        binding.buttonThird1.setOnClickListener {
            findNavController().let {
                it.navigate(R.id.action_AddPersonInfoToList)
            }
        }
        binding.buttonThird2.setOnClickListener {
            findNavController().let {
                it.navigate(R.id.action_ThirdFragment_to_FirstFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}