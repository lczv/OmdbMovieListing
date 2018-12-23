package br.com.desafio.mobile.desafiomobile.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.desafio.mobile.desafiomobile.data.datasource.ErrorResponse
import br.com.desafio.mobile.desafiomobile.data.datasource.MovieDataSource
import br.com.desafio.mobile.desafiomobile.data.datasource.RepositoryCallback
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.capture
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @JvmField
    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDataSource: MovieDataSource

    private lateinit var homeViewModel: HomeViewModel

    @Captor
    lateinit var localMoviesCaptor: ArgumentCaptor<RepositoryCallback>

    @Captor
    lateinit var remoteMoviesCaptor: ArgumentCaptor<RepositoryCallback>

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(movieDataSource)
    }

    @Test
    fun loadStoredMovies_withSuccess() {
        // Mock
        val dummyMovie = Movie()

        // Actuion
        homeViewModel.loadSavedMovies()

        // Assertion
        verify(movieDataSource).getStoredMovies(capture(localMoviesCaptor))

        // Action
        localMoviesCaptor.value.onSuccess(listOf(dummyMovie))

        // Assertion
        Assert.assertEquals(homeViewModel.movies.value?.get(0), dummyMovie)
    }

    @Test
    fun searchMovies_withSuccess() {
        // Mock
        val dummyMovie = Movie()

        // Action
        homeViewModel.searchMovie("")

        // Assertion
        verify(movieDataSource).searchMovie(ArgumentMatchers.anyString(), capture(localMoviesCaptor))

        // Action
        localMoviesCaptor.value.onSuccess(dummyMovie)

        // Assertion
        verify(movieDataSource).getStoredMovies(capture(remoteMoviesCaptor))

        // Mock
        remoteMoviesCaptor.value.onSuccess(listOf(dummyMovie))

        // Assertion
        Assert.assertEquals(homeViewModel.movies.value?.get(0), dummyMovie)
    }

    @Test
    fun loadStoredMovies_withFailure() {
        // Action
        homeViewModel.loadSavedMovies()
        verify(movieDataSource).getStoredMovies(capture(localMoviesCaptor))

        // Mock
        localMoviesCaptor.value.onFailure(ErrorResponse.UNKNOWN_ERROR)

        // Assertion
        Assert.assertEquals(homeViewModel.errorStatus.value, ErrorResponse.UNKNOWN_ERROR)
    }

    @Test
    fun searchMovies_withFailure() {
        // Action
        homeViewModel.searchMovie("")
        verify(movieDataSource).searchMovie(ArgumentMatchers.anyString(), capture(localMoviesCaptor))

        // Mock
        localMoviesCaptor.value.onFailure(ErrorResponse.UNKNOWN_ERROR)

        // Assertion
        Assert.assertEquals(homeViewModel.errorStatus.value, ErrorResponse.UNKNOWN_ERROR)
    }
}