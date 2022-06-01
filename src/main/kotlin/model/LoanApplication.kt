package model

import java.math.BigDecimal
import java.math.RoundingMode

enum class Term(val term: Int) {
    SHORT_TERM(12),
    LONG_TERM(24)
}

// Keeping the original, but the best option is to define directly Term as a parameter instead of Int
data class LoanApplication(var amount: BigDecimal, val term: Int) {
    companion object {
        const val DECIMAL_LIMIT = 2
    }

    init {
        require(Term.values().any { enumTerm -> enumTerm.term == term }) {
            "Term $term must belong to the Enum term allowed values (12, 24)"
        }

        // Rounding to 2 decimal places
        this.amount = amount.setScale(DECIMAL_LIMIT, RoundingMode.HALF_EVEN)
    }
}
