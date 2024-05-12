package com.example.subscribe_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.subscribe_feature.databinding.FragmentSubscribeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscribeFragment : Fragment() {

    private var _binding: FragmentSubscribeBinding? = null
    private val binding: FragmentSubscribeBinding
        get() = _binding ?: throw IllegalStateException("Uninitialized binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSubscribeBinding.inflate(inflater, container, false)

        return binding.root
    }
}