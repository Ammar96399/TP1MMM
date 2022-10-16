package com.example.tp1_mmm.views

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tp1_mmm.viewmodels.SubmitViewModel
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.tp1_mmm.R
import com.example.tp1_mmm.databinding.FragmentFirstBinding
import com.example.tp1_mmm.databinding.FragmentSecondBinding

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

        setHasOptionsMenu(true)

        FragmentFirstBinding.inflate(inflater, container, false).let {
            _binding = it
            val viewModel = ViewModelProvider(requireActivity())[SubmitViewModel::class.java]
            it.submitViewModel = viewModel
            it.lifecycleOwner = this
            viewModel.person.observe(viewLifecycleOwner
            ) { user ->
                Toast.makeText(
                    context,
                    "User ${user.firstName} ${user.lastName} was born in ${user.birthPlace}",
                    Toast.LENGTH_SHORT).show()
            }
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = view.findViewById(R.id.spinner)

        ArrayAdapter
            .createFromResource(this.requireContext(), R.array.planet_array, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

        binding.NextFrag.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_enable_password -> {
                binding.submitViewModel?.enablePassword()
                true
            }
            R.id.menu_disable_password -> {
                binding.submitViewModel?.disablePassword()
                true
            }
            R.id.menu_clear_fields -> {
                binding.submitViewModel?.clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.first_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}