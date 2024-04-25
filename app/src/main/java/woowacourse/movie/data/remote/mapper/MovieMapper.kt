package woowacourse.movie.data.remote.mapper

import woowacourse.movie.data.remote.dto.MovieResponse
import woowacourse.movie.domain.Movie

object MovieMapper {
    fun fromMovieResponse(movieResponse: MovieResponse): Movie {
        return Movie(
            id = movieResponse.id,
            thumbnailUrl = movieResponse.thumbnailUrl,
            title = movieResponse.title,
            description = movieResponse.description,
            dateTime = movieResponse.dateTime,
            runningTime = movieResponse.runningTime,
        )
    }
}
