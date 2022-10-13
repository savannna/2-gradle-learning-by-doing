package Users;

import ParkingLot.ParkingBuilding;
import ParkingLot.ParkingLot;
import ParkingLot.ParkingSpot;

import java.util.*;

public class Manager extends Staff {
    //TODO  为什么是final
    private final List<ParkingBoy> parkingBoyList;
    private final String name;
    private final ParkingBuilding parkingBuilding;

    public Manager(List<ParkingBoy> parkingBoyList, String name, ParkingBuilding parkingBuilding) {
        this.parkingBoyList = parkingBoyList;
        this.name = name;
        this.parkingBuilding = parkingBuilding;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<ParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }


    //增小弟
    //初始化Manager 就要生成boyList 以及每添加一个小弟 就new一个小弟对象
    public void addParkingBoy(String ParkingBoyName){

        // TODO 此方案加break循环只执行一次，不加break 就会重复添加循环次

        // TODO parkingBoyList.add(boy)不应该写在for 里，在for里不应该进行增删
        // 使用clone list 就可以在for里增删了
        //List<ParkingBoy> parkingBoyListClone = new ArrayList<>(parkingBoyList);
        // TODO 如果初始为空，并不执行遍历
//        if(!parkingBoyList.isEmpty()) {
//            for (ParkingBoy e : parkingBoyListClone) {
//                System.out.println("!!!!!!!");
//                System.out.println(e.getName());
//                System.out.println(ParkingBoyName);
//                if (Objects.equals(e.getName(), ParkingBoyName)) {//如果重名了
//                    if (e.isAvailable()) {//如果已经被雇佣
//                        System.out.println("请勿添加已雇佣的同名员工" + "'" + ParkingBoyName + "'");
//                    } else {//已被开除
//                        for(ParkingBoy a:parkingBoyList){
//                            if(Objects.equals(a.getName(), ParkingBoyName)){
//                                a.setAvailable(true);
//                            }
//                        }
//                        System.out.println("重新雇用员工" + "'" + ParkingBoyName + "'");
//                        //不跳出循环他还会做判断，就重新添加新员工
//                    }
//                }
//            }
//            for (ParkingBoy e : parkingBoyListClone) {
//                if (!Objects.equals(e.getName(), ParkingBoyName)) {//如果不重名
//                    ParkingBoy boy = new ParkingBoy(ParkingBoyName, true);
//                    parkingBoyList.add(boy);
//                    System.out.println("员工'" + ParkingBoyName + "'添加成功！");
//                }
//            }
//
//        }else {
//            ParkingBoy boy = new ParkingBoy(ParkingBoyName, true);
//            parkingBoyList.add(boy);
//            System.out.println("员工'" + ParkingBoyName + "'添加成功！");
//        }

        // TODO map 可以直接contain 不用for遍历+equal
        Map<String,Boolean> parkingBoyMap = new HashMap<>();
        for(ParkingBoy e:parkingBoyList){
            parkingBoyMap.put(e.getName(),e.isAvailable());
        }

        if(parkingBoyMap.containsKey(ParkingBoyName)){//如果重名
            for(Map.Entry<String,Boolean> e:parkingBoyMap.entrySet()){
                if(Objects.equals(e.getKey(), ParkingBoyName)&& e.getValue()){
                    System.out.println("请勿添加已雇佣的同名员工" + "'" + ParkingBoyName + "'");
                }
                if(Objects.equals(e.getKey(), ParkingBoyName)&& !e.getValue()){//已开除的
                    for(ParkingBoy a:parkingBoyList){
                            if(Objects.equals(a.getName(), ParkingBoyName)){
                                a.setAvailable(true);
                            }
                        }
                        System.out.println("重新雇用员工" + "'" + ParkingBoyName + "'");
                }
            }

        }else {
            ParkingBoy boy = new ParkingBoy(ParkingBoyName, true);
            parkingBoyList.add(boy);
            System.out.println("员工'" + ParkingBoyName + "'添加成功！");
        }

    }
    //删小弟
    public void removeParkingBoy(String ParkingBoyName){//并不是真正删除，而是把avaiable改成false
        Map<String,Boolean> parkingBoyMap = new HashMap<>();
        for(ParkingBoy e:parkingBoyList){
            parkingBoyMap.put(e.getName(),e.isAvailable());
        }
        if(!parkingBoyMap.containsKey(ParkingBoyName)){
            System.out.println("不存在员工"+"'"+ParkingBoyName+"'");
        }else {
            for (ParkingBoy e : parkingBoyList) {
                if (Objects.equals(e.getName(), ParkingBoyName)) {
                    e.setAvailable(false);
                    System.out.println("已开除小弟" + "'" + ParkingBoyName + "'");
                }
            }
        }





    }
    //查小弟
    public void listBoyList(){

        System.out.println("=====================");
        System.out.println("《员 工 名 单》");
        System.out.println("---------------------");
        System.out.println("[姓 名]:[状 态]");
        System.out.println("---------------------");
        if(!parkingBoyList.isEmpty()) {
            for (ParkingBoy e : parkingBoyList) {
                if (e.isAvailable()) {
                    System.out.println(e.getName() + ":雇佣中");
                } else {
                    System.out.println(e.getName() + ":已开除");
                }
            }

        }else{
            System.out.println("员工名单为空");
        }
        System.out.println("=====================");

    }

