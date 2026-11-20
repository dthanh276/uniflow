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
                LocalDate.of(2026, 12, 1));
        Assessment a3 = new Assessment("Quiz3",
                40.0,
                null,
                null);
        stu.addAssessment(s1, a1);
        stu.addAssessment(s1, a3);
        stu.addAssessment(s2, a2);

        assertEquals(List.of(a2, a1, a3), stu.assessmentsByDeadline());
    }

    @Test
    void assessmentsDueWithin_includesOnlyUpcomingWithinDays() {
        Student stu = new Student(1, "Harry");
        Subject sub = new Subject("COMP2001", "DSA");
        stu.enroll(sub);
        LocalDate today = LocalDate.of(2026, 11, 20);
        Assessment dueIn3 = new Assessment("quiz1", 40.0, null, LocalDate.of(2026, 11, 23));
        Assessment dueIn5 = new Assessment("quiz2", 40.0, null, LocalDate.of(2026, 11, 25));
        Assessment dueIn10 = new Assessment("quiz3", 40.0, null, LocalDate.of(2026, 11, 30));
        Assessment overdue = new Assessment("quiz4", 40.0, null, LocalDate.of(2026, 11, 18));
        Assessment noDate = new Assessment("quiz5", 40.0, null, null);
        stu.addAssessment(sub, dueIn10);
        stu.addAssessment(sub, dueIn5);
        stu.addAssessment(sub, overdue);
        stu.addAssessment(sub, noDate);
        stu.addAssessment(sub, dueIn3);

        assertEquals(List.of(dueIn3, dueIn5), stu.assessmentsDueWithin(7, today));
    }

    @Test
    void assessmentsDueWithin_includesAssessmentDueExactlyOnLastDay() {
        Student stu = new Student(1, "Harry");
        Subject sub = new Subject("COMP2001", "DSA");
        stu.enroll(sub);
        LocalDate today = LocalDate.of(2026, 11, 20);
        Assessment a = new Assessment("quiz1", 40.0, null, LocalDate.of(2026, 11, 27));
        stu.addAssessment(sub, a);

        assertEquals(List.of(a), stu.assessmentsDueWithin(7, today));
    }

    @Test
    void assessmentsDueWithin_includesAssessmentDueToday() {
        Student stu = new Student(1, "Harry");
        Subject sub = new Subject("COMP2001", "DSA");
        stu.enroll(sub);
        LocalDate today = LocalDate.of(2026, 11, 20);
        Assessment a = new Assessment("quiz1", 40.0, null, LocalDate.of(2026, 11, 20));
        stu.addAssessment(sub, a);

        assertEquals(List.of(a), stu.assessmentsDueWithin(0, today));
    }

    @Test
    void assessmentsDueToday_returnsOnlyAssessmentsDueToday() {
        Student stu = new Student(1, "Harry");
        Subject sub = new Subject("COMP2001", "DSA");
        Assessment a1 = new Assessment("quiz1", 40.0, null, LocalDate.of(2026, 11, 23));
        Assessment a2 = new Assessment("quiz2", 40.0, null, LocalDate.of(2026, 11, 24));
        Assessment a3 = new Assessment("quiz3", 40.0, null, null);
        LocalDate today = LocalDate.of(2026, 11, 23);
        stu.enroll(sub);
        stu.addAssessment(sub, a1);
        stu.addAssessment(sub, a2);
        stu.addAssessment(sub, a3);

        assertEquals(List.of(a1), stu.assessmentsDueToday(today));

    }

}
