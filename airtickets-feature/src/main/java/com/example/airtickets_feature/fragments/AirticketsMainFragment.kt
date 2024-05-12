package com.example.airtickets_feature.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airtickets_feature.AirticketsViewModel
import com.example.airtickets_feature.adapters.offerAdapterDelegate
import com.example.airtickets_feature.databinding.FragmentMainBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirticketsMainFragment : Fragment() {

    private val viewModel: AirticketsViewModel by activityViewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

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

        binding.apply {
            viewModel.departureLocation.observe(viewLifecycleOwner) {
                if (it != null)
                    inputTxtFrom.setText(it)
            }

            inputTxtTo.setOnClickListener {
                val bottomSheet = SearchBottomSheetFragment()
                bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            }

            val adapter = ListDelegationAdapter(offerAdapterDelegate())
            recyclerView.adapter = adapter
            val layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager

            viewModel.offersData.observe(viewLifecycleOwner) { offers ->
                adapter.items = offers
                adapter.notifyDataSetChanged() //FIXME
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveDepartureLocation()
        _binding = null
        viewModel.departureLocation.removeObservers(viewLifecycleOwner)
        viewModel.offersData.removeObservers(viewLifecycleOwner)
    }

    override fun onPause() {
        super.onPause()
        saveDepartureLocation()
    }

    private fun saveDepartureLocation() {
        binding.apply {
            if (inputTxtFrom.text != null)
                viewModel.saveDepartureLocation(inputTxtFrom.text.toString())
        }
    }
}