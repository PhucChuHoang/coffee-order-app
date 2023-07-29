package com.example.coffee_order_app.ui.redeem

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.adapter.HistoryRewardAdapter
import com.example.coffee_order_app.adapter.RedeemAdapter
import com.example.coffee_order_app.databinding.FragmentRedeemBinding
import com.example.coffee_order_app.databinding.FragmentRewardBinding

class RedeemFragment : Fragment() {

    private var _binding: FragmentRedeemBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRedeemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(view.context)
        binding.redeemRecyclerView.layoutManager = layoutManager
        binding.redeemRecyclerView.adapter = RedeemAdapter(Globals.redeemItemList)
        val clickListener = View.OnClickListener { v ->
            when(v) {
                binding.redeemReturnButton -> {
                    findNavController().popBackStack()
                }
            }
        }
        binding.redeemReturnButton.setOnClickListener(clickListener)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}