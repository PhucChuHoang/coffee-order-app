package com.example.coffee_order_app.ui.reward

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.adapter.HistoryRewardAdapter
import com.example.coffee_order_app.databinding.FragmentRewardBinding

class RewardFragment : Fragment() {

    private var _binding: FragmentRewardBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardBinding.inflate(inflater, container, false)
        Globals.setLoyaltyCardView(Globals.loyaltyPoint, binding.loyaltyCard.root)
        val pointString = Globals.loyaltyPoint.toString() + " / 8"
        binding.loyaltyCard.loyaltyValue.text = pointString
        binding.pointTextview.text = Globals.pointRedeeem.toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        binding.historyRewardRecyclerView.layoutManager = layoutManager
        binding.historyRewardRecyclerView.adapter = HistoryRewardAdapter(Globals.rewardItemHistory)
        val clickListener = View.OnClickListener { v ->
            when(v) {
                binding.loyaltyCard.root -> {
                    if(Globals.loyaltyPoint == 8) {
                        Log.i("Loyalty Point", Globals.loyaltyPoint.toString())
                        Globals.loyaltyPoint = 0
                        Globals.setLoyaltyCardView(0, binding.root)
                        binding.loyaltyCard.loyaltyValue.text = "0 / 8"
                        Globals.pointRedeeem += 100
                        binding.pointTextview.text = Globals.pointRedeeem.toString()
                    }
                }
                binding.redeemButton -> {
                    findNavController().navigate(R.id.action_navigation_dashboard_to_redeemFragment)
                }
            }
        }
        binding.loyaltyCard.root.setOnClickListener(clickListener)
        binding.redeemButton.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}