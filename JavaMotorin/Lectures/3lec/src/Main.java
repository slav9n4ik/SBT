public class Main {
    public static void main(String[] args) {
//        Integer x = 1000;
//        Integer y = 1000;
//
//        System.out.println(x == y);         //сравнивает ссылки
//        System.out.println(x.equals(y));    //сравнивает значения

//        Person a1 = new Person("Alex", 24, null);
//        Person a2 = new Person("Alex", 24, null);
//
////        System.out.println(a1 == a2);           //сравнивает ссылки
////        System.out.println(a1.equals(a2));
//
//        System.out.println(a1.hashCode());
//        System.out.println(a2.hashCode();
        String x = "100";
        String y = "78";

        System.out.println(x.compareTo(x));
        System.out.println(x.compareTo(y));
        System.out.println(y.compareTo(x));

    }
}
