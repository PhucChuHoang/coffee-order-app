package com.example.coffee_order_app.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.coffee_order_app.R
import com.example.coffee_order_app.databinding.FragmentOrderSuccessBinding
class OrderSuccessFragment : Fragment() {
    private var _binding: FragmentOrderSuccessBinding? = null

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
        _binding = FragmentOrderSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numFragments = childFragmentManager.backStackEntryCount
        Log.d("OrderSuccessFragment", "BackStackEntryCount: $numFragments")
        val clickListener = View.OnClickListener { v ->
            when(v) {
                binding.trackButton -> {
                    findNavController().navigate(R.id.action_orderSuccessFragment_to_orderFragment, null, NavOptions.Builder().setPopUpTo(findNavController().graph.startDestinationId, true).build())
                }
            }
        }
        binding.trackButton.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}