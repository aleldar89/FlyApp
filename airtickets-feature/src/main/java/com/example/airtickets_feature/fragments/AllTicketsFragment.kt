package com.example.airtickets_feature.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airtickets_feature.AirticketsViewModel
import com.example.airtickets_feature.adapters.BaseAdapter
import com.example.airtickets_feature.databinding.FragmentAllTicketsBinding
import com.example.airtickets_feature.utils.dateFormat
import com.example.airtickets_feature.utils.dateFullFormat
import com.example.common_resources.R
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class AllTicketsFragment : Fragment() {

    private val viewModel: AirticketsViewModel by activityViewModels()

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding: FragmentAllTicketsBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    private val adapter by lazy { BaseAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            back.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.removeObservers(viewLifecycleOwner)
    }

    private fun setObservers() {
        viewModel.apply {
            ticketsData.observe(viewLifecycleOwner) { tickets ->
                adapter.items = tickets
            }

            departureDate.observe(viewLifecycleOwner) { date ->
                binding.txtDetails.text = resources.getString(
                    R.string.detail_data,
                    dateFullFormat().format(date.first)
                )
            }

            departureLocation.observe(viewLifecycleOwner) {
                if (it != null)
                    binding.txtFrom.text = it
            }

            arrivalLocation.observe(viewLifecycleOwner) {
                if (it != null)
                    binding.txtTo.text = resources.getString(R.string.to, it)
            }
        }
    }
}