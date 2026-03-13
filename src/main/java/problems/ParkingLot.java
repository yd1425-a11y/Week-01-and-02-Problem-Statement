package main.java.problems;

public class ParkingLot {

    private ParkingSpot[] table;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        table = new ParkingSpot[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new ParkingSpot();
        }
    }

    // Hash function
    private int hash(String licensePlate) {
        return Math.abs(licensePlate.hashCode()) % capacity;
    }

    // Park vehicle
    public int parkVehicle(String licensePlate) {

        int index = hash(licensePlate);
        int probes = 0;

        while (table[index].occupied) {
            index = (index + 1) % capacity;
            probes++;
        }

        table[index].licensePlate = licensePlate;
        table[index].entryTime = System.currentTimeMillis();
        table[index].occupied = true;

        System.out.println(
                "Vehicle " + licensePlate +
                " parked at spot " + index +
                " (" + probes + " probes)"
        );

        return index;
    }

    // Exit vehicle
    public void exitVehicle(String licensePlate) {

        int index = hash(licensePlate);

        while (table[index].occupied) {

            if (table[index].licensePlate.equals(licensePlate)) {

                long duration =
                        System.currentTimeMillis() - table[index].entryTime;

                table[index].occupied = false;

                System.out.println(
                        "Vehicle " + licensePlate +
                        " exited. Duration: " +
                        duration / 1000 + " seconds"
                );

                return;
            }

            index = (index + 1) % capacity;
        }

        System.out.println("Vehicle not found.");
    }
}