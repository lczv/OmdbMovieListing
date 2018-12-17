package br.com.desafio.mobile.desafiomobile.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.desafio.mobile.desafiomobile.R
import br.com.desafio.mobile.desafiomobile.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: List<Movie>, val onClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {

            with(itemView) {
                setOnClickListener {
                    onClick(movie)
                }

                if(!movie.poster.isEmpty()){
                    Picasso.get().load(movie.poster).placeholder(R.drawable.poster_placeholder).into(ivMovieCover)
                    Picasso.get().load(movie.poster).placeholder(R.drawable.poster_placeholder).into(ivBackground)
                    ivBackground.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.DARKEN)
                }

                tvMovieTitle.text = movie.title
                tvYear.text = movie.year
                tvLength.text = movie.runtime
                tvGenre.text = movie.genre
            }
        }


    }

}