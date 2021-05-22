package universitymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Student {

    private List<Subject> subjects = new ArrayList<>();
    private final List<Grade> grades = new ArrayList<>();

    public void giveGrade(Subject subject, int grade) {
        if (grade < 0 || grade > 10) {
            throw new ArithmeticException(String.format("Grade %s is out of range %s!!!", grade, "from 0 to 10"));
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
            throw new NoSuchElementException("List of subjects is empty");
        }
        return subjects;
    }
}

