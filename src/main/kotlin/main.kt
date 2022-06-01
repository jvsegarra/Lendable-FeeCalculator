import breakpoints.BreakpointsProviderFactory
import breakpoints.BreakpointsProviderFactory.Companion.LIST_PROVIDER
import calculator.InterpolatedFeeCalculator
import converters.roundValue
import model.LoanApplication
import model.Term
import java.math.BigDecimal

fun main() {
    val breakpointsProvider = BreakpointsProviderFactory.getBreakpointsProvider(LIST_PROVIDER)
    val breakpointsList = breakpointsProvider.getBreakpointsList()
    val feeCalculator = InterpolatedFeeCalculator(breakpointsList, ::roundValue)

    val loanApplications = listOf<LoanApplication>(
        LoanApplication(BigDecimal(2000), Term.SHORT_TERM.term),
        LoanApplication(BigDecimal(2750), Term.SHORT_TERM.term),
        LoanApplication(BigDecimal(15000), Term.LONG_TERM.term),
        LoanApplication(BigDecimal(17178), Term.LONG_TERM.term),
    )

    loanApplications.forEach {
        loanApplication -> val fee = feeCalculator.calculate(loanApplication)
        println(fee)
    }
}
