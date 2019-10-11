import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        CountMap<Integer> map = new CountMapImp<>();
        CountMap<Integer> map2 = new CountMapImp<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        map2.add(10);
        map2.add(10);
        map2.add(5);
        map2.add(6);
        map2.add(5);
        map2.add(10);

        //map.addAll(map2);
        //map.toMap(map2.toMap());

        System.out.println(map2.getCount(5)); // 2
        System.out.println(map2.getCount(6)); // 1
        System.out.println(map2.getCount(10)); // 3

//        System.out.println(map.size()); //3
//        System.out.println(map.remove(10)); //3
//        System.out.println(map.size()); //2

    }
}
