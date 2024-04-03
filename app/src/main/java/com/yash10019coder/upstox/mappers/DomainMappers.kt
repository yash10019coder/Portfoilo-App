package com.yash10019coder.upstox.mappers

import com.yash10019coder.upstox.data.model.StockDto
import com.yash10019coder.upstox.data.model.StockResponse
import com.yash10019coder.upstox.ui.databinding.StockListingItemModel
import com.yash10019coder.upstox.domain.model.UserHoldingsDto
import com.yash10019coder.upstox.domain.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher

object DomainMappers {
    fun StockDto.mapStockDtoToStockListingsItemModel(): StockListingItemModel {
        val stockDto = this
        return StockListingItemModel(
            symbol = stockDto.symbol,
            quantity = stockDto.quantity,
            lastTradedPrice = stockDto.lastTradedPrice,
            profitAndLoss = (stockDto.closePrice - stockDto.averagePrice) * stockDto.quantity
        )
    }

    suspend fun List<StockDto>.mapStockDtoToUserHoldings(dispatcher: CoroutineDispatcher): UserHoldingsDto {
        val list = this
        val listStockItemModels = list.map { it.mapStockDtoToStockListingsItemModel() }
        val totalInvestment= Utils(dispatcher).calculateTotalInvestment(list)
        val currentValue = Utils(dispatcher).calculateCurrentTotalValue(list)
        val todayProfitAndLoss = Utils(dispatcher).calculateTodaysPNL(list)
        val profitAndLoss = Utils(dispatcher).calculateTotalPNL(currentValue, totalInvestment)
        return UserHoldingsDto(
            stocksListings = listStockItemModels,
            currentValue = currentValue,
            totalInvestment = totalInvestment,
            todayProfitAndLoss = todayProfitAndLoss,
            profitAndLoss = profitAndLoss
        )
    }
}
