package calculator

import breakpoints.Breakpoint
import breakpoints.BreakpointsList
import breakpoints.BreakpointsListProvider
import converters.roundValue
import exceptions.AmountNotInRangeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.*
import java.math.BigDecimal

class FeeCalculatorTest : FunSpec() {
    init {
        test("Interpolated Fee Calculator returns the expected correct result") {
            // we use the same list as BreakpointsListProvider, but we can inject any list into the Fee Calculator
            val breakpointsList = BreakpointsListProvider().getBreakpointsList()
            val feeInterpolatedFeeCalculator = InterpolatedFeeCalculator(breakpointsList, ::roundValue)

            listOf(
                LoanApplication(BigDecimal(1421), Term.SHORT_TERM.term) to BigDecimal(70),
                LoanApplication(BigDecimal(1421.89), Term.SHORT_TERM.term) to BigDecimal(70),
                LoanApplication(BigDecimal(2750), Term.SHORT_TERM.term) to BigDecimal(90),
                LoanApplication(BigDecimal(2750.23), Term.SHORT_TERM.term) to BigDecimal(90),
                LoanApplication(BigDecimal(4000), Term.SHORT_TERM.term) to BigDecimal(115),
                LoanApplication(BigDecimal(9300), Term.SHORT_TERM.term) to BigDecimal(190),
                LoanApplication(BigDecimal(9300.51), Term.SHORT_TERM.term) to BigDecimal(190),
                LoanApplication(BigDecimal(11000), Term.SHORT_TERM.term) to BigDecimal(220),
                LoanApplication(BigDecimal(12325), Term.SHORT_TERM.term) to BigDecimal(250),
                LoanApplication(BigDecimal(12325.83), Term.SHORT_TERM.term) to BigDecimal(250),
                LoanApplication(BigDecimal(12325.834567), Term.SHORT_TERM.term) to BigDecimal(250),
                LoanApplication(BigDecimal(17178), Term.SHORT_TERM.term) to BigDecimal(345),
                LoanApplication(BigDecimal(19400), Term.SHORT_TERM.term) to BigDecimal(390),
                LoanApplication(BigDecimal(1421.3), Term.LONG_TERM.term) to BigDecimal(85),
                LoanApplication(BigDecimal(2750), Term.LONG_TERM.term) to BigDecimal(115),
                LoanApplication(BigDecimal(4000), Term.LONG_TERM.term) to BigDecimal(160),
                LoanApplication(BigDecimal(4000.08), Term.LONG_TERM.term) to BigDecimal(160),
                LoanApplication(BigDecimal(4000.08435), Term.LONG_TERM.term) to BigDecimal(160),
                LoanApplication(BigDecimal(9300), Term.LONG_TERM.term) to BigDecimal(375),
                LoanApplication(BigDecimal(11000), Term.LONG_TERM.term) to BigDecimal(440),
                LoanApplication(BigDecimal(11000.01), Term.LONG_TERM.term) to BigDecimal(440),
                LoanApplication(BigDecimal(11000.0167), Term.LONG_TERM.term) to BigDecimal(440),
                LoanApplication(BigDecimal(12325), Term.LONG_TERM.term) to BigDecimal(495),
                LoanApplication(BigDecimal(17178), Term.LONG_TERM.term) to BigDecimal(690),
                LoanApplication(BigDecimal(17178.99), Term.LONG_TERM.term) to BigDecimal(690),
                LoanApplication(BigDecimal(17178.999999), Term.LONG_TERM.term) to BigDecimal(690),
                LoanApplication(BigDecimal(19400), Term.LONG_TERM.term) to BigDecimal(780),
                LoanApplication(BigDecimal(19999.99), Term.LONG_TERM.term) to BigDecimal(800),
                LoanApplication(BigDecimal(19999.9901987), Term.LONG_TERM.term) to BigDecimal(800),
            ).forAll { (loanApplication, expectedFee) ->
                feeInterpolatedFeeCalculator.calculate(loanApplication) shouldBe expectedFee
            }
        }

        test("Exception is thrown when loan amount is out of Breakpoints list range") {
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = BigDecimal(1000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(2000), term = 12, fee = BigDecimal(50))
                )
            )
            val feeInterpolatedFeeCalculator = InterpolatedFeeCalculator(breakpointsList, ::roundValue)

            listOf(
                LoanApplication(BigDecimal(999), Term.SHORT_TERM.term),
                LoanApplication(BigDecimal(2001), Term.SHORT_TERM.term),
            ).forAll { loanApplication ->
                shouldThrow<AmountNotInRangeException> {
                    feeInterpolatedFeeCalculator.calculate(loanApplication)
                }
            }
        }
    }
}
