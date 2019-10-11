import java.util.Objects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private final Person spouse;

    public Person(String name, int age, Person spouse) {
        this.name = name;
        this.age = age;
        this.spouse = spouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(spouse, person.spouse);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, spouse);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", spouse=" + spouse +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        int i = name.compareTo(o.name);
        if (i != 0) return i;

        return Integer.compare(age, o.age);
    }
}
