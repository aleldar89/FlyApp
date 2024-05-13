package com.example.airtickets_feature.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.airtickets_feature.databinding.FragmentEmptyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyFragment : Fragment() {

    private var _binding: FragmentEmptyBinding? = null
    private val binding: FragmentEmptyBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmptyBinding.inflate(inflater, container, false)

        return binding.root
    }
}