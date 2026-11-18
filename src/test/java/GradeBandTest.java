import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeBandTest {
    @Test
    void boundaries_mapToCorrectBand() {
        assertEquals(GradeBand.HD, GradeBand.fromMark(100));
        assertEquals(GradeBand.D, GradeBand.fromMark(84));
        assertEquals(GradeBand.D, GradeBand.fromMark(75));
        assertEquals(GradeBand.C, GradeBand.fromMark(74));
        assertEquals(GradeBand.P, GradeBand.fromMark(50));
        assertEquals(GradeBand.F, GradeBand.fromMark(49));
        assertEquals(GradeBand.F, GradeBand.fromMark(0));
    }

    @Test
    void moreThan100_valueCheck() {
        assertThrows(IllegalArgumentException.class, () -> GradeBand.fromMark(101));
    }

    @Test
    void lessThan0_valueCheck() {
        assertThrows(IllegalArgumentException.class, () -> GradeBand.fromMark(-1));
    }

}
