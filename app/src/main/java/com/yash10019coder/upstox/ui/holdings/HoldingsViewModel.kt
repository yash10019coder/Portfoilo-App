package com.yash10019coder.upstox.ui.holdings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash10019coder.upstox.data.model.NetworkResult
import com.yash10019coder.upstox.ui.databinding.StockListingItemModel
import com.yash10019coder.upstox.domain.controller.StocksController
import com.yash10019coder.upstox.mappers.UiMappers.mapStockDtoToUiModel
import com.yash10019coder.upstox.mappers.UiMappers.mapUserHoldingsDtoToStockTotalListingsModel
import com.yash10019coder.upstox.ui.databinding.StockTotalListingsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HoldingsViewModel @Inject constructor(
    private val stocksController: StocksController
) : ViewModel() {
    private val _stockList = MutableStateFlow<List<StockListingItemModel>>(emptyList())
    val stockList = _stockList.asStateFlow()

    private val _totalHoldings =
        MutableStateFlow<StockTotalListingsModel>(StockTotalListingsModel(0.0, 0.0, 0.0, 0.0))
    val totalHoldings = _totalHoldings.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isExpanded= MutableStateFlow(false)
    val isExpanded = _isExpanded.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            loadDataFromApi()
            _isLoading.value = false
        }
    }

    fun setOnExpanded(isExpanded: Boolean) {
        _isExpanded.value = isExpanded
    }

    private suspend fun generateDemiEmployeeData(): List<StockListingItemModel> {
        delay(3000L)
        val employeeList = mutableListOf<StockListingItemModel>()
        for (i in 1..10) {
            employeeList.add(StockListingItemModel("TCS", 100, 1000.0, 1000.0))
        }
        return employeeList
    }

    private suspend fun loadDemiData() {
        viewModelScope.launch {
            _isLoading.value = true
            _stockList.value = generateDemiEmployeeData()
            _isLoading.value = false
        }
    }

    private suspend fun loadDataFromApi() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = stocksController.getStocks()
            when (result) {
                is NetworkResult.Success -> {
                    _stockList.value = result.data.stocksListings
                    _totalHoldings.value = result.data.mapUserHoldingsDtoToStockTotalListingsModel()
                }

                is NetworkResult.Error -> {
                    Timber.e("Error: message is ${result.message} and code is ${result.code}")
                    _error.value = result.message
                }

                is NetworkResult.Exception -> {
                    Timber.e(result.exception, "Exception: ${result.exception}")
                    _error.value = result.exception.message
                }
            }
            _isLoading.value = false
        }
    }
}
