package com.yash10019coder.upstox.data.repository

import com.yash10019coder.upstox.data.model.NetworkResult
import com.yash10019coder.upstox.data.model.StockDto
import com.yash10019coder.upstox.data.model.StockResponse

interface StocksRepo {
    suspend fun getStocks(): NetworkResult<List<StockDto>>
}
