package universitymodel;

import exception.BaseException;

import java.util.UUID;

public class Subject {

    private final String subjectId;

    public Subject() {
        subjectId = UUID.randomUUID().toString();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public static class SubjectException extends BaseException {
        public SubjectException(String message) {
            super(message);
        }
    }
}
