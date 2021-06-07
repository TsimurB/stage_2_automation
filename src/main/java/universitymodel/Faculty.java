package universitymodel;

import exception.GroupException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Faculty {

    private List<Group> groups = new ArrayList<>();
    private final String facultyId;

    public Faculty() {
        facultyId = UUID.randomUUID().toString();
    }

    public String getFacultyId() {
        return facultyId;
    }

    public List<Group> getGroups() {
        if (groups.isEmpty()) {
            throw new GroupException("List of groups is empty");
        }
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
