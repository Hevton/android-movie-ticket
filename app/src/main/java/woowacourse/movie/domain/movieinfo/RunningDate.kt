package woowacourse.movie.domain.movieinfo

import java.io.Serializable
import java.time.LocalDate

data class RunningDate(val startDate: LocalDate, val endDate: LocalDate) :
    Serializable
