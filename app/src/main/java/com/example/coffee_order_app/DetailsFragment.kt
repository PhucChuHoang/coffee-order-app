package com.example.coffee_order_app

import android.os.Bundle
import android.telecom.Call.Details
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coffee_order_app.databinding.ActivityHomeBinding
import com.example.coffee_order_app.databinding.FragmentDetailsBinding
import com.example.coffee_order_app.databinding.FragmentHomeBinding
import com.example.coffee_order_app.ui.home.HomeViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val index = arguments?.getInt("index")
        println(index)
        when(index) {
            1 -> binding.detailsImage.setImageResource(R.drawable.coffee1)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heroImageView = view.findViewById<ImageView>(R.id.details_image)
        ViewCompat.setTransitionName(heroImageView, "hero_image")
    }
}