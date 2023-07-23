package com.example.coffee_order_app.ui.home

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.coffee_order_app.R
import com.example.coffee_order_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        ViewCompat.setTransitionName(binding.homeContentView.coffeeType1, "item_image1")
        ViewCompat.setTransitionName(binding.homeContentView.coffeeType2, "item_image2")
        ViewCompat.setTransitionName(binding.homeContentView.coffeeType3, "item_image3")
        ViewCompat.setTransitionName(binding.homeContentView.coffeeType4, "item_image4")

        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener = View.OnClickListener { v ->
            val bundle = Bundle()
            val extras: FragmentNavigator.Extras
            when (v) {
                binding.homeContentView.coffeeType1 -> {
                    bundle.putInt("intKey", 1)
                    extras = FragmentNavigatorExtras(
                        binding.homeContentView.coffeeType1 to "hero_image"
                    )
                }
                binding.homeContentView.coffeeType2 -> {
                    bundle.putInt("intKey", 2)
                    extras = FragmentNavigatorExtras(
                        binding.homeContentView.coffeeType2 to "hero_image"
                    )
                }
                binding.homeContentView.coffeeType3 -> {
                    bundle.putInt("intKey", 3)
                    extras = FragmentNavigatorExtras(
                        binding.homeContentView.coffeeType3 to "hero_image"
                    )
                }
                binding.homeContentView.coffeeType4 -> {
                    bundle.putInt("intKey", 4)
                    extras = FragmentNavigatorExtras(
                        binding.homeContentView.coffeeType4 to "hero_image"
                    )
                }
                binding.profileIcon -> {
                    findNavController().navigate(R.id.action_navigation_home_to_profileFragment)
                    return@OnClickListener
                }
                binding.buyIcon -> {
                    findNavController().navigate(R.id.action_navigation_home_to_cartFragment)
                    return@OnClickListener
                }
                else -> return@OnClickListener
            }
            findNavController().navigate(R.id.action_navigation_home_to_detailsFragment, bundle, null, extras)
        }

        binding.homeContentView.coffeeType1.setOnClickListener(clickListener)
        binding.homeContentView.coffeeType2.setOnClickListener(clickListener)
        binding.homeContentView.coffeeType3.setOnClickListener(clickListener)
        binding.homeContentView.coffeeType4.setOnClickListener(clickListener)
        binding.profileIcon.setOnClickListener(clickListener)
        binding.buyIcon.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}