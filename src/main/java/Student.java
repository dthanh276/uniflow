import java.util.Set;
import java.util.HashSet;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class Student {
    private int id;
    private String name;
    private Set<Subject> subjects;
    private Map<Subject, List<Assessment>> assessments = new HashMap<>();

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.subjects = new HashSet<>();
    }

    public boolean enroll(Subject s) {
        return subjects.add(s);
    }

    public int subjectCount() {
        return subjects.size();
    }

    public void addAssessment(Subject subject, Assessment assessment) {
        if (!subjects.contains(subject))
            throw new IllegalArgumentException("Subject is not enrolled");
        List<Assessment> currentAssessments = assessments.get(subject);
        if (currentAssessments == null) {
            currentAssessments = new ArrayList<>();
            assessments.put(subject, currentAssessments);
        }
        currentAssessments.add(assessment);
    }

    public List<Assessment> assessmentsFor(Subject subject) {
        List<Assessment> assessmentsList = assessments.get(subject);
        if (assessmentsList == null)
            return new ArrayList<>();
        return new ArrayList<>(assessmentsList);
    }

    public List<Assessment> assessmentsByDeadline() {
        List<Assessment> allAssessments = new ArrayList<>();
        for (List<Assessment> list : assessments.values()) {
            allAssessments.addAll(list);
        }
        allAssessments
                .sort(Comparator.comparing(Assessment::getDueDate, Comparator.nullsLast(Comparator.naturalOrder())));
        return allAssessments;
    }

    public List<Assessment> assessmentsDueWithin(int days, LocalDate today) {
        List<Assessment> result = new ArrayList<>();
        LocalDate due = today.plusDays(days);
        for (Assessment assessment : assessmentsByDeadline()) {
            if (assessment.getDueDate() == null)
                continue;
            if (!assessment.getDueDate().isAfter(due) && !assessment.getDueDate().isBefore(today)) {
                result.add(assessment);
            }
        }
        return result;
    }

    public List<Assessment> assessmentsDueToday(LocalDate today) {
        List<Assessment> result = new ArrayList<>();
        for (List<Assessment> assessmentPerSubject : assessments.values()) {
            for (Assessment assessment : assessmentPerSubject) {
                if (assessment.getDueDate() == null)
                    continue;
                if (assessment.getDueDate().isEqual(today)) {
                    result.add(assessment);
                }
            }
        }
        return result;
    }
}
