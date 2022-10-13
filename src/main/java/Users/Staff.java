package Users;

import ParkingLot.ParkingBuilding;
import ParkingLot.ParkingLot;
import ParkingLot.ParkingSpot;
import ParkingLot.Ticket;
import Vehicle.Vehicle;

import java.util.Calendar;
import java.util.Objects;

import static Vehicle.VehicleType.*;

public class Staff {
    private String name;

    public String getName() {
        return name;
    }

    //存车
    public Ticket parkingCar(Client client, ParkingBuilding parkingBuilding) {
        int index = 0;
        //先找到空位最多的Lot对象，再停车
        //寻找parkingBuilding.getMostVacancies()，要写在外面，只执行一次，否则在存车后，再次执行结果不一致
        ParkingLot parkingLotWithMostVacanices = parkingBuilding.getMostVacancies();
        ParkingSpot spotInMostVacanies = null;

        Ticket ticket = new Ticket();
        if (parkingBuilding.getAviliableTotalCount() >= 1) {//判断还有没有空停车位
            for (ParkingSpot e : parkingLotWithMostVacanices.getSpotList()) {
                if (e.isAviliable()) {
                    e.setParkingLot(parkingLotWithMostVacanices);
                    e.setParkingBuilding(parkingBuilding);
                    e.setAviliable(false);
                    e.setVehicle(new Vehicle(client.getVehicle().getNumberPlate(), client.getVehicle().getVehicleType()) {
                    });

                    break;
                } else {
                    index++;
                }
            }
            spotInMostVacanies = parkingLotWithMostVacanices.getSpotList().get(index);
            ticket = new Ticket();
            ticket.setClient(client);
            if (ticket.getClient().getVehicle().getVehicleType().equals(SMALL)) {
                System.out.println("型号为'" + "摩托车" + "'" + "车牌号为'" + client.getVehicle().getNumberPlate() + "'的车辆停车成功");
            }
            if (ticket.getClient().getVehicle().getVehicleType().equals(MEDIUM)) {
                System.out.println("型号为'" + "小轿车" + "'" + "车牌号为'" + client.getVehicle().getNumberPlate() + "'的车辆停车成功");
            }
            if (ticket.getClient().getVehicle().getVehicleType().equals(BIG)) {
                System.out.println("型号为'" + "大卡车" + "'" + "车牌号为'" + client.getVehicle().getNumberPlate() + "'的车辆停车成功");
            }

            //set小票
            ticket.setParkingSpot(spotInMostVacanies);
            Calendar calendar = Calendar.getInstance();
            ticket.setEnterTime(calendar.getTime());
            //打印小票
            parkingBuilding.printTicket(ticket);

        } else {
            System.out.println("停车场空车位不足，"+"车牌号为'" + client.getVehicle().getNumberPlate()+"'的车辆停车失败");
        }
        return ticket;
    }

    //取车
    public void pickingUpCar(Ticket ticket, ParkingBuilding parkingBuilding) {
        try {
            //根据名字找到lot 再找到sopt
            ParkingLot parkingGarage = null;
            try {
                int index1 = 0;
                //找车库对象
                for (ParkingLot e : parkingBuilding.getParkingLotList()) {
                    if (Objects.equals(e.getName(), ticket.getParkingSpot().getParkingLot().getName())) {
                        break;
                    } else {
                        index1++;
                    }
                }
                parkingGarage = parkingBuilding.getParkingLotList().get(index1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("注 意：未找到该停车库");
            }
            ParkingSpot parkingSpot = null;
            try {
                int index2 = 0;
                //找车位对象
                if (parkingGarage != null) {
                    for (ParkingSpot e : parkingGarage.getSpotList()) {
                        if (e.getSpotNo() == ticket.getParkingSpot().getSpotNo()) {
                            break;
                        } else {
                            index2++;
                        }
                    }
                    parkingSpot = parkingGarage.getSpotList().get(index2);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("注 意：未找到该停车位");
            }

            if (parkingSpot != null&&ticket.getParkingSpot()==parkingSpot&& !parkingSpot.isAviliable()) {
                parkingSpot.setAviliable(true);
                Calendar calendar = Calendar.getInstance();
                ticket.setExitTime(calendar.getTime());
                if(ticket.getClient().getVehicle().getVehicleType().equals(SMALL)){
                    System.out.println("型号为'" + "摩托车" + "'车牌号为" + ticket.getClient().getVehicle().getNumberPlate() + "的车辆已取出") ;}
                if(ticket.getClient().getVehicle().getVehicleType().equals(MEDIUM)){
                    System.out.println("型号为'" + "小轿车" + "'车牌号为" + ticket.getClient().getVehicle().getNumberPlate() + "的车辆已取出") ;}
                if(ticket.getClient().getVehicle().getVehicleType().equals(BIG)){
                    System.out.println("型号为'" + "大卡车" + "'车牌号为" + ticket.getClient().getVehicle().getNumberPlate() + "的车辆已取出") ;}
                //打印小票
                parkingBuilding.printTicket(ticket);
            }else{
                System.out.println("小票有误，无法取车");
            }
        }catch (NullPointerException e){
            System.out.println("注 意：未获取小票,无法取车");
        }


    }

    //查停车场
    public void listParkingLotList(ParkingBuilding parkingBuilding){
        System.out.println("=================================");
        System.out.println("《停车场详情》");
        System.out.println("---------------------------------");
        System.out.println("[停车场名]:[状态]|[剩余车位]/[容量]");
        System.out.println("---------------------------------");

        if(!parkingBuilding.getParkingLotList().isEmpty()) {
            for (ParkingLot e : parkingBuilding.getParkingLotList()) {
                if (e.isAvailable()) {
                    if(e.getAviliableCount() == 0){
                        System.out.println(e.getName() + " : 已停满 | "+e.getAviliableCount()+" / "+e.getLotSize());
                    }else{
                        System.out.println(e.getName() + " : 使用中 | "+e.getAviliableCount()+" / "+e.getLotSize());
                    }
                } else {
                    System.out.println(e.getName() + " : 已删除");
                }
            }

        }else{
            System.out.println("停车场名单为空");
        }
        System.out.println("---------------------------------");
        System.out.println("停车场总空位："+parkingBuilding.getAviliableTotalCount());
        System.out.println("=================================");
    }

}
