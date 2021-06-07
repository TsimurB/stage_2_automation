package universityservice;

import exception.*;
import universitymodel.*;

import java.util.List;

import static universityservice.DataGenerator.*;
import static universityservice.Util.getRandomGrade;

public class UniversityService {

    private static final int NUMBER_OF_SUBJECTS = 6;
    private static final int NUMBER_OF_STUDENTS = 10;
    private static final int NUMBER_OF_FACULTIES = 3;
    private static final int NUMBER_OF_GROUPS = 4;

    public static void main(String[] args) {
        University university = new University();
        List<Subject> subjects = createSubject(NUMBER_OF_SUBJECTS);
        createFaculties(university, NUMBER_OF_FACULTIES).forEach(faculty -> createGroups(faculty, NUMBER_OF_GROUPS)
                .forEach(group -> assignStudents(group, NUMBER_OF_STUDENTS)
                        .forEach(student -> {
                                    student.setSubjects(subjects);
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
        } catch (GradeException exception) {
            System.out.println("GradeException was caught!");
        }

        try {
            simulateExceptionForEmptySubjects(university);
        } catch (SubjectException exception) {
            System.out.println("SubjectException was caught for simulateExceptionForEmptySubjects!");
        }

        try {
            simulateExceptionForEmptyStudents(university);
        } catch (StudentException exception) {
            System.out.println("StudentException was caught for simulateExceptionForEmptyStudents!");
        }

        try {
            simulateExceptionForEmptyGroups(university);
        } catch (GroupException exception) {
            System.out.println("GroupException was caught for simulateExceptionForEmptyGroups!");
        }

        try {
            simulateExceptionForEmptyFaculties();
        } catch (FacultyException exception) {
            System.out.println("FacultyException was caught for simulateExceptionForEmptyFaculties!");
        }
    }

    public static void simulateExceptionForIncorrectGrade(University university) {
        createFaculties(university, NUMBER_OF_FACULTIES)
                .forEach(faculty -> createGroups(faculty, NUMBER_OF_GROUPS)
                        .forEach(group -> assignStudents(group, NUMBER_OF_STUDENTS)
                                .forEach(student -> {
                                            student.setSubjects(createSubject(100));
                                            student.getSubjects()
                                                    .forEach(subject -> student.giveGrade(subject, getRandomGrade(-100, 100)));
                                        }
                                )));
    }

    public static void simulateExceptionForEmptySubjects(University university) {
        createFaculties(university, NUMBER_OF_FACULTIES).forEach(faculty -> createGroups(faculty, NUMBER_OF_GROUPS)
                .forEach(group -> assignStudents(group, NUMBER_OF_STUDENTS)
                        .forEach(Student::getSubjects)));
    }

    public static void simulateExceptionForEmptyStudents(University university) {
        createFaculties(university, NUMBER_OF_FACULTIES).forEach(faculty -> createGroups(faculty, NUMBER_OF_GROUPS)
                .forEach(Group::getStudents));
    }

    public static void simulateExceptionForEmptyGroups(University university) {
        createFaculties(university, NUMBER_OF_FACULTIES).get(0).getGroups();
    }

    public static void simulateExceptionForEmptyFaculties() {
        new University().getFaculties();
    }
}
