package com.example.airtickets_feature.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airtickets_feature.AirticketsViewModel
import com.example.airtickets_feature.adapters.calculateHoursDifference
import com.example.airtickets_feature.adapters.ticketAdapterDelegate
import com.example.airtickets_feature.adapters.ticketOfferAdapterDelegate
import com.example.airtickets_feature.databinding.FragmentAllTicketsBinding
import com.example.airtickets_feature.databinding.FragmentSearchCountryBinding
import com.example.common_resources.R
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AllTicketsFragment : Fragment() {

    private val viewModel: AirticketsViewModel by activityViewModels()

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding: FragmentAllTicketsBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

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

        binding.apply {

            setObservers()

            val adapter = ListDelegationAdapter(ticketAdapterDelegate())
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            viewModel.ticketsData.observe(viewLifecycleOwner) { tickets ->
                adapter.items = tickets
                adapter.notifyDataSetChanged() //FIXME
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        removeObservers()
    }

    private fun setObservers() {
        viewModel.departureDate.observe(viewLifecycleOwner) { currentDate ->
            binding.txtDetails.text = context?.resources?.getString(
                R.string.detail_data,
                currentDate.first
            )
        }
        viewModel.departureLocation.observe(viewLifecycleOwner) {
            if (it != null)
                binding.txtFrom.setText(it)
        }
        viewModel.arrivalLocation.observe(viewLifecycleOwner) {
            if (it != null)
                binding.txtTo.setText(it)
        }
    }
    private fun removeObservers() {
        viewModel.apply {
            departureDate.removeObservers(viewLifecycleOwner)
            ticketsData.removeObservers(viewLifecycleOwner)
            departureLocation.removeObservers(viewLifecycleOwner)
            arrivalLocation.removeObservers(viewLifecycleOwner)
        }
    }
}