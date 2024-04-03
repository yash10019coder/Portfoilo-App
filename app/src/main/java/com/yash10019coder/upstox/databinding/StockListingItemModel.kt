package com.yash10019coder.upstox.databinding

data class StockListingItemModel(
    val symbol: String,
    val quantity: Long,
    val lastTradedPrice: Double,
    val profitAndLoss: Double,
)
