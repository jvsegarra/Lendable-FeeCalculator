package breakpoints

import exceptions.AmountNotInRangeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.LoanApplication
import model.Term
import java.math.BigDecimal

class BreakpointsListTest : FunSpec() {
    init {
        test("Breakpoint is returned when its amount matches the application amount") {
            // Given
            val loanApplication = LoanApplication(BigDecimal(9988), 12)
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = BigDecimal(1000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(2000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(9988), term = 12, fee = BigDecimal(50)),
                )
            )

            // When
            val result = breakpointsList.getMatchingBreakpoint(loanApplication)

            // Then
            result shouldBe Breakpoint(amount = BigDecimal(9988), term = 12, fee = BigDecimal(50))
        }

        test("Null is returned when no breakpoint amount matches the application amount") {
            // Given
            val loanApplication = LoanApplication(BigDecimal(9987), 12)
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = BigDecimal(1000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(2000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(9988), term = 12, fee = BigDecimal(50)),
                )
            )

            // When
            val result = breakpointsList.getMatchingBreakpoint(loanApplication)

            // Then
            result shouldBe null
        }

        test("Lower and Upper breakpoints are returned for loan application amount") {
            // Given
            val loanApplication = LoanApplication(BigDecimal(9987), 12)
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = BigDecimal(1000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(2000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(9988), term = 12, fee = BigDecimal(50)),
                )
            )

            // When
            val result = breakpointsList.fetchBoundingBreakpoints(loanApplication)

            // Then
            result shouldBe Pair(
                Breakpoint(amount = BigDecimal(2000), term = 12, fee = BigDecimal(50)),
                Breakpoint(amount = BigDecimal(9988), term = 12, fee = BigDecimal(50)),
            )
        }

        test("Exception is thrown when application amount is out of Breakpoints list range") {
            // Given
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = BigDecimal(1000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(2000), term = 12, fee = BigDecimal(50)),
                    Breakpoint(amount = BigDecimal(9988), term = 12, fee = BigDecimal(50)),
                )
            )

            // When - Then
            listOf(
                LoanApplication(BigDecimal(999), Term.SHORT_TERM.term),
                LoanApplication(BigDecimal(9989), Term.SHORT_TERM.term),
            ).forAll { loanApplication ->
                shouldThrow<AmountNotInRangeException> {
                    breakpointsList.fetchBoundingBreakpoints(loanApplication)
                }
            }
        }
    }
}

