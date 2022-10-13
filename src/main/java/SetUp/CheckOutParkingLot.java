package SetUp;

import ParkingLot.ParkingBuilding;
import Users.Client;
import Users.Manager;

import static SetUp.CreateData.createList;

public class CheckOutParkingLot extends RunExample {

    @Override
    public void run(Manager manager, ParkingBuilding parkingBuilding, Client client) {
        // TODO 增删查 停车场  结果是增加了3个停车场
        //添加停车场
        for (String e : createList(1,5,"停车场")) {
            manager.addParkingLot(e, 3);
        }
        //展示停车场
        manager.listParkingLotList(parkingBuilding);

        //删除停车场
        for (String e:createList(2,3,"停车场")){
            manager.removeParkingLot(e);
        }
        //展示停车场
        manager.listParkingLotList(parkingBuilding);
//
//        //经理添加停车场 重复停车场、重新使用停车场
//        for (String e:createList(1,3,"停车场")){
//            manager.addParkingLot(e,5);
//        }
//        //展示停车场
//        manager.listParkingLotList(parkingBuilding);
    }

}
