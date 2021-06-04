package universitymodel;

import exception.GradeException;
import exception.SubjectException;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private List<Subject> subjects = new ArrayList<>();
    private final List<Grade> grades = new ArrayList<>();

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double calculateAvgAllSubjects() {
        return getGrades().stream()
                .mapToInt(Grade::getGrade)
                .average()
                .getAsDouble();
    }

    public void giveGrade(Subject subject, int grade) {
        if (grade < 0 || grade > 10) {
            throw new GradeException(String.format("Grade %s is out of range %s!!!", grade, "from 0 to 10"));
        }
        this.grades.add(new Grade(subject, grade));
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        if (subjects.isEmpty()) {
            throw new SubjectException("List of subjects is empty");
        }
        return subjects;
    }
}

