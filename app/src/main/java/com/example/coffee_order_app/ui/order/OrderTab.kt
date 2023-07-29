package com.example.coffee_order_app.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.adapter.OrderAdapter
import com.example.coffee_order_app.databinding.TabOnGoingBinding

class TabFragmentOnGoing : Fragment() {

    private var _binding: TabOnGoingBinding? = null

    private val binding get() = _binding!!

    private var adapter: OrderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TabOnGoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        this.adapter = OrderAdapter(Globals.onGoingOrder)
        binding.onGoingRecyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(view.context)
        binding.onGoingRecyclerView.layoutManager = layoutManager
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                adapter?.notifyItemRemoved(position)
                Globals.historyOrder.add(Globals.onGoingOrder[position])
                Globals.onGoingOrder.removeAt(position)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.onGoingRecyclerView)
    }
}
