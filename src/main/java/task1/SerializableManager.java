package task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SerializableManager {
    private List<Animal> animals;
    private String file;

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

    static public void mySerializable(List<Animal> animals, String file) throws IOException, ClassNotFoundException {
        Path path = Paths.get(file);

    }

}
