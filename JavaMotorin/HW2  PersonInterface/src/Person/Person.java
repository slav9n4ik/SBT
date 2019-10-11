package Person;

public interface Person {
    boolean marry(Person person);
    void divorce();
    Person getSpouse();
    int getAge();
    Sex getSex();
    String getName();
}
