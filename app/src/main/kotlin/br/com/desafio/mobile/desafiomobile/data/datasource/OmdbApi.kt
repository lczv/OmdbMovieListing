package br.com.desafio.mobile.desafiomobile.data.datasource

import br.com.desafio.mobile.desafiomobile.data.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/")
    fun searchMovie(@Query("t") movieTitle: String,
                    @Query("plot") plot: String = "full",
                    @Query("apikey") apiKey: String): Call<Movie>

}