package br.com.desafio.mobile.desafiomobile.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
        @SerializedName("Source") var source: String  = "",
        @SerializedName("Value") var value: String  = ""
) : Parcelable