package com.example.short_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.short_feature.databinding.FragmentShortBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShortFragment : Fragment() {

    private var _binding: FragmentShortBinding? = null
    private val binding: FragmentShortBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}