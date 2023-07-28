package com.example.coffee_order_app.ui.order

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.adapter.OrderAdapter
import com.example.coffee_order_app.databinding.TabHistoryBinding
import com.example.coffee_order_app.databinding.TabOnGoingBinding

class TabFragmentOnGoing : Fragment() {

    private var _binding: TabOnGoingBinding? = null

    private val binding get() = _binding!!

    private var adapter: OrderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                // Get the position of the swiped item
                val position = viewHolder.adapterPosition

                // Get the data from the source adapter at the position
                val item = adapter?.getItemViewType(position)

                // Remove the item from the source adapter and notify the change
                adapter?.notifyItemRemoved(position)
                Globals.historyOrder.add(Globals.onGoingOrder.get(position))
                Log.i("Latest Item", Globals.historyOrder.get(Globals.historyOrder.size - 1).coffeeName)
                Log.i("History", Globals.historyOrder.size.toString())
                Globals.onGoingOrder.removeAt(position)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Check if it's a right swipe or left swipe
                    val isRightSwipe = dX > 0
                    val isLeftSwipe = dX < 0

                    // You can perform customizations based on the swipe direction
                    // For example, change background colors or icon visibility

                    if (isRightSwipe) {
                        // It's a right swipe
                    } else if (isLeftSwipe) {
                        // It's a left swipe
                    }
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.onGoingRecyclerView)
    }
}