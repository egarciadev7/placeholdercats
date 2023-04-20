package com.test.placeholdercats.cats.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.placeholdercats.R
import com.test.placeholdercats.cats.adapters.CatListAdapter
import com.test.placeholdercats.cats.viewmodels.ViewModelCat
import com.test.placeholdercats.databinding.FragmentViewCatsBinding
import com.test.placeholdercats.utils.hidden
import com.test.placeholdercats.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewCatsFragment : Fragment() {
    private lateinit var binding: FragmentViewCatsBinding
    private val viewModel: ViewModelCat by viewModels()
    private val adapter by lazy {
        CatListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewCatsBinding.inflate(inflater, container, false)
        initView()
        initObservers()
        viewModel.getCats()
        return binding.root
    }

    private fun initView() {
        binding.catsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.catsList.adapter = adapter
    }


    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loader.show(it)
        }

        viewModel.cats.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.catsList.show()
        }

        viewModel.notResults.observe(viewLifecycleOwner) {
            binding.listIsEmpty.show(it)
            if (it) {
                binding.catsList.hidden()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.something_is_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


