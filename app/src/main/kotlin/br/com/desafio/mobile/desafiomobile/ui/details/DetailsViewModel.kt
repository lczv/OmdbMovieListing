package br.com.desafio.mobile.desafiomobile.ui.details

import androidx.lifecycle.MutableLiveData
import br.com.desafio.mobile.desafiomobile.data.datasource.ErrorResponse
import br.com.desafio.mobile.desafiomobile.data.datasource.MovieDataSource
import br.com.desafio.mobile.desafiomobile.data.datasource.RepositoryCallback
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.BaseViewModel

class DetailsViewModel(val movieDataSource: MovieDataSource) : BaseViewModel() {

    val movie = MutableLiveData<Movie>()

    fun saveMovie() {
        movie.value?.let {
            movieDataSource.saveMovie(it, object : RepositoryCallback {
                override fun onSuccess(result: Any?) {
                    setMovie(it.apply {
                        this.stored = true
                    })
                }

                override fun onFailure(errorResponse: ErrorResponse?) {
                }
            })
        }
    }

    fun deleteMovie() {
        movie.value?.let {
            movieDataSource.deleteMovie(it, object : RepositoryCallback {
                override fun onSuccess(result: Any?) {
                    setMovie(it.apply {
                        this.stored = false
                    })
                }

                override fun onFailure(errorResponse: ErrorResponse?) {
                }
            })
        }

    }

    fun setMovie(movie: Movie) {
        this.movie.postValue(movie)
    }
}