package com.example.coffee_order_app.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.R
import com.example.coffee_order_app.adapter.CartAdapter
import com.example.coffee_order_app.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        binding.recyclerViewCart.layoutManager = layoutManager
        binding.recyclerViewCart.adapter = CartAdapter(arrayOf("Americano", "Cappuccino", "Flat White", "Latte"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}