package com.example.coffee_order_app.ui.cart

import android.os.Bundle
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
//                    val bundle = Bundle()
//                    bundle.putDouble("total_price", cartViewModel.getTotalPrice())
//                    bundle.putParcelableArrayList("cart_item_list", ArrayList(Globals.cartItemList))
//                    val fragment = CheckoutFragment()
//                    fragment.arguments = bundle
//                    val transaction = parentFragmentManager.beginTransaction()
//                    transaction.replace(R.id.nav_host_fragment_content_main, fragment)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
                    for (i in 0..Globals.cartItemList.size - 1) {
                        Globals.loyaltyPoint += Globals.cartItemList.get(i).coffeeItem.quantity
                    }
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