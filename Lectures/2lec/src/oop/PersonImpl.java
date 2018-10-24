package oop;

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
    public boolean marry(Person peson) {
        if (this.sex != peson.getSex() && this.age >= 18 && peson.getAge() >= 18) {
            if (this.spouse != null) {
                this.divorce();
                peson.divorce();
            }

            spouse = peson;
            System.out.println(this.name + " was married on " + peson.getName());
            return true;

        } else {
            System.out.println("You can't marry them");
            return false;
        }
    }

    @Override
    public void divorce() {
        System.out.println(this.name + " was devorced!");

        this.spouse = null;
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
