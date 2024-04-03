package com.yash10019coder.upstox.mappers

import com.yash10019coder.upstox.data.model.StockDto
import com.yash10019coder.upstox.databinding.StockListingItemModel
import com.yash10019coder.upstox.domain.model.UserHoldingsDto

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
}
