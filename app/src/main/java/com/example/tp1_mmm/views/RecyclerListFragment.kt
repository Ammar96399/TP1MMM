package com.example.tp1_mmm.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tp1_mmm.R
import com.example.tp1_mmm.databinding.FragmentRecyclerListBinding
import com.example.tp1_mmm.viewmodels.RecyclerListViewModel

class RecyclerListFragment : Fragment() {

    companion object {
        fun newInstance() = RecyclerListFragment()
    }

    private lateinit var viewModel: RecyclerListViewModel

    private var _binding: FragmentRecyclerListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentRecyclerListBinding.inflate(inflater, container, false).let {
            _binding = it
            it.viewModel = ViewModelProvider(this)[RecyclerListViewModel::class.java]
            it.lifecycleOwner = this
            return it.root
        }
    }
}