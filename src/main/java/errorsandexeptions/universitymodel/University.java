package errorsandexeptions.universitymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class University {

    private List<Faculty> faculties = new ArrayList<>();

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
