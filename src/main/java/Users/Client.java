package Users;

import Vehicle.Vehicle;


public class Client {
    private final String name;
    private final Vehicle vehicle;

    //谁为他服务
    private final Staff staff;

    public Client(String name, Vehicle vehicle, Staff staff) {
        this.name = name;
        this.vehicle = vehicle;
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public String getName() {
        return name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


}
