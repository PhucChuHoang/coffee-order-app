package com.example.coffee_order_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coffee_order_app.databinding.FragmentOrderSuccessBinding
class OrderSuccessFragment : Fragment() {
    private var _binding: FragmentOrderSuccessBinding? = null

    private val binding get() = _binding!!

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
        var numFragments = childFragmentManager.backStackEntryCount
        Log.d("OrderSuccessFragment", "BackStackEntryCount: $numFragments")
//        var clickListener = View.OnClickListener { v ->
//            when(v) {
//                binding.trackButton -> {
//                    findNavController().navigate(R.id.action_orderSuccessFragment_to_orderFragment)
//                }
//            }
//        }
//        binding.trackButton.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}