package com.example.coffee_order_app.ui.order

import android.graphics.Color
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
        val layoutManagerOnGoing = LinearLayoutManager(view.context)
        binding.onGoingRecyclerView.layoutManager = layoutManagerOnGoing
        binding.onGoingRecyclerView.adapter = OrderAdapter(arrayOf("Americano", "Cappuccino", "Flat White", "Latte"))
        val layoutManagerHistory = LinearLayoutManager(view.context)
        binding.historyRecyclerView.layoutManager = layoutManagerHistory
        binding.historyRecyclerView.adapter = OrderAdapter(arrayOf("Cappuccino", "Flat White", "Latte", "Americano"))

        val clickListener = View.OnClickListener { v ->
            when(v) {
                binding.historyBox -> {
                    binding.onGoingRecyclerView.visibility = View.GONE
                    binding.historyRecyclerView.visibility = View.VISIBLE
                    binding.historyRecyclerView.bringToFront()
                    binding.textOnGoing.setTextColor(Color.parseColor("#D8D8D8"))
                    binding.textOnHistory.setTextColor(Color.parseColor("#001833"))
                    binding.activeBarOnGoing.visibility = View.INVISIBLE
                    binding.activeBarHistory.visibility = View.VISIBLE
                }
                binding.onGoingBox -> {
                    binding.onGoingRecyclerView.visibility = View.VISIBLE
                    binding.historyRecyclerView.visibility = View.GONE
                    binding.onGoingRecyclerView.bringToFront()
                    binding.textOnHistory.setTextColor(Color.parseColor("#D8D8D8"))
                    binding.textOnGoing.setTextColor(Color.parseColor("#001833"))
                    binding.activeBarOnGoing.visibility = View.VISIBLE
                    binding.activeBarHistory.visibility = View.INVISIBLE
                }
            }
        }
        binding.historyBox.setOnClickListener(clickListener)
        binding.onGoingBox.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}