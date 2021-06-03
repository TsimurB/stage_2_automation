package universitymodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class University {

    private List<Faculty> faculties = new ArrayList<>();

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double calculateAvgForUniversity() {
        return getFaculties().stream()
                .map(Faculty::getGroups)
                .flatMap(Collection::stream)
                .map(Group::getStudents)
                .flatMap(Collection::stream)
                .map(Student::getGrades)
                .flatMap(Collection::stream)
                .mapToInt(Grade::getGrade)
                .average()
                .getAsDouble();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double calculateAvgConcrete(Faculty faculty, Group group, Subject subject) {
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

    public List<Faculty> createFaculties(int numberOfFaculties) {
        this.faculties = Stream.generate(Faculty::new)
                .limit(numberOfFaculties)
                .collect(Collectors.toList());
        return faculties;
    }

    public List<Subject> createSubject(int numberOfSubject) {
        return Stream.generate(Subject::new)
                .limit(numberOfSubject)
                .collect(Collectors.toList());
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }
}
