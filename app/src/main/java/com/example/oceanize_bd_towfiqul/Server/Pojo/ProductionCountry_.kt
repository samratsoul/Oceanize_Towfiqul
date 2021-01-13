package com.example.oceanize_bd_towfiqul.Server.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCountry_ {

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}