package SetUp;

import ParkingLot.ParkingBuilding;
import ParkingLot.Ticket;
import Users.Client;
import Users.Manager;
import Vehicle.Vehicle;
import Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static SetUp.CreateData.addVehicleList;

public class CheckOutManagerService extends RunExample{
    @Override
    public void run(Manager manager, ParkingBuilding parkingBuilding, Client client) throws InterruptedException {
        // TODO 此情况展示了 停满的话 listLot会显示"已停满"
        // TODO 用户选择经理停车
        //存放停车小票，用于取车
        List<Ticket> ticketList=new ArrayList<>();
        for (String e : addVehicleList(7)) {
            //经理停车
            client = new Client("李菁", new Vehicle(e, VehicleType.MEDIUM) {},manager);
            Ticket ticket=manager.parkingCar(client, parkingBuilding);
            ticketList.add(ticket);
            manager.listParkingLotList(parkingBuilding);
        }


        // TODO 用户选择经理取车
        //经理取车
        Timer timer = new Timer();
        //取车定时器
        for(Ticket a:ticketList){
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    //经理取车
                    manager.pickingUpCar(a,parkingBuilding);
                    manager.listParkingLotList(parkingBuilding);
                }
            };
            timer.schedule(task,3000L);
        }
        Thread.sleep(5000);
        timer.cancel();
    }

}
