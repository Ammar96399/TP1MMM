package com.example.tp1_mmm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tp1_mmm.databinding.FragmentRecyclerListBinding
import com.example.tp1_mmm.db.AppDatabase
import com.example.tp1_mmm.viewmodels.RecyclerListViewModel
import com.example.tp1_mmm.vm_factory.DBArgedFragment
import com.example.tp1_mmm.vm_factory.DBArgedViewModelFactory

class RecyclerListFragment() : DBArgedFragment<RecyclerListViewModel>() {

    companion object {
        fun newInstance() = RecyclerListFragment()
    }

    private var viewModel: RecyclerListViewModel = dbViewModel as RecyclerListViewModel

    private var _binding: FragmentRecyclerListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentRecyclerListBinding.inflate(inflater, container, false).let {
            _binding = it
            it.viewModel = ViewModelProvider(this, DBArgedViewModelFactory<RecyclerListViewModel>((requireActivity() as MainActivity).database))[RecyclerListViewModel::class.java]
            it.lifecycleOwner = this
            return it.root
        }
    }
}