package com.example.coffee_order_app.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.adapter.OrderAdapter
import com.example.coffee_order_app.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        binding.onGoingRecyclerView.layoutManager = layoutManager
        binding.onGoingRecyclerView.adapter = OrderAdapter(arrayOf("Americano", "Cappuccino", "Flat White", "Latte"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}