package com.example.tp1_mmm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp1_mmm.R
import com.example.tp1_mmm.databinding.FragmentAddBinding
import com.example.tp1_mmm.viewmodels.AddViewModel
import com.example.tp1_mmm.vm_factory.DBArgedFragment
import com.example.tp1_mmm.vm_factory.DBArgedViewModelFactory


class AddFragment : DBArgedFragment<AddViewModel>() {

    private var _binding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentAddBinding.inflate(inflater, container, false).let {
            _binding = it
            it.viewModel = ViewModelProvider(this, DBArgedViewModelFactory<AddViewModel>((requireActivity() as MainActivity).database))[AddViewModel::class.java]
            it.lifecycleOwner = this
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Back.setOnClickListener {
            findNavController().let {
                it.navigate(R.id.action_BackToList)
            }
        }
        binding.ValidateButton.setOnClickListener {
            findNavController().let {
                it.navigate(R.id.action_BackToList)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}