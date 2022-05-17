package model

enum class Term(val term: Int) {
    SHORT_TERM(12),
    LONG_TERM(24)
}

// Keeping the original, but the best option is to define directly Term as a parameter instead of Int
data class LoanApplication(val amount: Float, val term: Int) {
    init {
        require(Term.values().any { enumTerm -> enumTerm.term == term }) {
            "Term $term must belong to the Enum term allowed values (12, 24)"
        }
    }
}
