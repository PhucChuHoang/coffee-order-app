package com.example.coffee_order_app.ui.reward

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee_order_app.adapter.HistoryRewardAdapter
import com.example.coffee_order_app.databinding.FragmentRewardBinding

class RewardFragment : Fragment() {

    private var _binding: FragmentRewardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rewardViewModel =
            ViewModelProvider(this).get(RewardViewModel::class.java)

        _binding = FragmentRewardBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textDashboard
//        rewardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        binding.historyRewardRecyclerView.layoutManager = layoutManager
        binding.historyRewardRecyclerView.adapter = HistoryRewardAdapter(arrayOf("Americano", "Cappuccino", "Flat White", "Latte"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}