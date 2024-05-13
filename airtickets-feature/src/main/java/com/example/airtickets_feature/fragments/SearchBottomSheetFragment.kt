package com.example.airtickets_feature.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.airtickets_feature.AirticketsViewModel
import com.example.airtickets_feature.databinding.FragmentBottomSheetBinding
import com.example.airtickets_feature.loadImage
import com.example.common_resources.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel: AirticketsViewModel by activityViewModels()

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding: FragmentBottomSheetBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TransparentDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel.departureLocation.observe(viewLifecycleOwner) {
                if (it != null)
                    inputTxtFrom.setText(it)
            }

            inputTxtTo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val text = s.toString().trim()
                    if (text.isNotEmpty()) {
                        dismiss()
                        findNavController().navigate(
                            com.example.airtickets_feature.R.id.searchCountryFragment
                        )
                    }
                }
            })
        }
        loadImages()
        setClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveRoute()
        _binding = null
        viewModel.departureLocation.removeObservers(viewLifecycleOwner)
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

    private fun setClickListeners() {
        binding.apply {
            clearTxt.setOnClickListener {
                inputTxtTo.setText("")
            }
            routeContainer1.setOnClickListener {
                inputTxtTo.setText(cityName1.text)
            }
            routeContainer2.setOnClickListener {
                inputTxtTo.setText(cityName2.text)
            }
            routeContainer3.setOnClickListener {
                inputTxtTo.setText(cityName3.text)
            }
            anywhere.setOnClickListener {
                inputTxtTo.setText(
                    context?.resources?.getString(
                        R.string.anywhere
                    )
                )
            }
            diffRoute.setOnClickListener {
                navigateToEmpty()
            }
            weekend.setOnClickListener {
                navigateToEmpty()
            }
            hot.setOnClickListener {
                navigateToEmpty()
            }
        }
    }

    private fun loadImages() {
        binding.apply {
            imageView1.loadImage(com.example.airtickets_feature.R.drawable.four)
            imageView2.loadImage(com.example.airtickets_feature.R.drawable.five)
            imageView3.loadImage(com.example.airtickets_feature.R.drawable.six)
        }
    }

    private fun navigateToEmpty() {
        dismiss()
        findNavController().navigate(com.example.airtickets_feature.R.id.emptyFragment)
    }
}