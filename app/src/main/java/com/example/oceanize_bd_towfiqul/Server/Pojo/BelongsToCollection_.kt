package com.example.oceanize_bd_towfiqul.Server.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BelongsToCollection_ {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
}