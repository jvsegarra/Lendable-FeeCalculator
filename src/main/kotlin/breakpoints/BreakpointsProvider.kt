package breakpoints

interface BreakpointsProvider {
    fun getBreakpointsList(): BreakpointsList
}

class BreakpointsListProvider : BreakpointsProvider {
    override fun getBreakpointsList(): BreakpointsList {
        return BreakpointsList(
            listOf(
                Breakpoint(amount = 1000F, term = 12, fee = 50F),
                Breakpoint(amount = 2000F, term = 12, fee = 90F),
                Breakpoint(amount = 3000F, term = 12, fee = 90F),
                Breakpoint(amount = 4000F, term = 12, fee = 115F),
                Breakpoint(amount = 5000F, term = 12, fee = 100F),
                Breakpoint(amount = 6000F, term = 12, fee = 120F),
                Breakpoint(amount = 7000F, term = 12, fee = 140F),
                Breakpoint(amount = 8000F, term = 12, fee = 160F),
                Breakpoint(amount = 9000F, term = 12, fee = 180F),
                Breakpoint(amount = 10000F, term = 12, fee = 200F),
                Breakpoint(amount = 11000F, term = 12, fee = 220F),
                Breakpoint(amount = 12000F, term = 12, fee = 240F),
                Breakpoint(amount = 13000F, term = 12, fee = 260F),
                Breakpoint(amount = 14000F, term = 12, fee = 280F),
                Breakpoint(amount = 15000F, term = 12, fee = 300F),
                Breakpoint(amount = 16000F, term = 12, fee = 320F),
                Breakpoint(amount = 17000F, term = 12, fee = 340F),
                Breakpoint(amount = 18000F, term = 12, fee = 360F),
                Breakpoint(amount = 19000F, term = 12, fee = 380F),
                Breakpoint(amount = 20000F, term = 12, fee = 400F),
                Breakpoint(amount = 1000F, term = 24, fee = 70F),
                Breakpoint(amount = 2000F, term = 24, fee = 100F),
                Breakpoint(amount = 3000F, term = 24, fee = 120F),
                Breakpoint(amount = 4000F, term = 24, fee = 160F),
                Breakpoint(amount = 5000F, term = 24, fee = 200F),
                Breakpoint(amount = 6000F, term = 24, fee = 240F),
                Breakpoint(amount = 7000F, term = 24, fee = 280F),
                Breakpoint(amount = 8000F, term = 24, fee = 320F),
                Breakpoint(amount = 9000F, term = 24, fee = 360F),
                Breakpoint(amount = 10000F, term = 24, fee = 400F),
                Breakpoint(amount = 11000F, term = 24, fee = 440F),
                Breakpoint(amount = 12000F, term = 24, fee = 480F),
                Breakpoint(amount = 13000F, term = 24, fee = 520F),
                Breakpoint(amount = 14000F, term = 24, fee = 560F),
                Breakpoint(amount = 15000F, term = 24, fee = 600F),
                Breakpoint(amount = 16000F, term = 24, fee = 640F),
                Breakpoint(amount = 17000F, term = 24, fee = 680F),
                Breakpoint(amount = 18000F, term = 24, fee = 720F),
                Breakpoint(amount = 19000F, term = 24, fee = 760F),
                Breakpoint(amount = 20000F, term = 24, fee = 800F),
            )
        )
    }
}

class BreakpointsJsonProvider : BreakpointsProvider {
    override fun getBreakpointsList(): BreakpointsList {
        // This would have injected a file reader and return a BreakpointsList from a json file
        return BreakpointsList(
            listOf(
                Breakpoint(amount = 1000F, term = 12, fee = 50F),
                Breakpoint(amount = 1000F, term = 24, fee = 50F),
            )
        )
    }
}
