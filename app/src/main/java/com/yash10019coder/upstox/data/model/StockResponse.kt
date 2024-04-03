package com.yash10019coder.upstox.data.model


import com.google.gson.annotations.SerializedName

data class StockResponse(
    @SerializedName("data")
    var `data`: Data
) {
    data class Data(
        @SerializedName("userHolding")
        var userHolding: List<UserHolding>
    ) {
        data class UserHolding(
            @SerializedName("avgPrice")
            var avgPrice: Double,
            @SerializedName("close")
            var close: Double,
            @SerializedName("ltp")
            var ltp: Double,
            @SerializedName("quantity")
            var quantity: Int,
            @SerializedName("symbol")
            var symbol: String
        )
    }
}
