package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParkingTest {

    @Ignore
    @Test
    public void whenEnoughPlace() {
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Car car3 = new Truck(2);
        Car car4 = new Truck(3);
        Car car5 = new Truck(4);
        List<Car> variant1 = List.of(car1, car2, car3, car4);
        List<Car> variant2 = List.of(car3, car4, car5);
        Parking parking = new Parking(2, 2);
        assertTrue(parking.park(variant1));
        assertTrue(parking.park(variant2));

    }

    @Ignore
    @Test public void whenNotEnoughPlace() {
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Car car3 = new Truck(2);
        Car car4 = new Truck(3);
        Car car5 = new Truck(4);
        List<Car> variant1 = List.of(car1, car2, car3, car4);
        List<Car> variant2 = List.of(car3, car4, car5);
        Parking parking = new Parking(1, 2);
        assertFalse(parking.park(variant1));
        assertFalse(parking.park(variant2));
    }

    @Ignore
    @Test
    public void whenNoPassPlaces() {
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        List<Car> variant1 = List.of(car1, car2);
        Parking parking = new Parking(0, 2);
        assertFalse(parking.park(variant1));
    }

}