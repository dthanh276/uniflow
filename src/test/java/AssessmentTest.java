import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AssessmentTest {
    @Test 
    void weightedScore_isCorrect(){
        Assessment a = new Assessment("Quiz",20.0, 80.0);
        assertEquals(16.0,a.getWeightedScore(), 0.0001);
    }
    
    @Test
    void constructor_rejectsBadWeight(){
        assertThrows(IllegalArgumentException.class,() -> new Assessment("Quiz",150.0, 80.0));
    }

    @Test
    void constructor_rejectsBlankTitle(){
        assertThrows(IllegalArgumentException.class,() -> new Assessment("",20.0, 80.0));
    }

    @Test
    void constructor_rejectsBadMark(){
        assertThrows(IllegalArgumentException.class,() -> new Assessment("Quiz",20.0, 150.0));
    }
    @Test
    void weightedScore_throwsWhenUnmarked(){
        Assessment a = new Assessment("Exam",50.0,null);
        assertThrows(IllegalStateException.class,() -> a.getWeightedScore());
    }
}

