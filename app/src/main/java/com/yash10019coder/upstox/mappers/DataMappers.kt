package com.yash10019coder.upstox.mappers

import com.yash10019coder.upstox.data.model.StockDto
import com.yash10019coder.upstox.data.model.StockResponse

object DataMappers {
    fun StockResponse.mapStockResponseToDto(): List<StockDto> {
        val response: StockResponse = this
        return response.data.userHolding.map {
            StockDto(
                symbol = it.symbol,
                quantity = it.quantity.toLong(),
                lastTradedPrice = it.ltp,
                averagePrice = it.avgPrice,
                closePrice = it.close
            )
        }
    }
}
