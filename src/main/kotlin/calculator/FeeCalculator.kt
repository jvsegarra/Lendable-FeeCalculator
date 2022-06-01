package calculator

import breakpoints.BreakpointsList
import model.LoanApplication
import java.math.BigDecimal


interface FeeCalculator {
    fun calculate(loanApplication: LoanApplication): BigDecimal
}

class InterpolatedFeeCalculator(
    private val breakpointsList: BreakpointsList,
    private val convertValue: (value: BigDecimal) -> BigDecimal
) : FeeCalculator {
    override fun calculate(loanApplication: LoanApplication): BigDecimal {
        val matchingBreakpoint = breakpointsList.getMatchingBreakpoint(loanApplication)
        matchingBreakpoint?.let {
            return convertValue(matchingBreakpoint.fee)
        }

        val boundingBreakpoints = breakpointsList.fetchBoundingBreakpoints(loanApplication)

        val fee = InterpolationCalculator.calculateInterpolation(
            loanApplication.amount,
            boundingBreakpoints.first,
            boundingBreakpoints.second
        )

        return convertValue(fee)
    }
}
