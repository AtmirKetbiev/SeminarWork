package task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SerializableManager {

    public SerializableManager() {
    }

    static public void serializable(List<Animal> animals, String file) throws IOException, ClassNotFoundException {
        Path path = Paths.get(file);
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(animals);
        }
    }

    public static List<Animal> deserializer(String nameFile) throws IOException, ClassNotFoundException {
        Path path = Paths.get(nameFile);
        List<Animal> animalList;
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(path))) {
            animalList = (List<Animal>) inputStream.readObject();
        }

        return animalList;
    }

    static public void myOutput(List<Animal> animals, String file) throws IOException, ClassNotFoundException {
        Path path = Paths.get(file);
        try (DataOutputStream dataOutputStream = new DataOutputStream(Files.newOutputStream(path))) {
            for (Animal animal : animals) {
                dataOutputStream.writeChars(animal.getName());
                dataOutputStream.writeInt(animal.getAge());
                dataOutputStream.writeChars(animal.getType().name());
                dataOutputStream.writeInt(animal.getFood().size());
                for (Food food : animal.getFood()) {
                    dataOutputStream.writeChars(food.getName());
                    dataOutputStream.writeInt(food.getCount());
                }
            }
        }
    }

    static public List<Animal> myInput(String file) throws IOException, ClassNotFoundException {
        Path path = Paths.get(file);
        List<Animal> animal = new ArrayList<>();
        String name;
        Type type;
        int age;
        int foodCount;
        String nameFood;
        int coutFood;

        try (DataInputStream dataInputStream = new DataInputStream(Files.newInputStream(path))) {

            for (int i = 0; i <= dataInputStream.readInt(); i++) {
                name = dataInputStream.readUTF();
                age = dataInputStream.readInt();
                type = Type.valueOf(dataInputStream.readUTF());
                foodCount = dataInputStream.readInt();
                List<Food> food = new ArrayList<>();
                for (int j = 0; j <= foodCount; j++) {
                    nameFood = dataInputStream.readUTF();
                    coutFood = dataInputStream.readInt();
                    food.add(new Food(nameFood, coutFood));
                }

                animal.add(new Animal(name, type, age, food));
            }
            return animal;
        }

    }

}
