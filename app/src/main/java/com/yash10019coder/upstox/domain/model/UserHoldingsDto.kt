package com.yash10019coder.upstox.domain.model

import com.yash10019coder.upstox.databinding.StockListingItemModel

data class UserHoldingsDto(
    val stocksListings: List<StockListingItemModel>,
    val currentValue: Double,
    val totalInvestment: Double,
    val todayProfitAndLoss: Double,
    val profitAndLoss: Double
)
