package oop;

public class Student {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name) {
        this(name,20);
    }

    public Student(int age) {
        this("default", age);
    }

    public void sayHello() {
        System.out.println("Hello! I'm " + name + ", " + age + " old");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
