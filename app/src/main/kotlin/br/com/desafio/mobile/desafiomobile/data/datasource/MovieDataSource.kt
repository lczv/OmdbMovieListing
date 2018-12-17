package br.com.desafio.mobile.desafiomobile.data.datasource

import br.com.desafio.mobile.desafiomobile.data.model.Movie

interface MovieDataSource {
    fun searchMovie(movieTitle: String, callback: RepositoryCallback)
    fun getStoredMovies(callback: RepositoryCallback)
    fun saveMovie(movie: Movie, callback: RepositoryCallback)
    fun deleteMovie(movie: Movie, callback: RepositoryCallback)
}