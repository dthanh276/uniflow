import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

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

        student.enroll(subject);

        Assessment a1 = new Assessment("Quiz 1", 15.0, 80.0, LocalDate.of(2026, 12, 1));
        Assessment a2 = new Assessment("Quiz 2", 15.0, 80.0, LocalDate.of(2026, 12, 1));

        student.addAssessment(subject, a1);
        student.addAssessment(subject, a2);

        assertEquals(2, student.assessmentsFor(subject).size());
    }

    @Test
    void addAssessment_rejectsUnenrolledSubject() {
        Student student = new Student(1, "Harry");
        Subject subject = new Subject("COMP2009", "DSA");
        Assessment a1 = new Assessment("Quiz 1", 15.0, 80.0, LocalDate.of(2026, 12, 1));
        assertThrows(IllegalArgumentException.class, () -> student.addAssessment(subject, a1));
    }

    @Test
    void assessmentsFor_unknownSubject_returnsEmptyList() {
        Student student = new Student(1, "Harry");
        Subject subject = new Subject("COMP2009", "DSA");

        assertEquals(0, student.assessmentsFor(subject).size());
    }

    @Test
    void assessmentsByDeadline_nearestFirst_noDateLast() {
        Student stu = new Student(1, "harry");

        Subject s1 = new Subject("COMP2001", "DSA");
        Subject s2 = new Subject("COMP2002", "DSP");
        stu.enroll(s1);
        stu.enroll(s2);

        Assessment a1 = new Assessment("Quiz1",
                40.0,
                null,
                LocalDate.of(2026, 12, 10));
        Assessment a2 = new Assessment("Quiz2",
                40.0,
                null,
                LocalDate.of(2026, 12,1));
        Assessment a3 = new Assessment("Quiz3",
                40.0,
                null,
                null);
        stu.addAssessment(s1,a1);
        stu.addAssessment(s1,a3);
        stu.addAssessment(s2,a2);
        
        assertEquals(List.of(a2,a1,a3),stu.assessmentsByDeadline());


    }
}
