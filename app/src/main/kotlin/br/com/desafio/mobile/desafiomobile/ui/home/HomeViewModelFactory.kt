package br.com.desafio.mobile.desafiomobile.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.desafio.mobile.desafiomobile.data.datasource.MovieDataSource

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(val movieDataSource: MovieDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(movieDataSource) as T
    }
}