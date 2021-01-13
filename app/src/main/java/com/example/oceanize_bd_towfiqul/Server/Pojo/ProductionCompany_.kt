package com.example.oceanize_bd_towfiqul.Server.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCompany_ {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null
}