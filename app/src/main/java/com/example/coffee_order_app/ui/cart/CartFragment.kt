package com.example.coffee_order_app.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.adapter.CartAdapter
import com.example.coffee_order_app.databinding.FragmentCartBinding
import com.example.coffee_order_app.ui.details.CoffeeItem
import com.example.coffee_order_app.ui.order.OrderList
import com.example.coffee_order_app.ui.reward.RewardItem
import java.text.SimpleDateFormat
import java.util.Date

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null

    private val binding get() = _binding!!

    private lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val coffeeItem= CoffeeItem(
            arguments?.getInt("type_coffee") ?: 0,
            arguments?.getInt("quantity") ?: 0,
            arguments?.getInt("shot") ?: 1,
            arguments?.getBoolean("hot_cold") ?: false,
            arguments?.getInt("size") ?: 1,
            arguments?.getInt("ice") ?: 1,
            arguments?.getDouble("price") ?: 0.0
        )
        val totalPrice: Double = arguments?.getDouble("total_price")?: 0.0
        if (coffeeItem.coffeeType != 0) {
            cartViewModel.addCartItem(CartItem(coffeeItem, totalPrice))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        val totalPrice = "$" + String.format("%.2f", cartViewModel.getTotalPrice())
        binding.totalPrice.text = totalPrice
        binding.recyclerViewCart.layoutManager = layoutManager
        binding.recyclerViewCart.adapter = CartAdapter(Globals.cartItemList)
        val clickListener = View.OnClickListener { view ->
            when (view) {
                binding.checkoutButton -> {
                    for (i in 0..Globals.cartItemList.size - 1) {
                        Globals.loyaltyPoint += Globals.cartItemList.get(i).coffeeItem.quantity
                    }
                    if (Globals.loyaltyPoint > 8) {
                        Globals.loyaltyPoint = 8
                    }
                    val dateFormat = SimpleDateFormat("dd MMMM | HH:mm aa")
                    val currentDate = dateFormat.format(Date())
                    Log.i("Date", currentDate)
                    for (i in 0..Globals.cartItemList.size - 1) {
                        var coffeeName = ""
                        when(Globals.cartItemList.get(i).coffeeItem.coffeeType) {
                            1 -> coffeeName = "Americano"
                            2 -> coffeeName = "Cappuccino"
                            3 -> coffeeName = "Mocha"
                            4 -> coffeeName = "Flat White"
                        }
                        var price = 0.0
                        when(Globals.cartItemList.get(i).coffeeItem.size) {
                            1 -> price = Globals.cartItemList.get(i).coffeeItem.price - 0.5
                            2 -> price = Globals.cartItemList.get(i).coffeeItem.price
                            3 -> price = Globals.cartItemList.get(i).coffeeItem.price + 0.5
                        }
                        val item = OrderList(
                            coffeeName,
                            Globals.user.address,
                            price * Globals.cartItemList.get(i).coffeeItem.quantity,
                            currentDate,
                        )
                        val points = (2 * item.price).toInt()
                        Globals.onGoingOrder.add(item)
                        var rewardItem = RewardItem(
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