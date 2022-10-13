package ParkingLot;

import java.util.List;

public class ParkingLot {
    //与最小单位spot一一对应上 哪些属性是没了，哪些新加的

    // TODO 属性是 构造器里有的才定义成属性么？ 比如AvailiableCount，不是new一个实例需要的，却是garage的属性

    private final List<ParkingSpot> spotList;//该层有哪些车位
    private boolean available;//该层是否有空车位
    private final String name;
    // TODO string类的属性是final。表示引用地址不可变  原因是：安全性、效率  1.为了实现字符串池 2.为了线程安全 3.为了实现String可以创建HashCode不可变性

    public ParkingLot(List<ParkingSpot> spotList, boolean available, String name) {
        this.spotList = spotList;
        this.available = available;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public int getAviliableCount() {
        int aviliableCount=0;
        for(ParkingSpot e:spotList){
            if(e.isAviliable()){
                aviliableCount++;
            }
        }
        return aviliableCount;
    }

    public  int getLotSize(){
        return spotList.size();
    }

    //isAvailable()是判断是否删除此停车场
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<ParkingSpot> getSpotList() {
        return spotList;
    }
}
