package com.yash10019coder.upstox.data.repository

import com.yash10019coder.upstox.data.api.StockService
import com.yash10019coder.upstox.data.model.NetworkResult
import com.yash10019coder.upstox.data.model.StockResponse
import com.yash10019coder.upstox.data.model.handleApi
import javax.inject.Inject

class StocksRepoImpl @Inject constructor(
    private val stockService: StockService
    private val applicationContext: Context
) : StocksRepo {
    override suspend fun getStocks(): NetworkResult<StockResponse> {
        return handleApi { stockService.getStocks() }
    }

    override suspend fun getStockDemi(): NetworkResult<StockResponse> {
        return NetworkResult.Success(StockResponse())
    }

    private fun getDemiStocksResponse(): StockResponse {
        val
    }
}