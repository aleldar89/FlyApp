package com.example.airtickets_feature.fragments

import android.app.DatePickerDialog
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
import com.example.airtickets_feature.databinding.FragmentSearchCountryBinding
import com.example.airtickets_feature.utils.dateFormat
import com.example.airtickets_feature.utils.dayOfWeekFormat
import com.example.common_resources.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class SearchCountryFragment : Fragment() {

    private val viewModel: AirticketsViewModel by activityViewModels()

    private var _binding: FragmentSearchCountryBinding? = null
    private val binding: FragmentSearchCountryBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    private val adapter by lazy { BaseAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        setObservers()

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            viewModel.ticketsOffersData.observe(viewLifecycleOwner) { ticketsOffers ->
                val itemsToShow = ticketsOffers.take(3)
                adapter.items = itemsToShow
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveRoute()
        _binding = null
        viewModel.removeObservers(viewLifecycleOwner)
    }

    override fun onPause() {
        super.onPause()
        saveRoute()
    }

    private fun saveRoute() {
        binding.apply {
            if (inputTxtFrom.text != null)
                viewModel.saveDepartureLocation(inputTxtFrom.text.toString())

            if (inputTxtTo.text != null)
                viewModel.saveArrivalLocation(inputTxtTo.text.toString())
        }
    }

    private fun setOnClickListeners() {
        binding.apply {
            exchangeIcon.setOnClickListener {
                val tempText = inputTxtFrom.text.toString()
                inputTxtFrom.setText(inputTxtTo.text.toString())
                inputTxtTo.setText(tempText)
            }
            btnBackData.setOnClickListener { showDatePicker() }
            btnDepartureData.setOnClickListener { showDatePicker() }
            clearIcon.setOnClickListener { inputTxtTo.setText("") }
            back.setOnClickListener { findNavController().navigateUp() }
            btnShowAll.setOnClickListener {
                findNavController().navigate(
                    com.example.airtickets_feature.R.id.allTicketsFragment
                )
            }
        }
    }

    private fun setObservers() {
        viewModel.apply {
            ticketsOffersData.observe(viewLifecycleOwner) { ticketsOffers ->
                val itemsToShow = ticketsOffers.take(3)
                adapter.items = itemsToShow
            }
            departureDate.observe(viewLifecycleOwner) { date ->
                binding.txtDepartureData.text = dateFormat().format(date.first)
                binding.txtDepartureDay.text = date.second
            }
            departureLocation.observe(viewLifecycleOwner) {
                if (it != null)
                    binding.inputTxtFrom.setText(it)
            }
            arrivalLocation.observe(viewLifecycleOwner) {
                if (it != null)
                    binding.inputTxtTo.setText(it)
            }
        }
    }

    private fun showDatePicker() {
        val currentDate = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                currentDate.set(year, month, dayOfMonth)
                updateDateViews(currentDate)
            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDateViews(date: Calendar) {
        binding.txtDepartureData.text = dateFormat().format(date.time)
        binding.txtDepartureDay.text = resources.getString(
            R.string.day_of_week,
            dayOfWeekFormat().format(date.time)
        )
        viewModel.saveDepartureDate(date)
    }
}