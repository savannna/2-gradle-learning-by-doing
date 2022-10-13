import ParkingLot.ParkingBuilding;
import ParkingLot.Ticket;
import Users.Client;
import Users.Manager;
import Vehicle.Car;
import Vehicle.Motorcycle;
import Vehicle.Truck;
import Vehicle.VehicleType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProcessTest {
    private final ParkingBuilding parkingBuilding=new ParkingBuilding("BuildingLee", new ArrayList<>());
    private final Manager manager=new Manager(new ArrayList<>(), "停车场经理", parkingBuilding);

//    @Before
//    public void init(){
//        //初始化parkingBuilding
//        parkingBuilding = ;
//        //初始化经理
//        manager = ;
//    }
//    @After
//    public void close(){
//        parkingBuilding=null;
//        manager=null;
//    }


    @Test
    public void testAddParkingBoy(){
        //Given

        //when
       manager.addParkingBoy("小弟1号");
       manager.listBoyList();
        //then
        //期望，实际
        Assertions.assertEquals("小弟1号", manager.getParkingBoyList().get(0).getName());
        Assertions.assertTrue(manager.getParkingBoyList().get(0).isAvailable());

        //when 开除
        manager.removeParkingBoy("小弟1号");
        manager.listBoyList();
        //then
        Assertions.assertFalse(manager.getParkingBoyList().get(0).isAvailable());


    }
    @Test
    public void testRemoveBoy(){
        //Given
        manager.addParkingBoy("小弟1号");
        manager.listBoyList();
        //when 开除不存在的人
        manager.removeParkingBoy("小弟2号");
        //then
        Assertions.assertTrue(manager.getParkingBoyList().get(0).isAvailable());
    }
    @Test
    public void testRehireBoy(){
        //Given
        manager.addParkingBoy("小弟1号");
        manager.removeParkingBoy("小弟1号");

        //when 重新雇用
        manager.addParkingBoy("小弟1号");
        manager.listBoyList();

        //then
        Assertions.assertTrue(manager.getParkingBoyList().get(0).isAvailable());

        //when 添加重复的人
        manager.addParkingBoy("小弟1号");
        //then
        Assertions.assertEquals("小弟1号", manager.getParkingBoyList().get(0).getName());




    }


    @Test
    public void testAddParkingGarage(){
        //Given
        //when
        manager.addParkingLot("A",10);
        //then
        //期望，实际
        Assertions.assertEquals("A", parkingBuilding.getParkingLotList().get(0).getName());
        Assertions.assertTrue(parkingBuilding.getParkingLotList().get(0).isAvailable());

        //when 删除成功
        manager.removeParkingLot("A");
        //then
        Assertions.assertFalse(parkingBuilding.getParkingLotList().get(0).isAvailable());
    }
    @Test
    public void testRemoveGarage(){
        //Given
        manager.addParkingLot("A",10);
        manager.listParkingLotList(parkingBuilding);
        //when 删除失败
        manager.removeParkingLot("B");
        //then
        Assertions.assertTrue(parkingBuilding.getParkingLotList().get(0).isAvailable());
    }
    @Test
    public void testReuseGarage(){
        //Given
        manager.addParkingLot("A",10);
        manager.removeParkingLot("A");

        //when 重新使用该库 输入错误的容量
        manager.addParkingLot("A",20);
        manager.listParkingLotList(parkingBuilding);
        //then
        Assertions.assertEquals("A", parkingBuilding.getParkingLotList().get(0).getName());
        Assertions.assertFalse(parkingBuilding.getParkingLotList().get(0).isAvailable());

        //when 重新使用该库 输入正确的容量
        manager.addParkingLot("A",10);
        manager.listParkingLotList(parkingBuilding);

        //then
        Assertions.assertEquals("A", parkingBuilding.getParkingLotList().get(0).getName());
        Assertions.assertTrue(parkingBuilding.getParkingLotList().get(0).isAvailable());

    }
    @Test
    public void testEnlargeGarage(){
        //Given
        manager.addParkingLot("A",10);
        manager.listParkingLotList(parkingBuilding);

        //when 扩容成功
        manager.changeCapacityOfParkingLot("A",20);
        manager.listParkingLotList(parkingBuilding);

        //then
        Assertions.assertEquals(20, parkingBuilding.getParkingLotList().get(0).getLotSize());

        //when 扩容输入比以前小的数字 扩容失败
        manager.changeCapacityOfParkingLot("A",3);
        manager.listParkingLotList(parkingBuilding);
        //then
        Assertions.assertEquals(20, parkingBuilding.getParkingLotList().get(0).getLotSize());
    }

    @Test
    public void testParkingCar(){
        //Given
        manager.addParkingLot("A",10);
        Client client1 = new Client("李菁", new Car("黑A12345", VehicleType.MEDIUM) {
        }, manager);
        Client client2 = new Client("李菁", new Motorcycle("黑A56789", VehicleType.SMALL) {
        }, manager);
        Client client3 = new Client("李菁", new Truck("黑A02959", VehicleType.BIG) {
        }, manager);


        //when
        Ticket ticket1 = manager.parkingCar(client1, parkingBuilding);
        manager.parkingCar(client2, parkingBuilding);
        manager.parkingCar(client3, parkingBuilding);

        //then
        Assertions.assertEquals("黑A12345", ticket1.getParkingSpot().getVehicle().getNumberPlate());
        Assertions.assertFalse(ticket1.getParkingSpot().isAviliable());
        Assertions.assertEquals("A", ticket1.getParkingSpot().getParkingLot().getName());
    }

    @Test
    public void testPickingUpCar(){
        //Given
        manager.addParkingLot("A",10);
        Client client = new Client("李菁", new Car("黑A12345", VehicleType.MEDIUM) {
        }, manager);
        Ticket ticket = manager.parkingCar(client, parkingBuilding);

        //when
        manager.pickingUpCar(ticket,parkingBuilding);

        //then
        Assertions.assertTrue(ticket.getParkingSpot().isAviliable());
    }

    @Test
    public void testVaildTicket(){
        //Given
        manager.addParkingLot("A",10);
        manager.addParkingLot("B",10);

        Client client = new Client("李菁", new Car("黑A12345", VehicleType.MEDIUM) {
        }, manager);
        Ticket rightTicket = manager.parkingCar(client, parkingBuilding);
        Ticket wrongTicket=new Ticket();
        wrongTicket.setClient(client);
        wrongTicket.setParkingSpot(parkingBuilding.getParkingLotList().get(1).getSpotList().get(3));
        wrongTicket.setEnterTime(rightTicket.getEnterTime());
        wrongTicket.setExitTime(rightTicket.getExitTime());
        parkingBuilding.printTicket(wrongTicket);

        //when
        manager.pickingUpCar(wrongTicket, parkingBuilding);
        manager.listParkingLotList(parkingBuilding);

        //then
        Assertions.assertEquals("黑A12345", rightTicket.getParkingSpot().getVehicle().getNumberPlate());
        Assertions.assertFalse(rightTicket.getParkingSpot().isAviliable());
        Assertions.assertEquals("A", rightTicket.getParkingSpot().getParkingLot().getName());

    }
}
