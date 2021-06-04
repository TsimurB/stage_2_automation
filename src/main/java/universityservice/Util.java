package universityservice;

import java.util.Collection;

public abstract class Util {

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
