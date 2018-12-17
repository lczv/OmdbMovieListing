package br.com.desafio.mobile.desafiomobile.ui.home

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.desafio.mobile.desafiomobile.R
import br.com.desafio.mobile.desafiomobile.data.datasource.ApiInstance
import br.com.desafio.mobile.desafiomobile.data.datasource.ErrorResponse
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.data.repository.MovieRemoteDataSource
import br.com.desafio.mobile.desafiomobile.ui.BaseActivity
import br.com.desafio.mobile.desafiomobile.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, HomeViewModelFactory(MovieRemoteDataSource(ApiInstance.getApi()!!)))
                .get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_home)

        setupViews()

        setupObservers()

        setupToolbar()

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadSavedMovies()
    }

    override fun setupObservers() {
        super.setupObservers()

        vfMain.displayedChild = CHILD_LOADING

        viewModel.movies.observe(this, Observer {
            vfMain.displayedChild = CHILD_LIST
            setupMovieList(it)
        })

        viewModel.errorStatus.observe(this, Observer {
            it?.let {
                if (it == ErrorResponse.NO_CONTENT_FOUND) {
                    vfMain.displayedChild = CHILD_NO_MOVIE
                } else if (it == ErrorResponse.NO_INTERNET) {
                    vfMain.displayedChild = CHILD_NO_MOVIE
                    showSnackbar(lMain, getString(R.string.please_check_internet))
                }
                viewModel.clearErrorStatus()
            }
        })
    }

    private fun setupMovieList(movies: List<Movie>) {

        rvMoviesList.adapter = MovieAdapter(movies) {
            startActivity(DetailsActivity.getStartIntent(this, it))
        }
        rvMoviesList.layoutManager = LinearLayoutManager(this)

    }

    override fun setupViews() {
        super.setupViews()

        svMovieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    vfMain.displayedChild = CHILD_LOADING
                    viewModel.searchMovie(query ?: "")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    vfMain.displayedChild = CHILD_LOADING
                    viewModel.loadSavedMovies()
                }
                return true
            }
        })
    }

    companion object {
        private const val CHILD_LIST = 0
        private const val CHILD_LOADING = 1
        private const val CHILD_NO_MOVIE = 2
    }

}