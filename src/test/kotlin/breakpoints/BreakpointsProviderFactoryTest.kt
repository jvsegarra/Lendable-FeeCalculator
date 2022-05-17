package breakpoints

import exceptions.InvalidProviderException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

class BreakpointsProviderFactoryTest : FunSpec() {
    init {
        test("Factory returns correct instance for the breakpoints list provider") {
            // Given
            val providerType = BreakpointsProviderFactory.LIST_PROVIDER

            // When
            val result = BreakpointsProviderFactory.getBreakpointsProvider(providerType)

            // Then
            result shouldBe instanceOf<BreakpointsListProvider>()
        }

        test("Factory returns correct instance for the breakpoints json provider") {
            // Given
            val providerType = BreakpointsProviderFactory.JSON_PROVIDER

            // When
            val result = BreakpointsProviderFactory.getBreakpointsProvider(providerType)

            // Then
            result shouldBe instanceOf<BreakpointsJsonProvider>()
        }

        test("Exception is thrown when an invalid provider is requested") {
            // Given
            val providerType = "any other provider"

            // When - Then
            shouldThrow<InvalidProviderException> {
                BreakpointsProviderFactory.getBreakpointsProvider(providerType)
            }
        }
    }
}

