package universitymodel;

import exception.BaseException;

public class Grade {

    private final int grade;
    private final Subject subject;

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

    public static class GradeException extends BaseException {

        public GradeException(String message) {
            super(message);
        }
    }
}
