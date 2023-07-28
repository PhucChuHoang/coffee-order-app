package com.example.coffee_order_app.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.adapter.OrderAdapter
import com.example.coffee_order_app.databinding.TabHistoryBinding
import com.example.coffee_order_app.databinding.TabOnGoingBinding

class TabFragmentOnGoing : Fragment() {

    private var _binding: TabOnGoingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TabOnGoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(view.context)
        binding.onGoingRecyclerView.layoutManager = layoutManager
        val adapter = OrderAdapter(Globals.onGoingOrder)
        binding.onGoingRecyclerView.adapter = adapter
    }
}
