package com.yash10019coder.upstox.ui.holdings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import com.yash10019coder.upstox.R
import com.yash10019coder.upstox.databinding.FragmentHoldingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HoldingsFragment : Fragment() {
    private lateinit var binding: FragmentHoldingsBinding
    private lateinit var stockListAdapter: StockListAdapter
    private lateinit var viewModel: HoldingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_holdings, container, false)
        viewModel = ViewModelProvider(this).get(HoldingsViewModel::class.java)

        binding.holdingsViewModel = viewModel
        binding.lifecycleOwner = this

        stockListAdapter =
            StockListAdapter(requireContext(), mutableListOf()) {
                Timber.d("Employee selected: $it")
            }
        binding.holdingsListAdapter = stockListAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.stockList.collect {
                stockListAdapter.updateList(it)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect {
                if (it != null) {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        binding.totalHoldingsView.ivArrow.setOnClickListener {
            viewModel.setOnExpanded(!viewModel.isExpanded.value)
        }

        lifecycleScope.launch {
            viewModel.isExpanded.collect { isExpanded ->
                if (isExpanded) {
                    expandCollapse()
                    viewModel.setOnExpanded(true)
                } else {
                    expandCollapse()
                    viewModel.setOnExpanded(false)
                }
            }
        }
    }

    private fun expandCollapse() {
        if (binding.totalHoldingsView.totalHoldingsContainer.isVisible) {
            TransitionManager.beginDelayedTransition(
                binding.totalHoldingsContainer,
                AutoTransition()
            )
            binding.totalHoldingsView.totalHoldingsContainer.visibility = View.GONE
            binding.totalHoldingsView.ivArrow.setImageResource(R.drawable.ic_arrow_drop_up_24)
        } else {
            TransitionManager.beginDelayedTransition(
                binding.totalHoldingsContainer,
                AutoTransition()
            )
            binding.totalHoldingsView.totalHoldingsContainer.visibility = View.VISIBLE
            binding.totalHoldingsView.ivArrow.setImageResource(R.drawable.ic_arrow_drop_down_24)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun newInstance() = HoldingsFragment()
    }
}
