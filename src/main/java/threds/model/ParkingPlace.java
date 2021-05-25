package threds.model;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingPlace {

    private static final int NUMBER_OF_PARK_PLACES = 6;
    private static final int NUMBER_OF_CARS = 10;
    private static final int TIME_OF_WAIT_SECONDS = 5;

    private final Vector<ParkingPlace> parkingPlaces;
    private final List<Car> cars;


    public ParkingPlace() {
        this.parkingPlaces = Stream.generate(ParkingPlace::new)
                .limit(NUMBER_OF_PARK_PLACES)
                .collect(Collectors.toCollection(Vector::new));
        this.cars = Stream.generate(Car::new)
                .limit(NUMBER_OF_CARS)
                .collect(Collectors.toList());
    }

    public synchronized void parkCar(){
        if(!parkingPlaces.isEmpty()){
            parkingPlaces.remove(0);
            cars.remove(0);
        }
    }

}
