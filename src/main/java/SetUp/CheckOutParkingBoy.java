package SetUp;

import ParkingLot.ParkingBuilding;
import Users.Client;
import Users.Manager;

import static SetUp.CreateData.createList;


public class CheckOutParkingBoy extends RunExample{

    @Override
    public void run(Manager manager, ParkingBuilding parkingBuilding, Client client) {
        // TODO 增删查 小弟  结果是增加了3个小弟
        // 经理添加小弟
        for (String e:createList(1,7,"小弟")){
            manager.addParkingBoy(e);
        }
        //展示小弟
        manager.listBoyList();
        //经理开除小弟
        for (String e:createList(2,3,"小弟")){
            manager.removeParkingBoy(e);
        }
        //展示小弟
        manager.listBoyList();
//
//        //经理添加小弟 重复小弟、重新雇用某人
//        for (String e:createList(1,3,"小弟")){
//            manager.addParkingBoy(e);
//        }
//        //展示小弟
//        manager.listBoyList();
    }

}
