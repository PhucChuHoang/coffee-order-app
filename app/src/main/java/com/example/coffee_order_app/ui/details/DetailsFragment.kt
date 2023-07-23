package com.example.coffee_order_app.ui.details

import android.content.res.ColorStateList
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.example.coffee_order_app.R
import com.example.coffee_order_app.databinding.FragmentDetailsBinding
import com.example.coffee_order_app.ui.home.HomeViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var index: Int? = null

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val root: View = binding.root
        index = arguments?.getInt("intKey", 1)
        when(index) {
            1 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee1)
                viewModel.coffeeItem.coffeeType = 1
                viewModel.coffeeItem.price = 3.50
                binding.totalAmountTextview.setText("$3.50")
            }
            2 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee2)
                viewModel.coffeeItem.coffeeType = 2
                viewModel.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
            3 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee3)
                viewModel.coffeeItem.coffeeType = 3
                viewModel.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
            4 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee4)
                viewModel.coffeeItem.coffeeType = 4
                viewModel.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heroImageView = view.findViewById<ImageView>(R.id.details_image)
        ViewCompat.setTransitionName(heroImageView, "hero_image")
        val coffeeItem = viewModel.coffeeItem
        if (coffeeItem.coffeeType == 1) {
            binding.coffeeName.text = "Americano"
        }
        else if (coffeeItem.coffeeType == 2) {
            binding.coffeeName.text = "Cappuccino"
        }
        else if (coffeeItem.coffeeType == 3) {
            binding.coffeeName.text = "Mocha"
        }
        else if (coffeeItem.coffeeType == 4) {
            binding.coffeeName.text = "Flat White"
        }
        val clickListener = View.OnClickListener { v ->
            when(v) {
                binding.increaseQuantity -> {
                    binding.numberOfCoffee.text = (binding.numberOfCoffee.text.toString().toInt() + 1).toString()
                    coffeeItem.quantity = binding.numberOfCoffee.text.toString().toInt()
                }
                binding.decreaseQuantity -> {
                    if (binding.numberOfCoffee.text.toString().toInt() > 1) {
                        binding.numberOfCoffee.text = (binding.numberOfCoffee.text.toString().toInt() - 1).toString()
                        coffeeItem.quantity = binding.numberOfCoffee.text.toString().toInt()
                    }
                }
                binding.singleShotBtn -> {
                    setShot(1)
                    coffeeItem.shot = 1
                }
                binding.doubleShotBtn -> {
                    setShot(2)
                    coffeeItem.shot = 2
                }
                binding.dineIn -> {
                    setDineInTakeAway(1)
                    coffeeItem.dineInOrTakeAway = true
                }
                binding.takeAway -> {
                    setDineInTakeAway(2)
                    coffeeItem.dineInOrTakeAway = false
                }
                binding.coffeeSizeSmall -> {
                    setCupSize(1)
                    coffeeItem.size = 1
                }
                binding.coffeeSizeMedium -> {
                    setCupSize(2)
                    coffeeItem.size = 2
                }
                binding.coffeeSizeLarge -> {
                    setCupSize(3)
                    coffeeItem.size = 3
                }
                binding.coffeeIceSmall -> {
                    setIceButtonColor(1)
                    coffeeItem.ice = 1
                }
                binding.coffeeIceMedium -> {
                    setIceButtonColor(2)
                    coffeeItem.ice = 2
                }
                binding.coffeeIceLarge -> {
                    setIceButtonColor(3)
                    coffeeItem.ice = 3
                }
                binding.addToCartBtn -> {

                }
            }
            if(coffeeItem.size == 1) {
                val totalPrice = (coffeeItem.price - 0.50) * coffeeItem.quantity
                val formattedTotal = String.format("%.2f", totalPrice)
                binding.totalAmountTextview.text = "$" + formattedTotal
            }
            else if(coffeeItem.size == 2) {
                val totalPrice = coffeeItem.price * coffeeItem.quantity
                val formattedTotal = String.format("%.2f", totalPrice)
                binding.totalAmountTextview.text = "$" + formattedTotal
            }
            else if(coffeeItem.size == 3) {
                val totalPrice = (coffeeItem.price + 0.50) * coffeeItem.quantity
                val formattedTotal = String.format("%.2f", totalPrice)
                binding.totalAmountTextview.text = "$" + formattedTotal
            }
        }
        binding.addToCartBtn.setOnClickListener(clickListener)
        binding.increaseQuantity.setOnClickListener(clickListener)
        binding.decreaseQuantity.setOnClickListener(clickListener)
        binding.dineIn.setOnClickListener(clickListener)
        binding.takeAway.setOnClickListener(clickListener)
        binding.coffeeIceSmall.setOnClickListener(clickListener)
        binding.coffeeIceMedium.setOnClickListener(clickListener)
        binding.coffeeIceLarge.setOnClickListener(clickListener)
        binding.coffeeSizeSmall.setOnClickListener(clickListener)
        binding.coffeeSizeMedium.setOnClickListener(clickListener)
        binding.coffeeSizeLarge.setOnClickListener(clickListener)
        binding.singleShotBtn.setOnClickListener(clickListener)
        binding.doubleShotBtn.setOnClickListener(clickListener)
    }

    private fun setShot(index: Int) {
        for (i in 1..2) {
            val button = when(i) {
                1 -> binding.singleShotBtn
                else -> binding.doubleShotBtn
            }
            if (i == index) {
//              button.backgroundTintList = getColorStateList(requireContext(), R.color.color_324A59)
                button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            } else {
//              button.backgroundTintList = getColorStateList(requireContext(), R.color.white)
                button.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_001833))
            }
        }
    }
    private fun setCupSize(index: Int) {
        for (i in 1..3) {
            val button = when(i) {
                1 -> binding.coffeeSizeSmall
                2 -> binding.coffeeSizeMedium
                else -> binding.coffeeSizeLarge
            }
            if (i == index) {
                button.backgroundTintList = getColorStateList(requireContext(), R.color.black)
            } else {
                button.backgroundTintList = getColorStateList(requireContext(), R.color.color_d8d8d8)
            }
        }
    }

    private fun setDineInTakeAway(index: Int) {
        for (i in 1..2) {
            val button = when(i) {
                1 -> binding.dineIn
                else -> binding.takeAway
            }
            if (i == index) {
                button.backgroundTintList = getColorStateList(requireContext(), R.color.black)
            } else {
                button.backgroundTintList = getColorStateList(requireContext(), R.color.color_d8d8d8)
            }
        }
    }
    private fun setIceButtonColor(index: Int) {
        for (i in 1..3) {
            val button = when(i) {
                1 -> binding.coffeeIceSmall
                2 -> binding.coffeeIceMedium
                else -> binding.coffeeIceLarge
            }
            if (i == index) {
                button.backgroundTintList = getColorStateList(requireContext(), R.color.black)
            } else {
                button.backgroundTintList = getColorStateList(requireContext(), R.color.color_d8d8d8)
            }
        }
    }
}