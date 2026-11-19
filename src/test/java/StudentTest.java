import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class StudentTest {
    @Test
    void enrolSameSubjectTwice_countsOnce() {
        Student st = new Student(1,
                "Harry");
        st.enroll(new Subject("COMP101", "Intro"));
        st.enroll(new Subject("COMP101", "Intro"));
        assertEquals(1, st.subjectCount());
    }

    @Test
    void addAssessment_twoForSameSubject_bothStored() {
        Student student = new Student(1, "Harry");
        Subject subject = new Subject("COMP2009", "DSA");

        Assessment a1 = new Assessment("Quiz 1", 15.0, 80.0, LocalDate.of(2026, 12, 1));
        Assessment a2 = new Assessment("Quiz 2", 15.0, 80.0, LocalDate.of(2026, 12, 1));

        student.addAssessment(subject, a1);
        student.addAssessment(subject, a2);

        assertEquals(2, student.assessmentsFor(subject).size());
    }

    @Test
    void assessmentsFor_unknownSubject_returnsEmptyList() {
        Student student = new Student(1, "Harry");
        Subject subject = new Subject("COMP2009", "DSA");

        assertEquals(0, student.assessmentsFor(subject).size());
    }

}
