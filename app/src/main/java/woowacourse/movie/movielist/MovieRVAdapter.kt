package woowacourse.movie.movielist

import MovieViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.dto.AdDto
import woowacourse.movie.dto.MovieDto

class MovieRVAdapter(
    private val movies: List<MovieDto>,
    private val ad: AdDto,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var itemMovieClick: OnClickListener<MovieDto>
    lateinit var itemAdClick: OnClickListener<AdDto>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.values()[viewType]) {
            ViewType.MOVIE_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view, itemMovieClick)
            }
            ViewType.AD_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ad_item, parent, false)
                AdViewHolder(view, itemAdClick)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        when (holder.itemViewType) {
            ViewType.MOVIE_VIEW.ordinal -> (holder as MovieViewHolder).bind(item)
            ViewType.AD_VIEW.ordinal -> (holder as AdViewHolder).bind(ad)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if ((position + 1) % DIVIDE_MOVIE_VIEW == 0) return ViewType.AD_VIEW.ordinal else return ViewType.MOVIE_VIEW.ordinal
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    companion object {
        private const val DIVIDE_MOVIE_VIEW = 4
    }
}
