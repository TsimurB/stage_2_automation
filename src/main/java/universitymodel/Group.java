package universitymodel;

import exception.StudentException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private List<Student> students = new ArrayList<>();
    private final String groupId;

    public Group() {
        groupId = UUID.randomUUID().toString();
    }

    public List<Student> getStudents() {
        if (students.isEmpty()) {
            throw new StudentException("List of students is empty");
        }
        return students;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
