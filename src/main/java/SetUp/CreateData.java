package SetUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateData {
    public static List<String> createList(int number1, int number2,String listName){
        List<String> parkingBoyNameList =new ArrayList<>();
        for (int i = number1; i <= number2; i++) {
            parkingBoyNameList.add(listName+i+"号");
        }
        return parkingBoyNameList;
    }
    public static StringBuffer getRandom(int number){
        //创建随机数
        String str="1234567890";
        Random random =new Random();
        StringBuffer randomString =new StringBuffer();
        for (int j = 0; j < number; j++) {
            int num=random.nextInt(10);
            randomString.append(str.charAt(num));
        }
        return randomString;
    }

    //传入number 添加车辆
    public static List<String> addVehicleList(int number){
        List<String> vehicleList =new ArrayList<>();
        for (int i = 0; i < number; i++) {
            vehicleList.add("黑A"+getRandom(5));
        }
        return vehicleList;
    }
}
