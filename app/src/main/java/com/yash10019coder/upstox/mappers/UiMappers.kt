package com.yash10019coder.upstox.mappers

import com.yash10019coder.upstox.data.model.StockDto
import com.yash10019coder.upstox.ui.databinding.StockListingItemModel
import com.yash10019coder.upstox.domain.model.UserHoldingsDto
import com.yash10019coder.upstox.ui.databinding.StockTotalListingsModel

object UiMappers {
    fun List<StockDto>.mapStockDtoToUiModel(): List<StockListingItemModel> {
        val list = this
        return list.map {
            StockListingItemModel(
                symbol = it.symbol,
                quantity = it.quantity,
                lastTradedPrice = it.lastTradedPrice,
                profitAndLoss = it.averagePrice
            )
        }
    }

    fun UserHoldingsDto.mapUserHoldingsDtoToStockTotalListingsModel(): StockTotalListingsModel {
        val userHoldingsDto = this
        return StockTotalListingsModel(
            totalInvestment = userHoldingsDto.totalInvestment,
            todayProfitAndLoss = userHoldingsDto.todayProfitAndLoss,
            totalCurrentValue = userHoldingsDto.currentValue,
            profitAndLoss = userHoldingsDto.profitAndLoss
        )
    }
}
