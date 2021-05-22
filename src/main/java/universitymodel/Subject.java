package universitymodel;

import java.util.UUID;

public class Subject {
    //    private Student student;

    private String subjectId;

    public Subject() {
        subjectId = UUID.randomUUID().toString();
    }

    public String getSubjectId() {
        return subjectId;
    }
}
