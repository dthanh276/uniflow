import java.util.Set;
import java.util.HashSet;


public class Student {
    private int id;
    private String name;
    private Set<Subject> subjects;

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
}
