import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class AssessmentTest {
    @Test
    void weightedScore_isCorrect() {
        Assessment a = new Assessment("Quiz", 20.0, 80.0, null);
        assertEquals(16.0, a.getWeightedScore(), 0.0001);
    }

    @Test
    void constructor_rejectsBadWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Assessment("Quiz", 150.0, 80.0, null));
    }

    @Test
    void constructor_rejectsBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Assessment("", 20.0, 80.0, null));
    }

    @Test
    void constructor_rejectsBadMark() {
        assertThrows(IllegalArgumentException.class, () -> new Assessment("Quiz", 20.0, 150.0, null));
    }

    @Test
    void weightedScore_throwsWhenUnmarked() {
        Assessment a = new Assessment("Exam", 50.0, null, null);
        assertThrows(IllegalStateException.class, () -> a.getWeightedScore());
    }

    @Test
    void hasDueDate_trueWhenDateGiven() {
        Assessment a = new Assessment("Quiz", 20.0, 80.0, LocalDate.of(2026, 12, 1));
        assertTrue(a.hasDueDate());
    }

    @Test
    void hasDueDate_falseWhenNull() {
        Assessment a = new Assessment("Quiz", 20.0, 80.0, null);
        assertFalse(a.hasDueDate());
    }

    @Test
    void daysUntilDue_countsDays() {
        Assessment a = new Assessment("Quiz", 20.0, 80.0, LocalDate.of(2026, 12, 1));
        assertEquals(13, a.daysUntilDue(LocalDate.of(2026, 11, 18)));
    }

    @Test
    void daysUntilDue_negativeWhenPast() {
        Assessment a = new Assessment("Quiz", 20.0, 80.0, LocalDate.of(2026, 12, 1));
        assertEquals(-7, a.daysUntilDue(LocalDate.of(2026, 12, 8)));
    }

    @Test
    void daysUntilDue_throwsWhenNoDueDate() {
        Assessment a = new Assessment("Quiz", 20.0, 80.0, null);
        assertThrows(IllegalStateException.class, () -> a.daysUntilDue(LocalDate.of(2026, 11, 18)));
    }
}
