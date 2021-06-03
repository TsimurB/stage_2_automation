package universitymodel;

import exception.BaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Faculty {

    private List<Group> groups = new ArrayList<>();
    private final String facultyId;

    public Faculty() {
        facultyId = UUID.randomUUID().toString();
    }

    public List<Group> createGroups(int numberOfGroups) {
        this.groups = Stream.generate(Group::new)
                .limit(numberOfGroups)
                .collect(Collectors.toList());
        return groups;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public static class FacultyException extends BaseException {

        public FacultyException(String message) {
            super(message);
        }
    }

}
