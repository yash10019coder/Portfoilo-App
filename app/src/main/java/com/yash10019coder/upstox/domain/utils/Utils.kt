package com.yash10019coder.upstox.domain.utils

import com.yash10019coder.upstox.data.model.StockDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Utils @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun calculateProfitAndLoss(
        lastTradedPrice: Double,
        averagePrice: Double,
        quantity: Int
    ): Double = withContext(dispatcher) {
        (lastTradedPrice - averagePrice) * quantity
    }

    suspend fun calculateCurrentValue(
        lastTradedPrice: Double,
        quantity: Long
    ): Double = withContext(dispatcher) {
        lastTradedPrice * quantity
    }

    suspend fun calculateInvestmentValue(
        averagePrice: Double,
        quantity: Long
    ): Double = withContext(dispatcher) {
        averagePrice * quantity
    }

    suspend fun calculateCurrentTotalValue(
        holdings: List<StockDto>
    ): Double = withContext(dispatcher) {
        holdings.sumOf { calculateCurrentValue(it.lastTradedPrice, it.quantity) }
    }

    suspend fun calculateTotalInvestment(
        holdings: List<StockDto>
    ): Double = withContext(dispatcher) {
        holdings.sumOf { calculateInvestmentValue(it.averagePrice, it.quantity) }
    }

    suspend fun calculateTotalPNL(
        currentTotalValue: Double,
        totalInvestment: Double
    ): Double = withContext(dispatcher) {
        currentTotalValue - totalInvestment
    }

    suspend fun calculateTodaysPNL(
        holdings: List<StockDto>
    ): Double = withContext(dispatcher) {
        holdings.sumOf { (it.closePrice - it.lastTradedPrice) * it.quantity }
    }
}
