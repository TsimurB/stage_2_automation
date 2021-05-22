package universitymodel;

public class Grade {
    private int grade;
    private Subject subject;

    public Grade(Subject subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }
}
