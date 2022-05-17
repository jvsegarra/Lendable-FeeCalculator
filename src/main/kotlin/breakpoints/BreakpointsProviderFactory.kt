package breakpoints

import exceptions.InvalidProviderException

class BreakpointsProviderFactory {
    companion object {
        const val LIST_PROVIDER = "listProvider"
        const val JSON_PROVIDER = "jsonProvider"

        fun getBreakpointsProvider(providerType: String): BreakpointsProvider = when (providerType) {
            LIST_PROVIDER -> BreakpointsListProvider()
            JSON_PROVIDER -> BreakpointsJsonProvider()
            else -> throw InvalidProviderException(
                "The list of providers must be in ${listOf(LIST_PROVIDER, JSON_PROVIDER)}"
            )
        }
    }
}
