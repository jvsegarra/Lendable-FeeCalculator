package converters

interface ValueConverter {
    fun convert(value: Float): Float
}

class RoundFeeConverter : ValueConverter {
    companion object {
        const val ROUND_MULTIPLE_VALUE = 5
    }

    override fun convert(value: Float): Float {
        return (Math.round(value / ROUND_MULTIPLE_VALUE) * ROUND_MULTIPLE_VALUE).toFloat();
    }
}
