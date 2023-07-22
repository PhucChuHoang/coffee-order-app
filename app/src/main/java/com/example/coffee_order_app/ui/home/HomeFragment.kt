package com.example.coffee_order_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.coffee_order_app.R
import com.example.coffee_order_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener = View.OnClickListener { v ->
            when (v) {
                binding.homeContentView.coffeeType1 -> {
                    println("Americano Selected")
                }
                binding.homeContentView.coffeeType2 -> {
                    println("Cappuccino Selected")
                }
                binding.homeContentView.coffeeType3 -> {
                    println("Mocha Selected")
                }
                binding.homeContentView.coffeeType4 -> {
                    println("Flat White Selected")
                }
                binding.profileIcon -> {
                    findNavController().navigate(R.id.action_navigation_home_to_profileFragment2)
                }
            }
        }

        binding.homeContentView.coffeeType1.setOnClickListener(clickListener)
        binding.homeContentView.coffeeType2.setOnClickListener(clickListener)
        binding.homeContentView.coffeeType3.setOnClickListener(clickListener)
        binding.homeContentView.coffeeType4.setOnClickListener(clickListener)
        binding.profileIcon.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}