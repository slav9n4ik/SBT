import Person.PersonImpl;
import Person.Sex;
import Person.Person;

public class Main {
    public static void main(String[] args) {
        Person dima = new PersonImpl("Dima", 27, Sex.MAN);
        Person sveta = new PersonImpl("Sveta", 25, Sex.WOMAN);
        Person katya = new PersonImpl("Katya", 22, Sex.WOMAN);

        System.out.println("------------------- Проверка на присваивание поля spouse -------------------");
        System.out.println(dima.marry(sveta));
        System.out.println(dima.getSpouse().getName());
        System.out.println(sveta.getSpouse().getName());
        System.out.println("----------------------------------------------------------------------------");

        System.out.println("------------------- Проверка на развод при повторной женитьбе -------------------");
        System.out.println(dima.marry(katya));
        System.out.println(dima.getSpouse().getName());
        System.out.println(sveta.getSpouse());
        System.out.println(katya.getSpouse().getName());
        System.out.println("---------------------------------------------------------------------------------");

        System.out.println("------------------- Проверка на развод  -------------------");
        Person spouse = dima.getSpouse();
        spouse.divorce();

        System.out.println(dima.getSpouse());
        System.out.println(katya.getSpouse());
        System.out.println("-----------------------------------------------------------");

        System.out.println("------------------- Проверка на однополый брак и на брак с самим собой-------------------");

        Person nikita = new PersonImpl("Nikita", 22, Sex.MAN);
        Person boris = new PersonImpl("Boris", 16, Sex.MAN);

        nikita.marry(boris);
        nikita.marry(nikita);
        System.out.println("-------------------------------------------------------------------");

        System.out.println("------------------- Проверка на несовершеннолетний брак -------------------");
        boris.marry(sveta);
        System.out.println("-------------------------------------------------------------------");
    }
}
