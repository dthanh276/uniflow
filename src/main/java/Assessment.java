import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assessment {
    private String title;
    private double weightPercent;
    private Double mark;
    private LocalDate dueDate;


    public Assessment(String title, double weightPercent, Double mark, LocalDate dueDate) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("please enter valid name");
        }
        if (weightPercent < 0 || weightPercent > 100) {
            throw new IllegalArgumentException("please enter valid percent (0-100)");
        }

        if (mark != null && (mark < 0 || mark > 100)) {
            throw new IllegalArgumentException("please enter valid mark");
        }

        this.title = title;
        this.weightPercent = weightPercent;
        this.mark = mark;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public double getWeightPercent() {
        return weightPercent;
    }

    public Double getMark() {
        return mark;
    }

    public boolean isMarked() {
        if (getMark() == null) {
            return false;
        }
        return true;
    }

    public double getWeightedScore() {
        if (isMarked() == false) {
            throw new IllegalStateException("There is no mark yet");
        }
        double weightedScore = getMark() * weightPercent / 100;
        return weightedScore;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean hasDueDate() {
        if (getDueDate() == null) {
            return false;
        }
        return true;
    }

    public long daysUntilDue(LocalDate today) {
        if (!hasDueDate()) {
            throw new IllegalStateException("There is no due date");
        }
        return ChronoUnit.DAYS.between(today, dueDate);

    }
}
