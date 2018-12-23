package br.com.desafio.mobile.desafiomobile.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.desafio.mobile.desafiomobile.data.datasource.MovieDataSource
import br.com.desafio.mobile.desafiomobile.data.datasource.RepositoryCallback
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.ui.any
import br.com.desafio.mobile.desafiomobile.ui.capture
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    @JvmField
    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieDataSource: MovieDataSource

    @Captor
    lateinit var captor: ArgumentCaptor<RepositoryCallback>

    lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(movieDataSource)
    }

    @Test
    fun setUpMovie_withSuccess() {
        // Mock
        val dummyMovie = Movie()

        // Assertion
        assertNull(detailsViewModel.movie.value)

        // Action
        detailsViewModel.setMovie(dummyMovie)

        // Assertion
        assertNotNull(detailsViewModel.movie.value)
        assertSame(detailsViewModel.movie.value, dummyMovie)
    }

    @Test
    fun saveMovie_withSuccess() {
        // Mock
        val dummyMovieA = Movie()
        detailsViewModel.setMovie(dummyMovieA)

        // Assertion
        assertEquals(dummyMovieA.stored, false)

        // Action
        detailsViewModel.saveMovie()
        verify(movieDataSource).saveMovie(any(), capture(captor))

        // Mock
        captor.value.onSuccess()

        assertEquals(dummyMovieA.stored, true)
    }

    @Test
    fun deleteMovie_withSuccess() {

        // Mock
        val dummyMovie = Movie(stored = true)
        detailsViewModel.setMovie(dummyMovie)

        // Assertion
        assertEquals(dummyMovie.stored, true)

        // Action
        detailsViewModel.deleteMovie()

        // Mock
        verify(movieDataSource).deleteMovie(any(), capture(captor))
        captor.value.onSuccess(dummyMovie)

        // Assertion
        assertEquals(dummyMovie.stored, false)
    }

}