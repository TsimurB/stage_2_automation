package universityservice;

import universitymodel.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class UniversityService {

    private static final int NUMBER_OF_SUBJECTS = 6;
    private static final int NUMBER_OF_STUDENTS = 10;
    private static final int NUMBER_OF_FACULTIES = 3;
    private static final int NUMBER_OF_GROUPS = 4;
    private static final Function<University, Faculty> randomFacultyGenerator = university -> getRandom(university.getFaculties());
    private static final Function<Faculty, Group> randomGroupGenerator = faculty -> getRandom(faculty.getGroups());
    private static final Function<Group, Student> randomStudentGenerator = group -> getRandom(group.getStudents());
    private static final Function<Student, Subject> randomSubjectGenerator = student -> getRandom(student.getSubjects());


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

        Student student = randomStudentGenerator.apply(
                randomGroupGenerator.apply(
                        randomFacultyGenerator.apply(university)
                )
        );

        System.out.println(student.calculateAvgAllSubjects());

        Faculty faculty = randomFacultyGenerator.apply(university);
        Group group = randomGroupGenerator.apply(faculty);
        Subject subject = randomSubjectGenerator.apply(
                randomStudentGenerator.apply(group)

        );


        System.out.println(university.calculateAvgConcrete(faculty, group, subject));
        System.out.println(university.calculateAvgForUniversity());

        try {
            simulateExceptionForIncorrectGrade(university);
        } catch (Grade.GradeException exception) {
            System.out.println("GradeException was caught!");
        }

        try {
            simulateExceptionForEmptySubjects(university);
        } catch (Subject.SubjectException exception) {
            System.out.println("SubjectException was caught for simulateExceptionForEmptySubjects!");
        }

        try {
            simulateExceptionForEmptyStudents(university);
        } catch (Student.StudentException exception) {
            System.out.println("StudentException was caught for simulateExceptionForEmptyStudents!");
        }

        try {
            simulateExceptionForEmptyGroups(university);
        } catch (Group.GroupException exception) {
            System.out.println("GroupException was caught for simulateExceptionForEmptyGroups!");
        }

        try {
            simulateExceptionForEmptyFaculties();
        } catch (Faculty.FacultyException exception) {
            System.out.println("FacultyException was caught for simulateExceptionForEmptyFaculties!");
        }
    }

    public static void simulateExceptionForIncorrectGrade(University university) {
        university.createFaculties(NUMBER_OF_FACULTIES)
                .forEach(faculty -> faculty.createGroups(NUMBER_OF_GROUPS)
                        .forEach(group -> group.assignStudents(NUMBER_OF_STUDENTS)
                                .forEach(student -> {
                                            student.assignSubject(university.createSubject(100));
                                            student.getSubjects()
                                                    .forEach(subject -> student.giveGrade(subject, getRandomGrade(-100, 100)));
                                        }
                                )));
    }

    public static void simulateExceptionForEmptySubjects(University university) {
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .createGroups(NUMBER_OF_GROUPS)
                .forEach(group -> group.assignStudents(NUMBER_OF_STUDENTS)
                        .forEach(Student::getSubjects)));
    }

    public static void simulateExceptionForEmptyStudents(University university) {
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .createGroups(NUMBER_OF_GROUPS)
                .forEach(Group::getStudents));
    }

    public static void simulateExceptionForEmptyGroups(University university) {
        university.createFaculties(NUMBER_OF_FACULTIES).forEach(faculty -> faculty
                .getGroups()
                .stream()
                .findFirst()
                .orElseThrow(() -> new Group.GroupException("List of groups is empty")));
    }

    public static void simulateExceptionForEmptyFaculties() {
        new University().getFaculties()
                .stream()
                .findFirst()
                .orElseThrow(() -> new Faculty.FacultyException("List of faculties is empty"));
    }

    public static int getRandomGrade(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static <E> E getRandom(Collection<E> collection) {
        return collection.stream()
                .skip((long) (collection.size() * Math.random()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Collection is empty"));
    }
}
