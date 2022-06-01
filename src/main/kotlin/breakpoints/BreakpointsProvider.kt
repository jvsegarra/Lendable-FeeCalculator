package breakpoints

import java.math.BigDecimal

interface BreakpointsProvider {
    fun getBreakpointsList(): BreakpointsList
}

class BreakpointsListProvider : BreakpointsProvider {
    override fun getBreakpointsList(): BreakpointsList {
        return BreakpointsList(
            listOf(
                Breakpoint(amount = BigDecimal(1000), term = 12, fee = BigDecimal(50)),
                Breakpoint(amount = BigDecimal(2000), term = 12, fee = BigDecimal(90)),
                Breakpoint(amount = BigDecimal(3000), term = 12, fee = BigDecimal(90)),
                Breakpoint(amount = BigDecimal(4000), term = 12, fee = BigDecimal(115)),
                Breakpoint(amount = BigDecimal(5000), term = 12, fee = BigDecimal(100)),
                Breakpoint(amount = BigDecimal(6000), term = 12, fee = BigDecimal(120)),
                Breakpoint(amount = BigDecimal(7000), term = 12, fee = BigDecimal(140)),
                Breakpoint(amount = BigDecimal(8000), term = 12, fee = BigDecimal(160)),
                Breakpoint(amount = BigDecimal(9000), term = 12, fee = BigDecimal(180)),
                Breakpoint(amount = BigDecimal(10000), term = 12, fee = BigDecimal(200)),
                Breakpoint(amount = BigDecimal(11000), term = 12, fee = BigDecimal(220)),
                Breakpoint(amount = BigDecimal(12000), term = 12, fee = BigDecimal(240)),
                Breakpoint(amount = BigDecimal(13000), term = 12, fee = BigDecimal(260)),
                Breakpoint(amount = BigDecimal(14000), term = 12, fee = BigDecimal(280)),
                Breakpoint(amount = BigDecimal(15000), term = 12, fee = BigDecimal(300)),
                Breakpoint(amount = BigDecimal(16000), term = 12, fee = BigDecimal(320)),
                Breakpoint(amount = BigDecimal(17000), term = 12, fee = BigDecimal(340)),
                Breakpoint(amount = BigDecimal(18000), term = 12, fee = BigDecimal(360)),
                Breakpoint(amount = BigDecimal(19000), term = 12, fee = BigDecimal(380)),
                Breakpoint(amount = BigDecimal(20000), term = 12, fee = BigDecimal(400)),
                Breakpoint(amount = BigDecimal(1000), term = 24, fee = BigDecimal(70)),
                Breakpoint(amount = BigDecimal(2000), term = 24, fee = BigDecimal(100)),
                Breakpoint(amount = BigDecimal(3000), term = 24, fee = BigDecimal(120)),
                Breakpoint(amount = BigDecimal(4000), term = 24, fee = BigDecimal(160)),
                Breakpoint(amount = BigDecimal(5000), term = 24, fee = BigDecimal(200)),
                Breakpoint(amount = BigDecimal(6000), term = 24, fee = BigDecimal(240)),
                Breakpoint(amount = BigDecimal(7000), term = 24, fee = BigDecimal(280)),
                Breakpoint(amount = BigDecimal(8000), term = 24, fee = BigDecimal(320)),
                Breakpoint(amount = BigDecimal(9000), term = 24, fee = BigDecimal(360)),
                Breakpoint(amount = BigDecimal(10000), term = 24, fee = BigDecimal(400)),
                Breakpoint(amount = BigDecimal(11000), term = 24, fee = BigDecimal(440)),
                Breakpoint(amount = BigDecimal(12000), term = 24, fee = BigDecimal(480)),
                Breakpoint(amount = BigDecimal(13000), term = 24, fee = BigDecimal(520)),
                Breakpoint(amount = BigDecimal(14000), term = 24, fee = BigDecimal(560)),
                Breakpoint(amount = BigDecimal(15000), term = 24, fee = BigDecimal(600)),
                Breakpoint(amount = BigDecimal(16000), term = 24, fee = BigDecimal(640)),
                Breakpoint(amount = BigDecimal(17000), term = 24, fee = BigDecimal(680)),
                Breakpoint(amount = BigDecimal(18000), term = 24, fee = BigDecimal(720)),
                Breakpoint(amount = BigDecimal(19000), term = 24, fee = BigDecimal(760)),
                Breakpoint(amount = BigDecimal(20000), term = 24, fee = BigDecimal(800)),
            )
        )
    }
}

class BreakpointsJsonProvider : BreakpointsProvider {
    override fun getBreakpointsList(): BreakpointsList {
        // This would have injected a file reader and return a BreakpointsList from a json file
        return BreakpointsList(
            listOf(
                Breakpoint(amount = BigDecimal(1000), term = 12, fee = BigDecimal(50)),
                Breakpoint(amount = BigDecimal(1000), term = 24, fee = BigDecimal(50)),
            )
        )
    }
}
