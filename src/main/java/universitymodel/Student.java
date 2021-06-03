package universitymodel;

import exception.BaseException;

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
            throw new Grade.GradeException(String.format("Grade %s is out of range %s!!!", grade, "from 0 to 10"));
        }
        this.grades.add(new Grade(subject, grade));
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void assignSubject(List<Subject> subjects) {

        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        if (subjects.isEmpty()) {
            throw new Subject.SubjectException("List of subjects is empty");
        }
        return subjects;
    }

    public static class StudentException extends BaseException {
        public StudentException(String message) {
            super(message);
        }
    }
}

