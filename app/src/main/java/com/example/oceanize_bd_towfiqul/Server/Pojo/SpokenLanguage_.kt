package com.example.oceanize_bd_towfiqul.Server.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpokenLanguage_ {
    @SerializedName("english_name")
    @Expose
    var englishName: String? = null

    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}