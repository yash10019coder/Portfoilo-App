package com.yash10019coder.upstox.domain.utils

import com.yash10019coder.upstox.domain.model.UserHolding
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
        quantity: Int
    ): Double = withContext(dispatcher) {
        lastTradedPrice * quantity
    }

    suspend fun calculateInvestmentValue(
        averagePrice: Double,
        quantity: Int
    ): Double = withContext(dispatcher) {
        averagePrice * quantity
    }

    suspend fun calculateCurrentTotalValue(
        holdings: List<UserHolding>
    ): Double = withContext(dispatcher) {
        holdings.sumOf { calculateCurrentValue(it.ltp, it.quantity) }
    }

    suspend fun calculateTotalInvestment(
        holdings: List<UserHolding>
    ): Double = withContext(dispatcher) {
        holdings.sumOf { calculateInvestmentValue(it.avgPrice, it.quantity) }
    }

    suspend fun calculateTotalPNL(
        currentTotalValue: Double,
        totalInvestment: Double
    ): Double = withContext(dispatcher) {
        currentTotalValue - totalInvestment
    }

    suspend fun calculateTodaysPNL(
        holdings: List<UserHolding>
    ): Double = withContext(dispatcher) {
        holdings.sumOf { (it.close - it.ltp) * it.quantity }
    }
}
