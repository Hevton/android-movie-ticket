package woowacourse.movie.domain.grade

import woowacourse.movie.domain.price.TicketPrice

data class Position private constructor(val rowIndex: Int, val columnIndex: Int) {
    init {
        validateCoordinate(columnIndex)
    }

    private fun validateCoordinate(columnIndex: Int) {
        require(rowIndex in START_INDEX..MAXIMUM_ROW_INDEX) { OVER_MAXIMUM_INDEX }
        require(columnIndex in START_INDEX..MAXIMUM_COLUMN_INDEX) { OVER_MAXIMUM_INDEX }
    }

    fun getGradePrice(): TicketPrice {
        var price: Int = 0
        Grade.values().forEach { if (rowIndex in it.rowIndexRange) price = it.price }
        return TicketPrice(price)
    }

    companion object {
        const val START_INDEX = 0
        const val MAXIMUM_ROW_INDEX = 4
        const val MAXIMUM_COLUMN_INDEX = 3
        private const val OVER_MAXIMUM_INDEX = "좌석의 최대 범위를 벗어났습니다."
        private const val POSITION_CACHE_NULL_ERROR = "Position이 캐싱된 값이 없습니다"

        private fun getIndexRange(maximum: Int): IntRange = START_INDEX..maximum

        private val COORDINATE: Map<Pair<Int, Int>, Position> =
            getIndexRange(MAXIMUM_ROW_INDEX).map { row -> getIndexRange(MAXIMUM_COLUMN_INDEX).map { column -> row to column } }
                .flatten()
                .associateWith { Position(it.first, it.second) }

        fun from(row: Int, column: Int): Position =
            COORDINATE[row to column] ?: throw IllegalArgumentException(POSITION_CACHE_NULL_ERROR)
    }
}
