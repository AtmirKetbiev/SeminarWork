package task1;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Animal implements Serializable {

    private String name;
    private Type type;
    private int age;
    private List<Food> food;

    public Animal(String name, Type type, int age, List<Food> food) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public List<Food> getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", age='" + age + '\'' +
                ", food=" + food +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                Objects.equals(name, animal.name) &&
                type == animal.type &&
                Objects.equals(food, animal.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, age, food);
    }
}
