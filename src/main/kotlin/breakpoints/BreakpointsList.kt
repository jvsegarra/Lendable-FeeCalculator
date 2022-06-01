package breakpoints

import exceptions.AmountNotInRangeException
import model.LoanApplication
import java.math.BigDecimal

data class Breakpoint(val amount: BigDecimal, val term: Int, val fee: BigDecimal)

data class BreakpointsList(val breakpoints: List<Breakpoint>) {
    fun getMatchingBreakpoint(loanApplication: LoanApplication): Breakpoint? =
        breakpoints.firstOrNull { breakpoint -> breakpoint.amount == loanApplication.amount && breakpoint.term == loanApplication.term }

    fun fetchBoundingBreakpoints(loanApplication: LoanApplication): Pair<Breakpoint, Breakpoint> {
        this.checkAmountIsWithinRange(loanApplication.amount)

        val indexOfFirstBiggerFee =
            breakpoints.indexOfFirst { breakpoint -> breakpoint.amount > loanApplication.amount && breakpoint.term == loanApplication.term }

        return Pair(breakpoints[indexOfFirstBiggerFee - 1], breakpoints[indexOfFirstBiggerFee])
    }

    private fun checkAmountIsWithinRange(amount: BigDecimal) {
        val breakpointsLowerValue = breakpoints.first().amount
        val breakpointsUpperAmount = breakpoints.last().amount

        if (amount < breakpointsLowerValue || amount > breakpointsUpperAmount ) {
            throw AmountNotInRangeException("Amount $amount out of breakpoints range")
        }
    }
}
