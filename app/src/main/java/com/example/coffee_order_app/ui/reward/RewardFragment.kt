package com.example.coffee_order_app.ui.reward

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.adapter.HistoryRewardAdapter
import com.example.coffee_order_app.adapter.OrderAdapter
import com.example.coffee_order_app.databinding.FragmentRewardBinding

class RewardFragment : Fragment() {

    private var _binding: FragmentRewardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var adapter: HistoryRewardAdapter? = null

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
                    }
                }
            }
        }
        binding.loyaltyCard.root.setOnClickListener(clickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}