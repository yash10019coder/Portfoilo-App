package com.yash10019coder.upstox.domain.controller

import com.yash10019coder.upstox.data.model.NetworkResult
import com.yash10019coder.upstox.data.Mappers.mapStockResponseToDto
import com.yash10019coder.upstox.data.repository.StocksRepo
import com.yash10019coder.upstox.data.model.StockDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StocksController @Inject constructor(
    private val stocksRepo: StocksRepo,
) {
    suspend fun getStocks(): NetworkResult<List<StockDto>> {
        return withContext(Dispatchers.IO) {
            when (val result = stocksRepo.getStocks()) {
                is NetworkResult.Error -> NetworkResult.Error(code = result.code, result.message)
                is NetworkResult.Exception -> NetworkResult.Exception(result.exception)
                is NetworkResult.Success -> NetworkResult.Success(result.data.mapStockResponseToDto())
            }
        }
    }
}
