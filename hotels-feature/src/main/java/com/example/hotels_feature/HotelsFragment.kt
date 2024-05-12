package com.example.hotels_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hotels_feature.databinding.FragmentHotelsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelsFragment : Fragment() {

    private var _binding: FragmentHotelsBinding? = null
    private val binding: FragmentHotelsBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHotelsBinding.inflate(inflater, container, false)

        return binding.root
    }
}