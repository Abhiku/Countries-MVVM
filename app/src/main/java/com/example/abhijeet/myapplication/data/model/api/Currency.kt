package com.example.abhijeet.myapplication.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Currency {

    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("symbol")
    @Expose
    var symbol: String? = null

}