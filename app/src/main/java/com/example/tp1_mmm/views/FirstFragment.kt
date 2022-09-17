package com.example.tp1_mmm.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tp1_mmm.viewmodels.SubmitViewModel
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.tp1_mmm.R
import com.example.tp1_mmm.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {



    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[SubmitViewModel::class.java]
        binding.submitViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.person.observe(viewLifecycleOwner
        ) { user ->
            Toast.makeText(
                context,
                "User ${user.firstName} ${user.lastName} was born in ${user.birthPlace}",
                Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.NextFrag.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}