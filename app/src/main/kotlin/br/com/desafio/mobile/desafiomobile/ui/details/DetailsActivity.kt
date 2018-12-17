package br.com.desafio.mobile.desafiomobile.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.desafio.mobile.desafiomobile.R
import br.com.desafio.mobile.desafiomobile.data.datasource.ApiInstance
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import br.com.desafio.mobile.desafiomobile.data.repository.MovieRemoteDataSource
import br.com.desafio.mobile.desafiomobile.ui.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this, DetailsViewModelFactory(MovieRemoteDataSource(ApiInstance.getApi()!!)))
                .get(DetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setupViews()

        setupObservers()

        setupToolbar()
    }

    override fun setupViews() {
        super.setupViews()

        viewModel.movie.observe(this, Observer {

            if (!it.poster.isEmpty()) {
                Picasso.get().load(it.poster).placeholder(R.drawable.poster_placeholder).into(ivMovieBackground)

                ivMovieBackground.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary)
                        , android.graphics.PorterDuff.Mode.DARKEN)

                Picasso.get().load(it.poster).placeholder(R.drawable.poster_placeholder).into(ivMovieCover)
            }

            tvMovieTitle.text = it.title
            tvMovieYear.text = it.year
            tvCountry.text = it.country
            tvGenre.text = it.genre
            tvLength.text = it.runtime
            tvPlot.text = it.plot
            if (!it.imdbRating.isEmpty()) {
                rbMovie.rating = ((it.imdbRating.toFloat() / 2))
            } else {
                rbMovie.rating = 0.0f
            }


            if (it.stored) {
                fbFavourite.setImageResource(R.drawable.ic_delete)
            } else {
                fbFavourite.setImageResource(R.drawable.ic_save)
            }
        })

        fbFavourite.setOnClickListener {
            if (viewModel.movie.value?.stored == false) {
                viewModel.saveMovie()
                showSnackbar(svMain, getString(R.string.movie_saved_success))
            } else {
                viewModel.deleteMovie()
                showSnackbar(svMain, getString(R.string.movie_removed_success))
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.setMovie(intent.getParcelableExtra(EXTRA_MOVIE) as Movie)
    }

    override fun setupToolbar() {
        super.setupToolbar()

        setSupportActionBar(tbMain)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val EXTRA_MOVIE = "EXTRA_MOVIE"

        fun getStartIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }
}