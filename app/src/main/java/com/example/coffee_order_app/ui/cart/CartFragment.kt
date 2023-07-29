package com.example.coffee_order_app.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.adapter.CartAdapter
import com.example.coffee_order_app.databinding.FragmentCartBinding
import com.example.coffee_order_app.ui.order.OrderList
import com.example.coffee_order_app.ui.reward.RewardItem
import java.text.SimpleDateFormat
import java.util.Date

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null

    private val binding get() = _binding!!

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        val totalPrice = "$" + String.format("%.2f", cartViewModel.getTotalPrice())
        val adapter = CartAdapter(Globals.cartItemList)
        binding.totalPrice.text = totalPrice
        binding.recyclerViewCart.layoutManager = layoutManager
        binding.recyclerViewCart.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
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
                adapter.notifyItemRemoved(position)
                Globals.cartItemList.removeAt(position)
                val totalPrice = "$" + String.format("%.2f", cartViewModel.getTotalPrice())
                binding.totalPrice.text = totalPrice
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewCart)
        val clickListener = View.OnClickListener { view ->
            when (view) {
                binding.checkoutButton -> {
                    if(Globals.cartItemList.size == 0) {
                        Toast.makeText(view.context, "Cart is empty", Toast.LENGTH_SHORT).show()
                        return@OnClickListener
                    }
                    for (i in 0 until Globals.cartItemList.size) {
                        Globals.loyaltyPoint += Globals.cartItemList[i].coffeeItem.quantity
                    }
                    if (Globals.loyaltyPoint > 8) {
                        Globals.loyaltyPoint = 8
                    }
                    val dateFormat = SimpleDateFormat("dd MMMM | HH:mm aa")
                    val currentDate = dateFormat.format(Date())
                    Log.i("Date", currentDate)
                    for (i in 0 until Globals.cartItemList.size) {
                        var coffeeName = ""
                        when(Globals.cartItemList[i].coffeeItem.coffeeType) {
                            1 -> coffeeName = "Americano"
                            2 -> coffeeName = "Cappuccino"
                            3 -> coffeeName = "Mocha"
                            4 -> coffeeName = "Flat White"
                        }
                        var price = 0.0
                        when(Globals.cartItemList[i].coffeeItem.size) {
                            1 -> price = Globals.cartItemList[i].coffeeItem.price - 0.5
                            2 -> price = Globals.cartItemList[i].coffeeItem.price
                            3 -> price = Globals.cartItemList[i].coffeeItem.price + 0.5
                        }
                        val item = OrderList(
                            coffeeName,
                            Globals.user.address,
                            price * Globals.cartItemList[i].coffeeItem.quantity,
                            currentDate,
                        )
                        val points = (2 * item.price).toInt()
                        Globals.onGoingOrder.add(item)
                        val rewardItem = RewardItem(
                            coffeeName,
                            currentDate,
                            points
                        )
                        Globals.rewardItemHistory.add(rewardItem)
                        Globals.pointRedeeem += points
                    }
                    Globals.cartItemList.clear()
                    findNavController().navigate(R.id.action_cartFragment_to_orderSuccessFragment)
                }
                binding.returnButton -> {
                    findNavController().popBackStack()
                }
            }
        }
        binding.checkoutButton.setOnClickListener(clickListener)
        binding.returnButton.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}