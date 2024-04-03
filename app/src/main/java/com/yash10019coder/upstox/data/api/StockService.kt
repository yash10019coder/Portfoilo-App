package com.yash10019coder.upstox.data.api

import com.yash10019coder.upstox.data.model.StockResponse
import retrofit2.Response
import retrofit2.http.GET

interface StockService {

    @GET("/")
    suspend fun getStocks(): Response<StockResponse>
}
