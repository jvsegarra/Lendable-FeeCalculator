import calculator.InterpolatedFeeCalculator
import breakpoints.BreakpointsProviderFactory
import breakpoints.BreakpointsProviderFactory.Companion.LIST_PROVIDER
import converters.RoundFeeConverter
import model.LoanApplication
import model.Term

fun main() {
    val breakpointsProvider = BreakpointsProviderFactory.getBreakpointsProvider(LIST_PROVIDER)
    val breakpointsList = breakpointsProvider.getBreakpointsList()
    val feeCalculator = InterpolatedFeeCalculator(breakpointsList, RoundFeeConverter())

    val loanApplications = listOf<LoanApplication>(
        LoanApplication(2000F, Term.SHORT_TERM.term),
        LoanApplication(2750F, Term.SHORT_TERM.term),
        LoanApplication(15000F, Term.LONG_TERM.term),
        LoanApplication(17178F, Term.LONG_TERM.term),
    )

    loanApplications.forEach {
        loanApplication -> val fee = feeCalculator.calculate(loanApplication)
        println(fee)
    }
}
