package ParkingLot;

import Vehicle.Vehicle;

public class ParkingSpot {

    // spot ➡️level ➡️lot 最好由小到大进行编写
    //写上 一个停车点所具有的所有属性
    //时间、地点、人物（在此情景 人物是车辆）

    //地点
    private ParkingBuilding parkingBuilding;
    private ParkingLot parkingLot;
    private int spotNo;
    private boolean aviliable;//该车位是否有空

    //人物（车）
    private Vehicle vehicle;


    public ParkingSpot(int spotNo, boolean aviliable) {
        this.spotNo = spotNo;
        this.aviliable = aviliable;
    }

    public ParkingBuilding getParkingBuilding() {
        return parkingBuilding;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingBuilding(ParkingBuilding parkingBuilding) {
        this.parkingBuilding = parkingBuilding;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setSpotNo(int spotNo) {
        this.spotNo = spotNo;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSpotNo() {
        return spotNo;
    }

    public boolean isAviliable() {
        return aviliable;
    }

    public void setAviliable(boolean aviliable) {
        this.aviliable = aviliable;
    }

}
