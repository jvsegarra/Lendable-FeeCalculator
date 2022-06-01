package calculator

import breakpoints.Breakpoint
import java.math.BigDecimal

object InterpolationCalculator {
    fun calculateInterpolation(
        loanAmount: BigDecimal,
        lowerBreakpoint: Breakpoint,
        upperBreakpoint: Breakpoint
    ): BigDecimal {
        val upperAmount = upperBreakpoint.amount
        val lowerAmount = lowerBreakpoint.amount
        val upperFee = upperBreakpoint.fee
        val lowerFee = lowerBreakpoint.fee

        // Googled how to interpolate values :)
        // https://www.geeksforgeeks.org/how-to-implement-linear-interpolation-in-python/
        // x -> loanAmount, x1 -> lowerAmount, y1 -> lowerFee, x2 -> upperAmount, y2 -> upperFee
        return lowerFee +
                ((loanAmount - lowerAmount) * (upperFee - lowerFee) / (upperAmount - lowerAmount))
    }
}
