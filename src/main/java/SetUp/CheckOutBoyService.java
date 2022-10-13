package SetUp;

import ParkingLot.ParkingBuilding;
import ParkingLot.Ticket;
import Users.Client;
import Users.Manager;
import Users.ParkingBoy;
import Vehicle.Vehicle;
import Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

import static SetUp.CreateData.addVehicleList;


public class CheckOutBoyService extends RunExample{
    @Override
    public void run(Manager manager, ParkingBuilding parkingBuilding, Client client) throws InterruptedException {
        // TODO 用户选择小弟停车
        //存放停车小票，用于取车

        List<Ticket> ticketList = new ArrayList<>();
        for (String e : addVehicleList(10)) {
            //小弟停车
            ParkingBoy boy =manager.chooseRandomBoy();
            if(boy!=null) {
                client = new Client("李菁", new Vehicle(e, VehicleType.MEDIUM) {
                }, boy);
                Ticket ticket = boy.parkingCar(client, parkingBuilding);
                ticketList.add(ticket);
                boy.listParkingLotList(parkingBuilding);
            }else {
                break;
            }

        }

//        // TODO 用户选择小弟取车
//        //小弟取车
//        Timer timer = new Timer();
//        //取车定时器
//        for(Ticket e:ticketList){
//            ParkingBoy boy =manager.chooseRandomBoy();
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    //经理取车
//                    boy.pickingUpCar(e,parkingBuilding);
//                    boy.listParkingLotList(parkingBuilding);
//                }
//            };
//            timer.schedule(task,3000L);
//        }
//        Thread.sleep(5000);
//        timer.cancel();
    }

}
