import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

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

    @Test 
    void sortByCode_ordersAlphabetically() {
        List<Subject> subjects = new ArrayList<>(List.of(new Subject("COMP2019", "SysPro1"),new Subject("COMP2009", "DSA"),new Subject("COMP3009", "DSP")));
        subjects.sort(Comparator.comparing(Subject::code));
        List<Subject> expectedList = new ArrayList<>(List.of(new Subject("COMP2009", "DSA"),new Subject("COMP2019", "SysPro1"),new Subject("COMP3009", "DSP")));
        assertEquals(expectedList, subjects);
    }
}