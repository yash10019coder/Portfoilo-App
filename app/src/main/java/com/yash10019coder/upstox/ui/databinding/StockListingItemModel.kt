package com.yash10019coder.upstox.ui.databinding

data class StockListingItemModel(
    val symbol: String,
    val quantity: Long,
    val lastTradedPrice: Double,
    val profitAndLoss: Double,
)
