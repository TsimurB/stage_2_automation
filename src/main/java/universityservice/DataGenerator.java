package universityservice;

import universitymodel.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {
    public static List<Student> assignStudents(Group group, int numberOfStudents) {
        List<Student> students = Stream.generate(Student::new)
                .limit(numberOfStudents)
                .collect(Collectors.toList());
        group.setStudents(students);
        return students;
    }


    public static List<Faculty> createFaculties(University university, int numberOfFaculties) {
        List<Faculty> faculties = Stream.generate(Faculty::new)
                .limit(numberOfFaculties)
                .collect(Collectors.toList());
        university.setFaculties(faculties);
        return faculties;
    }

    public static List<Subject> createSubject(int numberOfSubject) {
        return Stream.generate(Subject::new)
                .limit(numberOfSubject)
                .collect(Collectors.toList());
    }
    public static List<Group> createGroups(Faculty faculty, int numberOfGroups) {
        List<Group> groups = Stream.generate(Group::new)
                .limit(numberOfGroups)
                .collect(Collectors.toList());
        faculty.setGroups(groups);
        return groups;
    }

}
