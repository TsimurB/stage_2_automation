package universitymodel;

import exception.BaseException;

import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Group {

    private List<Student> students = new ArrayList<>();
    private final String groupId;

    public Group() {
        groupId = UUID.randomUUID().toString();
    }

    public List<Student> assignStudents(int numberOfStudents) {
        this.students = Stream.generate(Student::new)
                .limit(numberOfStudents)
                .collect(Collectors.toList());
        return students;
    }

    public List<Student> getStudents() {
        if (students.isEmpty()) {
            throw new Student.StudentException("List of students is empty");
        }
        return students;
    }

    public String getGroupId() {
        return groupId;
    }

    public static class GroupException extends BaseException {
        public GroupException(String message) {
            super(message);
        }
    }
}
