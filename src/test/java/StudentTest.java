import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class StudentTest {
    @Test
    void enrolSameSubjectTwice_countsOnce() {
        Student st = new Student(1,
                "Harry");
        st.enroll(new Subject("COMP101", "Intro"));
        st.enroll(new Subject("COMP101", "Intro"));
        assertEquals(1, st.subjectCount());
    }
}
