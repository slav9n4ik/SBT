package Person;

public class PersonImpl implements Person {

    private final String name;
    private final int age;
    private final Sex sex;
    private Person spouse;

    public PersonImpl(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public boolean marry(Person person) {

        if (this.sex != person.getSex() && this.age >= 18 && person.getAge() >= 18) {
            if (this.spouse != null) {
                this.divorce();
            }

            this.spouse = person;

            if (person.getSpouse() == null) {
                person.marry(this);
            }

            System.out.println("*** " + this.name + " was married on " + person.getName() + " ***");
            return true;

        } else {
            System.out.println("*** You can't marry them ***");
            return false;
        }

    }

    @Override
    public void divorce() {
        Person swap = this.spouse;
        this.spouse = null;

        if (swap != null) {
            System.out.println("*** " + this.name + " was devorced! ***");
            swap.divorce();
        }
    }

    @Override
    public Person getSpouse() {
        return spouse;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public Sex getSex() {
        return sex;
    }


    @Override
    public String getName() {
        return this.name;
    }
}
