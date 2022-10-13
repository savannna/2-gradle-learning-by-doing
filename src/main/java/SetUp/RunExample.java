package SetUp;

import ParkingLot.ParkingBuilding;
import Users.Client;
import Users.Manager;

public abstract class RunExample {

    abstract void run(Manager
                      manager, ParkingBuilding parkingBuilding, Client client) throws InterruptedException;
}
