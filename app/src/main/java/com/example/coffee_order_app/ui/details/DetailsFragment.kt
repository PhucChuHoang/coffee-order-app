package com.example.coffee_order_app.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.databinding.FragmentDetailsBinding

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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val root: View = binding.root
        index = arguments?.getInt("intKey", 0)
        when(index) {
            1 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee1)
                Globals.coffeeItem.coffeeType = 1
                Globals.coffeeItem.price = 3.50
                binding.totalAmountTextview.setText("$3.50")
            }
            2 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee2)
                Globals.coffeeItem.coffeeType = 2
                Globals.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
            3 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee3)
                Globals.coffeeItem.coffeeType = 3
                Globals.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
            4 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee4)
                Globals.coffeeItem.coffeeType = 4
                Globals.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
        }
        setViewAccordingToGlobal()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heroImageView = view.findViewById<ImageView>(R.id.details_image)
        ViewCompat.setTransitionName(heroImageView, "hero_image")
        if (Globals.coffeeItem.coffeeType == 1) {
            binding.coffeeName.text = "Americano"
        }
        else if (Globals.coffeeItem.coffeeType == 2) {
            binding.coffeeName.text = "Cappuccino"
        }
        else if (Globals.coffeeItem.coffeeType == 3) {
            binding.coffeeName.text = "Mocha"
        }
        else if (Globals.coffeeItem.coffeeType == 4) {
            binding.coffeeName.text = "Flat White"
        }
        val clickListener = View.OnClickListener { v ->
            when(v) {
                binding.increaseQuantity -> {
                    binding.numberOfCoffee.text = (binding.numberOfCoffee.text.toString().toInt() + 1).toString()
                    Globals.coffeeItem.quantity = binding.numberOfCoffee.text.toString().toInt()
                }
                binding.decreaseQuantity -> {
                    if (binding.numberOfCoffee.text.toString().toInt() > 1) {
                        binding.numberOfCoffee.text = (binding.numberOfCoffee.text.toString().toInt() - 1).toString()
                        Globals.coffeeItem.quantity = binding.numberOfCoffee.text.toString().toInt()
                    }
                }
                binding.singleShotBtn -> {
                    setShot(1)
                    Globals.coffeeItem.shot = 1
                }
                binding.doubleShotBtn -> {
                    setShot(2)
                    Globals.coffeeItem.shot = 2
                }
                binding.hotCoffee -> {
                    setDineInTakeAway(1)
                    Globals.coffeeItem.hotOrCold = true
                    setIceButtonColor(1)
                    Globals.coffeeItem.ice = 1
                }
                binding.coldCoffee -> {
                    setDineInTakeAway(2)
                    Globals.coffeeItem.hotOrCold = false
                }
                binding.coffeeSizeSmall -> {
                    setCupSize(1)
                    Globals.coffeeItem.size = 1
                }
                binding.coffeeSizeMedium -> {
                    setCupSize(2)
                    Globals.coffeeItem.size = 2
                }
                binding.coffeeSizeLarge -> {
                    setCupSize(3)
                    Globals.coffeeItem.size = 3
                }
                binding.coffeeIceSmall -> {
                    setIceButtonColor(1)
                    Globals.coffeeItem.ice = 1
                }
                binding.coffeeIceMedium -> {
                    if (!Globals.coffeeItem.hotOrCold) {
                        setIceButtonColor(2)
                        Globals.coffeeItem.ice = 2
                    }
                }
                binding.coffeeIceLarge -> {
                    if (!Globals.coffeeItem.hotOrCold) {
                        setIceButtonColor(3)
                        Globals.coffeeItem.ice = 3
                    }
                }
                binding.addToCartBtn -> {
                    val bundle = Bundle().apply {
                        putInt("type_coffee", Globals.coffeeItem.coffeeType)
                        putInt("quantity", Globals.coffeeItem.quantity)
                        putInt("shot", Globals.coffeeItem.shot)
                        putInt("size", Globals.coffeeItem.size)
                        putInt("ice", Globals.coffeeItem.ice)
                        putBoolean("hot_cold", Globals.coffeeItem.hotOrCold)
                        putDouble("total_price", binding.totalAmountTextview.text.toString().replace(Regex("[^0-9.]"), "").toDouble())
                    }
                    Globals.coffeeItem = CoffeeItem(0, 1, 1, false, 2, 3, 3.00)
                    findNavController().navigate(R.id.action_detailsFragment_to_cartFragment, bundle)
                }
                binding.cartButton -> {
                    findNavController().navigate(R.id.action_detailsFragment_to_cartFragment)
                }
                binding.backButtonDetails -> {
                    val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

                    // Get the back stack entry count
                    val backStackEntryCount = fragmentManager.backStackEntryCount
                    Log.d("DetailsFragment", "BackStackEntryCount: $backStackEntryCount")
                    Globals.coffeeItem = CoffeeItem(0, 1, 1, false, 2, 3, 3.00)
                    findNavController().navigate(R.id.action_detailsFragment_to_navigation_home)
                }
            }
            if(Globals.coffeeItem.size == 1) {
                val totalPrice = (Globals.coffeeItem.price - 0.50) * Globals.coffeeItem.quantity
                val formattedTotal = String.format("%.2f", totalPrice)
                binding.totalAmountTextview.text = "$" + formattedTotal
            }
            else if(Globals.coffeeItem.size == 2) {
                val totalPrice = Globals.coffeeItem.price * Globals.coffeeItem.quantity
                val formattedTotal = String.format("%.2f", totalPrice)
                binding.totalAmountTextview.text = "$" + formattedTotal
            }
            else if(Globals.coffeeItem.size == 3) {
                val totalPrice = (Globals.coffeeItem.price + 0.50) * Globals.coffeeItem.quantity
                val formattedTotal = String.format("%.2f", totalPrice)
                binding.totalAmountTextview.text = "$" + formattedTotal
            }
        }
        binding.addToCartBtn.setOnClickListener(clickListener)
        binding.increaseQuantity.setOnClickListener(clickListener)
        binding.decreaseQuantity.setOnClickListener(clickListener)
        binding.hotCoffee.setOnClickListener(clickListener)
        binding.coldCoffee.setOnClickListener(clickListener)
        binding.coffeeIceSmall.setOnClickListener(clickListener)
        binding.coffeeIceMedium.setOnClickListener(clickListener)
        binding.coffeeIceLarge.setOnClickListener(clickListener)
        binding.coffeeSizeSmall.setOnClickListener(clickListener)
        binding.coffeeSizeMedium.setOnClickListener(clickListener)
        binding.coffeeSizeLarge.setOnClickListener(clickListener)
        binding.singleShotBtn.setOnClickListener(clickListener)
        binding.doubleShotBtn.setOnClickListener(clickListener)
        binding.cartButton.setOnClickListener(clickListener)
        binding.backButtonDetails.setOnClickListener(clickListener)
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
                1 -> binding.hotCoffee
                else -> binding.coldCoffee
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

    private fun setViewAccordingToGlobal() {
        when(Globals.coffeeItem.coffeeType) {
            1 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee1)
                binding.coffeeName.text = "Americano"
                Globals.coffeeItem.price = 3.50
                binding.totalAmountTextview.setText("$3.50")
            }
            2 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee2)
                binding.coffeeName.text = "Cappuccino"
                Globals.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
            3 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee3)
                binding.coffeeName.text = "Mocha"
                Globals.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
            4 -> {
                binding.detailsImage.setImageResource(R.drawable.coffee4)
                binding.coffeeName.text = "Flat White"
                Globals.coffeeItem.price = 4.00
                binding.totalAmountTextview.setText("$4.00")
            }
        }
        binding.numberOfCoffee.text = Globals.coffeeItem.quantity.toString()
        setShot(Globals.coffeeItem.shot)
        setDineInTakeAway(if (Globals.coffeeItem.hotOrCold) 1 else 2)
        setCupSize(Globals.coffeeItem.size)
        setIceButtonColor(Globals.coffeeItem.ice)
    }
}