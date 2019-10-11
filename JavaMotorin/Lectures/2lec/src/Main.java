import oop.Person;
import oop.PersonImpl;
import oop.
import oop.Sex;
import oop.Student;

public class Main {
    public static void main(String[] args) {
//        int i = 1;
//        Integer i1 = 1;
//
//        plus(i);
//        plus1(i1);
//
//        System.out.println(i);
//        System.out.println(i1);
//////////////////////////////////////////////////////////////////////////////////////////////

//        Student alex = new Student("Alex", 24);
//        Student alex1 = new Student ("Julia");
//        Student alex2 = new Student(30);
//        alex.sayHello();

        Person dima = new PersonImpl("Dima", 27, Sex.MAN);
        Person sveta = new PersonImpl("Sveta", 25, Sex.WOMAN);

        dima.marry(sveta);

        Person spouse = dima.getSpouse();
        System.out.println(spouse.getName());

        spouse.divorce();

        System.out.println(dima.getSpouse().getName());
        System.out.println(sveta.getSpouse());

        Person nikita = new PersonImpl("Nikita", 22, Sex.MAN);
        Person boris = new PersonImpl("Boris", 16, Sex.MAN);

        nikita.marry(boris);
        nikita.marry(nikita);

        boris.marry(sveta);


    }

//    private static void plus1(Integer i1) {
//        ++i1;
//        System.out.println(i1);
//    }
//
//    private static void plus(int i) {
//        ++i;
//        System.out.println(i);
//    }
}
