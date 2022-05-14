package ru.job4j.ood.lsp.parking;

import org.junit.Test;


import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {


    @Test
    public void whenEnoughPlace() {
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Car car3 = new Truck(2);
        Car car4 = new Truck(3);
        Parking parking = new Parking(2, 2);
        assertTrue(parking.park(car1));
        assertTrue(parking.park(car2));
        assertTrue(parking.park(car3));
        assertTrue(parking.park(car4));
    }


    @Test
    public void whenEnoughPlaceButSomeTrucksIsOnThePassengersPlaces() {
        Car car3 = new Truck(2);
        Car car4 = new Truck(3);
        Car car5 = new Truck(4);
        Parking parking = new Parking(2, 2);
        assertTrue(parking.park(car5));
        assertTrue(parking.park(car4));
        assertTrue(parking.park(car3));
    }


    @Test public void whenNotEnoughPlace() {
        Car car1 = new PassengerCar();
        Car car2 = new PassengerCar();
        Car car3 = new Truck(2);
        Car car4 = new Truck(3);
        Car car5 = new Truck(4);
        Parking parking = new Parking(1, 2);
        assertTrue(parking.park(car1));
        assertTrue(parking.park(car3));
        assertTrue(parking.park(car4));
        assertFalse(parking.park(car5));
    }


    @Test
    public void whenNoPassengerCarPlaces() {
        Car car1 = new PassengerCar();
        Parking parking = new Parking(0, 2);
        assertFalse(parking.park(car1));
    }


    @Test
    public void checkParkListAfterParking() {
        List<Car> cars
                = List.of(new PassengerCar(), new PassengerCar(), new Truck(2), new Truck(3));
        Parking parking = new Parking(2, 2);
        cars.forEach(parking::park);
        assertThat(parking.getParkCells().size(), is(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTruckSizeIsOne() {
        Car car = new Truck(1);
        Parking parking = new Parking(0, 1);
        parking.park(car);
    }

}