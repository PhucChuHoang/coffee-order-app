package com.example.coffee_order_app.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.adapter.OrderAdapter
import com.example.coffee_order_app.databinding.TabHistoryBinding

class TabFragmentHistory : Fragment() {

    private var _binding: TabHistoryBinding? = null

    private val binding get() = _binding!!

    private var adapter: OrderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TabHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        this.adapter = OrderAdapter(Globals.historyOrder)
        binding.historyRecyclerView.adapter = this.adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(view.context)
        binding.historyRecyclerView.layoutManager = layoutManager
    }
}
