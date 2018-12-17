package br.com.desafio.mobile.desafiomobile.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        @SerializedName("Title") var title: String = "",
        @SerializedName("Year") var year: String = "",
        @SerializedName("Rated") var rated: String = "",
        @SerializedName("Released") var released: String = "",
        @SerializedName("Runtime") var runtime: String = "",
        @SerializedName("Genre") var genre: String = "",
        @SerializedName("Director") var director: String = "",
        @SerializedName("Writer") var writer: String = "",
        @SerializedName("Actors") var actors: String = "",
        @SerializedName("Plot") var plot: String = "",
        @SerializedName("Language") var language: String = "",
        @SerializedName("Country") var country: String = "",
        @SerializedName("Awards") var awards: String = "",
        @SerializedName("Poster") var poster: String = "",
        @SerializedName("Ratings") var ratings: List<Rating> = listOf(),
        @SerializedName("Metascore") var metascore: String = "",
        @SerializedName("imdbRating") var imdbRating: String = "",
        @SerializedName("imdbVotes") var imdbVotes: String = "",
        @SerializedName("imdbID") var imdbId: String = "",
        @SerializedName("Type") var type: String = "",
        @SerializedName("DVD") var dvd: String = "",
        @SerializedName("BoxOffice") var boxOffice: String = "",
        @SerializedName("Production") var production: String = "",
        @SerializedName("Website") var website: String = "",
        @SerializedName("Response") var response: String = "",
        var stored: Boolean = false
) : Parcelable