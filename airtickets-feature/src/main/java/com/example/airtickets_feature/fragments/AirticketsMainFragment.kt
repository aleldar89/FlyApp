package com.example.airtickets_feature.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airtickets_feature.AirticketsViewModel
import com.example.airtickets_feature.adapters.BaseAdapter
import com.example.airtickets_feature.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirticketsMainFragment : Fragment() {

    private val viewModel: AirticketsViewModel by activityViewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    private val adapter by lazy { BaseAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            inputTxtTo.setOnClickListener {
                val bottomSheet = SearchBottomSheetFragment()
                bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveDepartureLocation()
        _binding = null
        viewModel.removeObservers(viewLifecycleOwner)
    }

    override fun onPause() {
        super.onPause()
        saveDepartureLocation()
    }

    private fun saveDepartureLocation() {
        binding.inputTxtFrom.text?.toString()?.let {
            viewModel.saveDepartureLocation(it)
        }
    }

    private fun setObservers() {
        viewModel.apply {
            offersData.observe(viewLifecycleOwner) { offers ->
                adapter.items = offers
            }

            departureLocation.observe(viewLifecycleOwner) {
                if (it != null)
                    binding.inputTxtFrom.setText(it)
            }
        }
    }
}