package com.yash10019coder.upstox.data

import com.yash10019coder.upstox.data.model.StockDto
import com.yash10019coder.upstox.data.model.StockResponse

object Mappers {
    fun StockResponse.mapStockResponseToDto(): List<StockDto> {
        val response: StockResponse = this
        return response.data.userHolding.map {
            StockDto(
                symbol = it.symbol,
                quantity = it.quantity.toLong(),
                lastTradedPrice = it.ltp,
                //TODO: Calculate profit and loss later in domain
                profitAndLoss = it.ltp
            )
        }
    }
}
