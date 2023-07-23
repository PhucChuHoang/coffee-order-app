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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailsViewModel =
            ViewModelProvider(this)[DetailsViewModel::class.java]

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        index = arguments?.getInt("intKey", 1)
        println(index)
        when(index) {
            1 -> binding.detailsImage.setImageResource(R.drawable.coffee1)
            2 -> binding.detailsImage.setImageResource(R.drawable.coffee2)
            3 -> binding.detailsImage.setImageResource(R.drawable.coffee3)
            4 -> binding.detailsImage.setImageResource(R.drawable.coffee4)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heroImageView = view.findViewById<ImageView>(R.id.details_image)
        ViewCompat.setTransitionName(heroImageView, "hero_image")
        val clickListener = View.OnClickListener { v ->
            when(v) {
                binding.increaseQuantity -> {
                    binding.numberOfCoffee.text = (binding.numberOfCoffee.text.toString().toInt() + 1).toString()
                }
                binding.decreaseQuantity -> {
                    if (binding.numberOfCoffee.text.toString().toInt() > 1) {
                        binding.numberOfCoffee.text = (binding.numberOfCoffee.text.toString().toInt() - 1).toString()
                    }
                }
                binding.singleShotBtn -> {
                    setShot(1)
                }
                binding.doubleShotBtn -> {
                    setShot(2)
                }
                binding.dineIn -> {
                    setDineInTakeAway(1)
                }
                binding.takeAway -> {
                    setDineInTakeAway(2)
                }
                binding.coffeeSizeSmall -> {
                    setCupSize(1)
                }
                binding.coffeeSizeMedium -> {
                    setCupSize(2)
                }
                binding.coffeeSizeLarge -> {
                    setCupSize(3)
                }
                binding.coffeeIceSmall -> {
                    setIceButtonColor(1)
                }
                binding.coffeeIceMedium -> {
                    setIceButtonColor(2)
                }
                binding.coffeeIceLarge -> {
                    setIceButtonColor(3)
                }
            }
        }
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