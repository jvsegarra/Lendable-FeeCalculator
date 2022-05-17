package calculator

import breakpoints.Breakpoint
import breakpoints.BreakpointsList
import breakpoints.BreakpointsListProvider
import converters.RoundFeeConverter
import exceptions.AmountNotInRangeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.*

class FeeCalculatorTest : FunSpec() {
    init {
        test("Interpolated Fee Calculator returns the expected correct result") {
            // we use the same list as BreakpointsListProvider, but we can inject any list into the Fee Calculator
            val breakpointsList = BreakpointsListProvider().getBreakpointsList()
            val valueConverter = RoundFeeConverter()
            val feeInterpolatedFeeCalculator = InterpolatedFeeCalculator(breakpointsList, valueConverter)

            listOf(
                LoanApplication(1421F, Term.SHORT_TERM.term) to 65F,
                LoanApplication(2750F, Term.SHORT_TERM.term) to 90F,
                LoanApplication(4000F, Term.SHORT_TERM.term) to 115F,
                LoanApplication(9300F, Term.SHORT_TERM.term) to 185F,
                LoanApplication(11000F, Term.SHORT_TERM.term) to 220F,
                LoanApplication(12325F, Term.SHORT_TERM.term) to 245F,
                LoanApplication(17178F, Term.SHORT_TERM.term) to 345F,
                LoanApplication(19400F, Term.SHORT_TERM.term) to 390F,
                LoanApplication(1421F, Term.LONG_TERM.term) to 85F,
                LoanApplication(2750F, Term.LONG_TERM.term) to 115F,
                LoanApplication(4000F, Term.LONG_TERM.term) to 160F,
                LoanApplication(9300F, Term.LONG_TERM.term) to 370F,
                LoanApplication(11000F, Term.LONG_TERM.term) to 440F,
                LoanApplication(12325F, Term.LONG_TERM.term) to 495F,
                LoanApplication(17178F, Term.LONG_TERM.term) to 685F,
                LoanApplication(19400F, Term.LONG_TERM.term) to 775F,
            ).forAll { (loanApplication, expectedFee) ->
                feeInterpolatedFeeCalculator.calculate(loanApplication) shouldBe expectedFee
            }
        }

        test("Exception is thrown when loan amount is out of Breakpoints list range") {
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = 1000F, term = 12, fee = 50F),
                    Breakpoint(amount = 2000F, term = 12, fee = 50F)
                )
            )
            val valueConverter = RoundFeeConverter()
            val feeInterpolatedFeeCalculator = InterpolatedFeeCalculator(breakpointsList, valueConverter)

            listOf(
                LoanApplication(999F, Term.SHORT_TERM.term),
                LoanApplication(2001F, Term.SHORT_TERM.term),
            ).forAll { loanApplication ->
                shouldThrow<AmountNotInRangeException> {
                    feeInterpolatedFeeCalculator.calculate(loanApplication)
                }
            }
        }
    }
}
