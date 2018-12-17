package br.com.desafio.mobile.desafiomobile.ui.home

import androidx.lifecycle.MutableLiveData
import br.com.desafio.mobile.desafiomobile.data.datasource.ErrorResponse
import br.com.desafio.mobile.desafiomobile.data.datasource.MovieDataSource
import br.com.desafio.mobile.desafiomobile.data.datasource.RepositoryCallback
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.BaseViewModel

@Suppress("UNCHECKED_CAST")
class HomeViewModel(val movieDataSource: MovieDataSource) : BaseViewModel() {

    val movies = MutableLiveData<List<Movie>>()

    // Returns a list of all stored movies
    fun loadSavedMovies() {

        movieDataSource.getStoredMovies(object : RepositoryCallback {
            override fun onSuccess(result: Any?) {
                val storedMovies = result as List<Movie>
                movies.postValue(storedMovies)
            }

            override fun onFailure(errorResponse: ErrorResponse?) {
                errorStatus.postValue(errorResponse)
            }
        })
    }

    fun searchMovie(movieTitle: String) {

        var movie: Movie?

        // Verify if this movie already exists
        movieDataSource.searchMovie(movieTitle, object : RepositoryCallback {
            override fun onSuccess(result: Any?) {
                movie = result as Movie

                movieDataSource.getStoredMovies(object : RepositoryCallback {
                    override fun onSuccess(result: Any?) {
                        val storedMovies = result as List<Movie>
                        storedMovies.forEach {
                            if (it.title.toLowerCase() == movie?.title?.toLowerCase()) {
                                movie = it
                            }
                        }
                    }

                    override fun onFailure(errorResponse: ErrorResponse?) {
                        errorStatus.postValue(errorResponse)
                    }
                })

                movies.postValue(listOf(movie!!))
            }

            override fun onFailure(errorResponse: ErrorResponse?) {
                errorStatus.postValue(errorResponse)
            }
        })


    }

}