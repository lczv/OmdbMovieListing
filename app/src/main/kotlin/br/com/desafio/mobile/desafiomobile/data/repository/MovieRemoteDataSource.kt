package br.com.desafio.mobile.desafiomobile.data.repository

import br.com.desafio.mobile.desafiomobile.BuildConfig
import br.com.desafio.mobile.desafiomobile.data.datasource.*
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.DesafioMobileApplication
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MovieRemoteDataSource(private val omdbApi: OmdbApi) : MovieDataSource {

    override fun searchMovie(movieTitle: String, callback: RepositoryCallback) {
        omdbApi.searchMovie(movieTitle, BuildConfig.OMDBKEY).enqueue(
                object : Callback<Movie> {
                    override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                        if (t is NoInternetException) {
                            callback.onFailure(ErrorResponse.NO_INTERNET)
                        } else {
                            callback.onFailure(ErrorResponse.UNKNOWN_ERROR)
                        }

                    }

                    override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                        if (response?.body()?.response == "True") {
                            callback.onSuccess(response.body())
                        } else {
                            callback.onFailure(ErrorResponse.NO_CONTENT_FOUND)
                        }
                    }
                }
        )
    }

    override fun getStoredMovies(callback: RepositoryCallback) {
        if (Hawk.contains(DesafioMobileApplication.KEY_SAVED_MOVIES)) {
            val storedMovies = Hawk.get<List<Movie>>(DesafioMobileApplication.KEY_SAVED_MOVIES)

            if (storedMovies.isEmpty()) {
                callback.onFailure(ErrorResponse.NO_CONTENT_FOUND)
            } else {
                callback.onSuccess(storedMovies.reversed())
            }

        } else {
            callback.onFailure(ErrorResponse.NO_CONTENT_FOUND)
        }
    }

    override fun saveMovie(movie: Movie, callback: RepositoryCallback) {
        if (Hawk.contains(DesafioMobileApplication.KEY_SAVED_MOVIES)) {
            val storedMovies = Hawk.get<MutableList<Movie>>(DesafioMobileApplication.KEY_SAVED_MOVIES)

            val movieSearch = storedMovies.firstOrNull {
                it.title == movie.title
            }

            if (movieSearch == null) {
                storedMovies.add(movie.apply {
                    this.stored = true
                })
                Hawk.put(DesafioMobileApplication.KEY_SAVED_MOVIES, storedMovies)
            }

        } else {
            Hawk.put(DesafioMobileApplication.KEY_SAVED_MOVIES, listOf(movie))
        }

        callback.onSuccess()
    }

    override fun deleteMovie(movie: Movie, callback: RepositoryCallback) {
        if (Hawk.contains(DesafioMobileApplication.KEY_SAVED_MOVIES)) {
            val storedMovies = Hawk.get<MutableList<Movie>>(DesafioMobileApplication.KEY_SAVED_MOVIES)

            storedMovies.removeAll {
                it.title == movie.title
            }

            Hawk.put(DesafioMobileApplication.KEY_SAVED_MOVIES, storedMovies)
        }

        callback.onSuccess()
    }
}