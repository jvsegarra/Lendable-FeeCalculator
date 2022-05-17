package calculator

import breakpoints.BreakpointsList
import converters.ValueConverter
import model.LoanApplication


interface FeeCalculator {
    fun calculate(loanApplication: LoanApplication): Float
}

class InterpolatedFeeCalculator(
    private val breakpointsList: BreakpointsList,
    private val valueConverter: ValueConverter
) : FeeCalculator {
    override fun calculate(loanApplication: LoanApplication): Float {
        val breakpoint = breakpointsList.containsMatchingLoanApplication(loanApplication)
        breakpoint?.let {
            return valueConverter.convert(breakpoint.fee)
        }

        val boundingBreakpoints = breakpointsList.fetchBoundingBreakpoints(loanApplication)

        val fee = InterpolationCalculator.calculateInterpolation(
            loanApplication.amount,
            boundingBreakpoints.first,
            boundingBreakpoints.second
        )

        return valueConverter.convert(fee)
    }
}
