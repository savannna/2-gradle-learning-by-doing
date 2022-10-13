import ParkingLot.ParkingBuilding;
import SetUp.CheckOutBoyService;
import SetUp.CheckOutManagerService;
import SetUp.CheckOutParkingBoy;
import SetUp.CheckOutParkingLot;
import Users.Client;
import Users.Manager;

import java.util.ArrayList;

public class Main {
    // yyx: main 方法有些长，想想有什么重构的办法呢？
    public static void main(String[] args) throws InterruptedException {
        //初始化parkingBuilding
        ParkingBuilding parkingBuilding = new ParkingBuilding("BuildingLee", new ArrayList<>());
        //初始化经理
        Manager manager = new Manager(new ArrayList<>(), "停车场经理", parkingBuilding);
        //初始化用户
        Client client = null;

        //初始化运行内容
        CheckOutParkingBoy parkingBoy = new CheckOutParkingBoy();
        CheckOutParkingLot parkingLot = new CheckOutParkingLot();
        CheckOutManagerService managerService = new CheckOutManagerService();
        CheckOutBoyService boyService = new CheckOutBoyService();

        parkingBoy.run(manager, parkingBuilding, client);
        parkingLot.run(manager, parkingBuilding, client);
        managerService.run(manager, parkingBuilding, client);
        boyService.run(manager, parkingBuilding, client);
    }
}
