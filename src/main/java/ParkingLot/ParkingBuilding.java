package ParkingLot;

//yyx: 可以删掉


import java.text.SimpleDateFormat;
import java.util.*;

import static Vehicle.VehicleType.*;

//yyx: 这个类名是不是不太make sense, 不过可以先不改， 看你的理解
public class ParkingBuilding {
    private final String name;
    private List<ParkingLot> parkingLotList;
    //private int aviliableTotalCount;//总空车位数量
    private boolean available;//该公司是否有空车位

    public ParkingBuilding(String name, List<ParkingLot> parkingLotList) {
        this.name = name;
        this.parkingLotList = parkingLotList;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<ParkingLot> getParkingLotList() {

        return parkingLotList;
    }

    public String getName() {
        return name;
    }

    public int getAviliableTotalCount() {
        int aviliableTotalCount=0;
        for (ParkingLot e : parkingLotList) {
            if(e.isAvailable()){
                aviliableTotalCount=aviliableTotalCount+e.getAviliableCount();
            }
        }
        return aviliableTotalCount;
    }

    //空位最多
    public ParkingLot getMostVacancies() {
        Map<String, Integer> parkingLotMapWithVacancies = new HashMap<>();
        int index = 0;
        ParkingLot parkingGarageWithMostVacanies = null;
        try {
            for (ParkingLot e : parkingLotList) {
                if (e.isAvailable()) {
                    parkingLotMapWithVacancies.put(e.getName(), e.getAviliableCount());
                }
            }
            // 升序比较器
            //comparingInt提取一个 int 排序键，并返回一个Comparator
            // Map.Entry -> Map.Entry.getValue()
            Comparator<Map.Entry<String, Integer>> valueComparator = Comparator.comparingInt(Map.Entry::getValue);
            // map转换成list进行排序
            List<Map.Entry<String, Integer>> list = new ArrayList<>(parkingLotMapWithVacancies.entrySet());
            // 排序  倒序
            list.sort(valueComparator.reversed());

            for (ParkingLot e : parkingLotList) {
                if (!Objects.equals(e.getName(), list.get(0).getKey())) {
                    index++;
                } else {
                    break;
                }
            }
            parkingGarageWithMostVacanies = parkingLotList.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("注意：该停车场未添加停车库，无法获取最佳停车位");
        }

        return parkingGarageWithMostVacanies;
    }

    public void printTicket(Ticket ticket){
        System.out.println("============================");
        if(ticket.getExitTime()==null){
            System.out.println("《停 车 小 票》");
        }else {
            System.out.println("《取 车 小 票》");
        }
        System.out.println("----------------------------");
        // TODO a.b.c（）调用时可能出现空指针异常，也就是空引用  可以逐一打印a.  a.b.  a.b.c 看哪个是空
        System.out.println("停车场集团："+ticket.getParkingSpot().getParkingBuilding().getName());
        System.out.println("用户姓名："+ticket.getClient().getName());
        System.out.println("车牌号："+ticket.getClient().getVehicle().getNumberPlate());
        if(ticket.getClient().getVehicle().getVehicleType().equals(SMALL)){
            System.out.println("车辆类型："+"摩托车");}
        if(ticket.getClient().getVehicle().getVehicleType().equals(MEDIUM)){
            System.out.println("车辆类型："+"小轿车");}
        if(ticket.getClient().getVehicle().getVehicleType().equals(BIG)){
            System.out.println("车辆类型："+"大卡车");}

        System.out.println("存入停车场："+ticket.getParkingSpot().getParkingLot().getName());
        System.out.println("所在停车位："+ticket.getParkingSpot().getSpotNo()+"号");
        System.out.println("办理人："+ticket.getClient().getStaff().getName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("驶入时间："+formatter.format(ticket.getEnterTime()));
        if(ticket.getExitTime()==null){
            System.out.println("驶出时间：未驶出");
        }else {
            System.out.println("驶出时间："+formatter.format(ticket.getExitTime()));
        }
        System.out.println("============================");
    }

}

