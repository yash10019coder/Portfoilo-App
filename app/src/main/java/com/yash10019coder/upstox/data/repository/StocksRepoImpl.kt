package com.yash10019coder.upstox.data.repository

import com.yash10019coder.upstox.data.api.StockService
import com.yash10019coder.upstox.data.model.NetworkResult
import com.yash10019coder.upstox.data.model.StockDto
import com.yash10019coder.upstox.data.model.StockResponse
import com.yash10019coder.upstox.data.model.handleApi
import com.yash10019coder.upstox.mappers.DataMappers.mapStockResponseToDto
import javax.inject.Inject

class StocksRepoImpl @Inject constructor(
    private val stockService: StockService
) : StocksRepo {
    override suspend fun getStocks(): NetworkResult<List<StockDto>> {
        val result = handleApi { stockService.getStocks() }
        return when (result) {
            is NetworkResult.Error -> NetworkResult.Error(code = result.code, result.message)
            is NetworkResult.Exception -> NetworkResult.Exception(result.exception)
            is NetworkResult.Success -> {
                NetworkResult.Success(result.data.mapStockResponseToDto())
            }
        }
    }
}
