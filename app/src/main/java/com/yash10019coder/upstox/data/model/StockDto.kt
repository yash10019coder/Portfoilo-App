package com.yash10019coder.upstox.data.model

data class StockDto(
    val symbol: String,
    val quantity: Long,
    val lastTradedPrice: Double,
    val closePrice: Double,
    val averagePrice: Double
)
