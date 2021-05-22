package universityservice;

import universitymodel.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class UniversityService {

    private static final int NUMBER_OF_SUBJECTS = 6;
    private static final int NUMBER_OF_STUDENTS = 10;
    private static final int NUMBER_OF_FACULTIES = 3;
    private static final int NUMBER_OF_GROUPS = 4;

    public static void main(String[] args) {
        University university = new University();
        List<Subject> subjects = university.createSubject(NUMBER_OF_SUBJECTS);
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .createGroups(NUMBER_OF_GROUPS)
                .forEach(group -> group.assignStudents(NUMBER_OF_STUDENTS)
                        .forEach(student -> {
                                    student.assignSubject(subjects);
                                    student.getSubjects()
                                            .forEach(subject -> student.giveGrade(subject, getRandomGrade(0, 10)));
                                }
                        )));

        Student student = university.getFaculties().get(0).getGroups().get(0).getStudents().get(0);
        System.out.println(calculateAvgAllSubjects(student));
        System.out.println(calculateAvgConcrete(university, university.getFaculties().get(0),
                university.getFaculties().get(0).getGroups().get(0),
                university.getFaculties().get(0).getGroups().get(0).getStudents().get(0).getSubjects().get(0)));
        System.out.println(calculateAvgForUniversity(university));

        try {
            simulateExceptionForIncorrectGrade(university);
        } catch (ArithmeticException exception) {
            System.out.println("ArithmeticException was caught!");
        }

        try {
            simulateExceptionForEmptySubjects(university);
        } catch (NoSuchElementException exception) {
            System.out.println("NoSuchElementException was caught for simulateExceptionForEmptySubjects!");
        }

        try {
            simulateExceptionForEmptyStudents(university);
        } catch (NoSuchElementException exception) {
            System.out.println("NoSuchElementException was caught for simulateExceptionForEmptyStudents!");
        }

        try {
            simulateExceptionForEmptyGroups(university);
        } catch (NoSuchElementException exception) {
            System.out.println("NoSuchElementException was caught for simulateExceptionForEmptyGroups!");
        }

        try {
            simulateExceptionForEmptyFaculties();
        } catch (NoSuchElementException exception) {
            System.out.println("NoSuchElementException was caught for simulateExceptionForEmptyFaculties!");
        }
    }

    private static double calculateAvgAllSubjects(Student student) {
        return student.getGrades().stream().mapToInt(Grade::getGrade)
                .average().getAsDouble();
    }

    private static double calculateAvgConcrete(University university, Faculty faculty, Group group, Subject subject) {
        Faculty faculty1 = university.getFaculties().stream()
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


    private static double calculateAvgForUniversity(University university) {
        return university.getFaculties().stream()
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

    public static void simulateExceptionForIncorrectGrade(University university) throws ArithmeticException {
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .createGroups(NUMBER_OF_GROUPS)
                .forEach(group -> group.assignStudents(NUMBER_OF_STUDENTS)
                        .forEach(student -> {
                                    student.assignSubject(university.createSubject(100));
                                    student.getSubjects()
                                            .forEach(subject -> student.giveGrade(subject, getRandomGrade(-100, 100)));
                                }
                        )));
    }

    public static void simulateExceptionForEmptySubjects(University university) throws NoSuchElementException {
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .createGroups(NUMBER_OF_GROUPS)
                .forEach(group -> group.assignStudents(NUMBER_OF_STUDENTS)
                        .forEach(student -> student.getSubjects()
                                .forEach(subject -> student.giveGrade(subject, getRandomGrade(-100, 100)))
                        )));
    }

    public static void simulateExceptionForEmptyStudents(University university) throws NoSuchElementException {
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .createGroups(NUMBER_OF_GROUPS)
                .forEach(Group::getStudents));
    }

    public static void simulateExceptionForEmptyGroups(University university) throws NoSuchElementException {
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .getGroups()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("List of groups is empty")));
    }

    public static void simulateExceptionForEmptyFaculties() throws NoSuchElementException {
        new University().getFaculties()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("List of faculties is empty"));
    }

    public static int getRandomGrade(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
