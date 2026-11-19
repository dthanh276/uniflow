import java.util.Set;
import java.util.HashSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Student {
    private int id;
    private String name;
    private Set<Subject> subjects;
    private Map <Subject, List<Assessment>> assessments = new HashMap<>();

    public Student(int id, String name){
        this.id = id;
        this.name = name;
        this.subjects = new HashSet<>();
    }

    public boolean enroll(Subject s){
        return subjects.add(s);
    }

    public int subjectCount(){
        return subjects.size();
    }

    public void addAssessment(Subject subject, Assessment assessment){
        List<Assessment> currentAssessments = assessments.get(subject);
        if (currentAssessments == null){
            currentAssessments = new ArrayList<>();
            assessments.put(subject,currentAssessments);
        }
        currentAssessments.add(assessment);
    }

    public List<Assessment> assessmentsFor(Subject subject){
        List <Assessment> assessmentsList = assessments.get(subject);
        if(assessmentsList == null) return new ArrayList<>();
        return new ArrayList<>(assessmentsList);
    }
}
