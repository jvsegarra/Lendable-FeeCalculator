package converters

import java.math.BigDecimal
import java.math.RoundingMode

const val ROUND_MULTIPLE_VALUE = 5

fun roundValue(value: BigDecimal): BigDecimal =
    (value / BigDecimal(ROUND_MULTIPLE_VALUE)).setScale(0, RoundingMode.CEILING) * BigDecimal(ROUND_MULTIPLE_VALUE);
