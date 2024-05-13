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
import com.example.airtickets_feature.adapters.ticketOfferAdapterDelegate
import com.example.airtickets_feature.databinding.FragmentSearchCountryBinding
import com.example.common_resources.R
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class SearchCountryFragment : Fragment() {

    private val viewModel: AirticketsViewModel by activityViewModels()

    private var _binding: FragmentSearchCountryBinding? = null
    private val binding: FragmentSearchCountryBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

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

        binding.apply {

            setOnClickListeners()
            setObservers()

            val adapter = ListDelegationAdapter(ticketOfferAdapterDelegate())
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            viewModel.ticketsOffersData.observe(viewLifecycleOwner) { ticketsOffers ->
                val itemsToShow = ticketsOffers.take(3) // Взять только первые три элемента
                adapter.items = itemsToShow
                adapter.notifyDataSetChanged() //FIXME
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveRoute()
        _binding = null
        removeObservers()
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
            btnBackData.setOnClickListener {
                showDatePicker()
            }
            btnDepartureData.setOnClickListener {
                showDatePicker()
            }
            clearIcon.setOnClickListener {
                inputTxtTo.setText("")
            }
            back.setOnClickListener {
                    findNavController().navigateUp()
                }
            binding.btnShowAll.setOnClickListener {
                findNavController().navigate(
                    com.example.airtickets_feature.R.id.allTicketsFragment
                )
            }
        }
    }

    private fun setObservers() {
        viewModel.departureDate.observe(viewLifecycleOwner) { currentDate ->
            binding.txtDepartureData.text = currentDate.first
            binding.txtDepartureDay.text = currentDate.second
        }

        viewModel.departureLocation.observe(viewLifecycleOwner) {
            if (it != null)
                binding.inputTxtFrom.setText(it)
        }

        viewModel.arrivalLocation.observe(viewLifecycleOwner) {
            if (it != null)
                binding.inputTxtTo.setText(it)
        }
    }

    private fun removeObservers() {
        viewModel.apply {
            departureDate.removeObservers(viewLifecycleOwner)
            ticketsOffersData.removeObservers(viewLifecycleOwner)
            departureLocation.removeObservers(viewLifecycleOwner)
            arrivalLocation.removeObservers(viewLifecycleOwner)
        }
    }

    private fun showDatePicker() {
        val currentDate = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                currentDate.set(year, month, dayOfMonth)
                updateDateViews(currentDate)
            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun updateDateViews(date: Calendar) {
        binding.apply {
            txtDepartureData.text = viewModel.dateFormat().format(date.time)
            txtDepartureDay.text = context?.resources?.getString(
                R.string.day_of_week,
                viewModel.dayOfWeekFormat().format(date.time)
            )
        }
        viewModel.saveDepartureDate(date)
    }
}