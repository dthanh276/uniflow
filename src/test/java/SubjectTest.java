import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class SubjectTest {
    @Test
    void sameFields_areEqual() {
        Subject a = new Subject("COMP101", "Intro");
        Subject b = new Subject("COMP101", "Intro");
        assertEquals(a, b);
    }

    @Test
    void sameFields_foundInHashSet() {
        Subject a = new Subject("COMP101", "Intro");
        Subject b = new Subject("COMP101", "Intro");
        Set<Subject> set = new HashSet<>();
        set.add(a);
        assertTrue(set.contains(b));
    }

    @Test
    void constructor_rejectsNullCode() {
        assertThrows(IllegalArgumentException.class, () -> new Subject(null, "Intro"));
    }

    @Test
    void constructor_rejectsBlankCode() {
        assertThrows(IllegalArgumentException.class, () -> new Subject(" ", "Intro"));
    }

}