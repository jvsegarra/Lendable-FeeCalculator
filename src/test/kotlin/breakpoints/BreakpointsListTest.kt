package breakpoints

import exceptions.AmountNotInRangeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.LoanApplication
import model.Term

class BreakpointsListTest : FunSpec() {
    init {
        test("Breakpoint is returned when its amount matches the application amount") {
            // Given
            val loanApplication = LoanApplication(9988F, 12)
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = 1000F, term = 12, fee = 50F),
                    Breakpoint(amount = 2000F, term = 12, fee = 50F),
                    Breakpoint(amount = 9988F, term = 12, fee = 50F),
                )
            )

            // When
            val result = breakpointsList.containsMatchingLoanApplication(loanApplication)

            // Then
            result shouldBe Breakpoint(amount = 9988F, term = 12, fee = 50F)
        }

        test("Null is returned when no breakpoint amount matches the application amount") {
            // Given
            val loanApplication = LoanApplication(9987F, 12)
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = 1000F, term = 12, fee = 50F),
                    Breakpoint(amount = 2000F, term = 12, fee = 50F),
                    Breakpoint(amount = 9988F, term = 12, fee = 50F),
                )
            )

            // When
            val result = breakpointsList.containsMatchingLoanApplication(loanApplication)

            // Then
            result shouldBe null
        }

        test("Lower and Upper breakpoints are returned for loan application amount") {
            // Given
            val loanApplication = LoanApplication(9987F, 12)
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = 1000F, term = 12, fee = 50F),
                    Breakpoint(amount = 2000F, term = 12, fee = 50F),
                    Breakpoint(amount = 9988F, term = 12, fee = 50F),
                )
            )

            // When
            val result = breakpointsList.fetchBoundingBreakpoints(loanApplication)

            // Then
            result shouldBe Pair(
                Breakpoint(amount = 2000F, term = 12, fee = 50F),
                Breakpoint(amount = 9988F, term = 12, fee = 50F),
            )
        }

        test("Exception is thrown when application amount is out of Breakpoints list range") {
            // Given
            val breakpointsList = BreakpointsList(
                listOf(
                    Breakpoint(amount = 1000F, term = 12, fee = 50F),
                    Breakpoint(amount = 2000F, term = 12, fee = 50F),
                    Breakpoint(amount = 9988F, term = 12, fee = 50F),
                )
            )

            // When - Then
            listOf(
                LoanApplication(999F, Term.SHORT_TERM.term),
                LoanApplication(9989F, Term.SHORT_TERM.term),
            ).forAll { loanApplication ->
                shouldThrow<AmountNotInRangeException> {
                    breakpointsList.fetchBoundingBreakpoints(loanApplication)
                }
            }
        }
    }
}

