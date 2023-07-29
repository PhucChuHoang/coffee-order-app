package com.example.coffee_order_app.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coffee_order_app.adapter.ViewPagerAdapter
import com.example.coffee_order_app.databinding.FragmentOrderBinding
import com.google.android.material.tabs.TabLayoutMediator

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(requireActivity())
        binding.orderViewPager.adapter = adapter

        TabLayoutMediator(binding.orderTabLayout, binding.orderViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "On Going"
                1 -> tab.text = "History"
            }
        }.attach()
    }
}