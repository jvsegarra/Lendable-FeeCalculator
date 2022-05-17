package breakpoints

import exceptions.AmountNotInRangeException
import model.LoanApplication

data class Breakpoint(val amount: Float, val term: Int, val fee: Float)

data class BreakpointsList(val breakpoints: List<Breakpoint>) {
    fun containsMatchingLoanApplication(loanApplication: LoanApplication): Breakpoint? =
        breakpoints.firstOrNull { breakpoint -> breakpoint.amount == loanApplication.amount && breakpoint.term == loanApplication.term }

    fun fetchBoundingBreakpoints(loanApplication: LoanApplication): Pair<Breakpoint, Breakpoint> {
        this.checkAmountIsWithinRange(loanApplication.amount)

        val indexOfFirstBiggerFee =
            breakpoints.indexOfFirst { breakpoint -> breakpoint.amount > loanApplication.amount && breakpoint.term == loanApplication.term }

        return Pair(breakpoints[indexOfFirstBiggerFee - 1], breakpoints[indexOfFirstBiggerFee])
    }

    private fun checkAmountIsWithinRange(amount: Float) {
        val breakpointsLowerValue = breakpoints.first().amount
        val breakpointsUpperAmount = breakpoints.last().amount

        if (amount < breakpointsLowerValue || amount > breakpointsUpperAmount ) {
            throw AmountNotInRangeException("Amount $amount out of breakpoints range")
        }
    }
}
