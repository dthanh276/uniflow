import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
    List<Assessment> assessments = new ArrayList<>();
    
    assessments.add(new Assessment("Quiz",20.0, 80.0,null));
    assessments.add(new Assessment("Assignment",30.0, 60.0,null));
    assessments.add(new Assessment("Exam",50.0, null,null));

    double achieved = 0;
    double stillAvailable = 0;
    for (Assessment assessment:assessments){
        if (assessment.isMarked()) {
            achieved = achieved + assessment.getWeightedScore();
        } else {
            stillAvailable = stillAvailable + assessment.getWeightPercent();
        }
    }
    double highest = achieved + stillAvailable;
    System.out.printf("achieved = %.1f and highest score achiev = %.1f%n", achieved,highest);
    }
}
