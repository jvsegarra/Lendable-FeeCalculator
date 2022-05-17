package model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll

class LoanApplicationTest : FunSpec() {
    init {
        test("Loan Application is correctly built with valid term value") {
            // Given
            val listOfValidValues = listOf(12, 24)

            // When - Then
            listOfValidValues.forAll { term ->
                LoanApplication(1000F, term)
            }
        }

        test("Exception is thrown when Loan Application is built with invalid term") {
            // Given
            val invalidTerm = 88

            // When - Then
            shouldThrow<IllegalArgumentException> {
                LoanApplication(1000f, invalidTerm)
            }
        }
    }
}