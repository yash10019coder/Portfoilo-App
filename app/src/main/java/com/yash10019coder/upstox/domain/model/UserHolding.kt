package com.yash10019coder.upstox.domain.model

data class UserHolding(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)
