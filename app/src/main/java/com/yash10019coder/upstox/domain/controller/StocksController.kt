package com.yash10019coder.upstox.domain.controller

import com.yash10019coder.upstox.mappers.DataMappers.mapStockResponseToDto
import com.yash10019coder.upstox.data.model.NetworkResult
import com.yash10019coder.upstox.data.repository.StocksRepo
import com.yash10019coder.upstox.domain.model.UserHoldingsDto
import com.yash10019coder.upstox.mappers.DomainMappers.mapStockDtoToUserHoldings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StocksController @Inject constructor(
    private val stocksRepo: StocksRepo,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getStocks(): NetworkResult<UserHoldingsDto> {
        return withContext(Dispatchers.IO) {
            when (val result = stocksRepo.getStocks()) {
                is NetworkResult.Error -> NetworkResult.Error(code = result.code, result.message)
                is NetworkResult.Exception -> NetworkResult.Exception(result.exception)
                is NetworkResult.Success -> {
                    NetworkResult.Success(result.data.mapStockDtoToUserHoldings(dispatcher))
                }
            }
        }
    }
}