    //选择随机小弟 进行服务
    public ParkingBoy chooseRandomBoy() {
        //随机下标
        //产生0-(arr.length-1)的整数值,也是数组的索引
        ParkingBoy parkingBoy = null;
        try {
            int index = 0;
            index = (int) (Math.random() * parkingBoyList.size());
            parkingBoy = parkingBoyList.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("此停车场未雇佣小弟");
        }


        return parkingBoy;
    }

    //增停车场
    //初始化ParkingBuilding 就要生成parkingLotList 以及每添加一个停车场 就new一个parkingLot对象
    public void addParkingLot(String ParkingLotName,int lotSize){
        Map<String,Boolean> parkingLotMap = new HashMap<>();
        for(ParkingLot e:parkingBuilding.getParkingLotList()){
            parkingLotMap.put(e.getName(),e.isAvailable());
        }

        if(parkingLotMap.containsKey(ParkingLotName)){//如果重名
            for(Map.Entry<String,Boolean> e:parkingLotMap.entrySet()){
                if(Objects.equals(e.getKey(), ParkingLotName)&& e.getValue()){
                    System.out.println("请勿添加已拥有的同名停车场" + "'" + ParkingLotName + "'");
                }
                if(Objects.equals(e.getKey(), ParkingLotName)&& !e.getValue()){//已删除的
                    for(ParkingLot a:parkingBuilding.getParkingLotList()){
                        if(Objects.equals(a.getName(), ParkingLotName)){
                            if(a.getLotSize() == lotSize) {
                                a.setAvailable(true);
                                System.out.println("重新使用停车库" + "'" + ParkingLotName + "'"+"，容量为" + a.getLotSize());
                                break;
                            } else {
                                System.out.println("重新使用不可改变容量，请输入旧容量"+a.getLotSize());
                                break;
                            }
                        }
                    }

                }
            }

        }else {
            ParkingLot lot = new ParkingLot(new ArrayList<>(lotSize),true,ParkingLotName);

            //把ParkingLot填满spot对象
            for (int i = 1; i <= lotSize; i++) {
                ParkingSpot spot =new ParkingSpot(i,true);
                spot.setParkingLot(lot);
                spot.setParkingBuilding(parkingBuilding);
                spot.setSpotNo(i);
               lot.getSpotList().add(spot);
            }
            parkingBuilding.getParkingLotList().add(lot);
            System.out.println("停车库'" + ParkingLotName + "'添加成功！"+"容量为"+lot.getLotSize());
        }

    }


    //改变停车库容量
    public void changeCapacityOfParkingLot(String ParkingLotName,int newLotSize) {
        ParkingLot parkingGarage = null;
        try {
            int index = 0;
            for (ParkingLot e : parkingBuilding.getParkingLotList()) {
                if (Objects.equals(e.getName(), ParkingLotName)) {
                    break;
                } else {

                    index++;
                }
            }
            parkingGarage = parkingBuilding.getParkingLotList().get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("不存在该名字车库");
        }
        int oldSize = parkingGarage.getLotSize();
        if (oldSize < newLotSize) {
            if (parkingGarage.isAvailable()) {//对于未删除的停车场

                //把ParkingLot新增空位填满spot对象
                for (int i = oldSize + 1; i <= newLotSize; i++) {
                    ParkingSpot spot = new ParkingSpot(i, true);
                    spot.setParkingLot(parkingGarage);
                    spot.setParkingBuilding(parkingBuilding);
                    spot.setSpotNo(i);
                    parkingGarage.getSpotList().add(spot);
                }
                System.out.println("扩容成功！"+"旧容量为" + oldSize + ",现容量为" + parkingGarage.getLotSize());

            } else {
                System.out.println("该停车场已删除，不可修改容量");
            }
        } else {
            System.out.println("请输入大于旧容量" + oldSize + "的数字");
        }

    }

    //删停车场
    public void removeParkingLot(String ParkingLotName){
        Map<String,Boolean> parkingLotMap = new HashMap<>();
        for(ParkingLot e:parkingBuilding.getParkingLotList()){
            parkingLotMap.put(e.getName(),e.isAvailable());
        }
        if(!parkingLotMap.containsKey(ParkingLotName)){
            System.out.println("不存在停车场"+"'"+ParkingLotName+"'");
        }else {
            for (ParkingLot e : parkingBuilding.getParkingLotList()) {
                if (Objects.equals(e.getName(), ParkingLotName)) {
                    e.setAvailable(false);
                    System.out.println("已删除停车场" + "'" + ParkingLotName + "'");
                }
            }
        }

    }

}
