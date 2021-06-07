package universitymodel;

import exception.FacultyException;
import exception.StudentException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class University {

    private List<Faculty> faculties = new ArrayList<>();

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double calculateAvgForUniversity() {
        return getFaculties().stream()
                .map(Faculty::getGroups)
                .flatMap(Collection::stream)
                .map(group -> {
                    try {
                        return group.getStudents();
                    } catch (StudentException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .flatMap(Collection::stream)
                .map(Student::getGrades)
                .flatMap(Collection::stream)
                .mapToInt(Grade::getGrade)
                .average()
                .getAsDouble();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double calculateAvgConcrete(Faculty faculty, Group group, Subject subject) throws StudentException {
        Faculty faculty1 = getFaculties().stream()
                .filter(f -> f.getFacultyId().equals(faculty.getFacultyId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Faculty with ID %s is not find", faculty.getFacultyId())));
        Group group1 = faculty1.getGroups().stream()
                .filter(g -> g.getGroupId().equals(group.getGroupId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Group with ID %s is not find", group.getGroupId())));
        return group1.getStudents().stream()
                .map(Student::getGrades)
                .flatMap(Collection::stream)
                .filter(g -> g.getSubject().getSubjectId().equals(subject.getSubjectId()))
                .mapToInt(Grade::getGrade)
                .average()
                .getAsDouble();
    }
    public List<Faculty> getFaculties() {
        if (faculties.isEmpty()) {
            throw new FacultyException("List of faculties is empty");
        }
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
