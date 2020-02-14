package task1;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;

import static org.junit.Assert.*;

public class SerializableManagerTest {

    List<Animal> animalList = Arrays.asList(new Animal("волк", Type.MAMMAL, 7, Arrays.asList(new Food("rabbit", 2))));
    List<Animal> animalList2 = Arrays.asList(new Animal("лиса", Type.MAMMAL, 5, Arrays.asList(new Food("rabbit", 1))));

    @Test
    public void serializable() throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("волк", Type.MAMMAL, 7, Arrays.asList(new Food("rabbit", 2))));
        SerializableManager.serializable(animals, "animalsList");
        assertEquals(animalList, SerializableManager.deserializer("animalsList"));
    }

    @Test
    public void myTest() throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("волк", Type.MAMMAL, 7, Arrays.asList(new Food("rabbit", 2))));
        SerializableManager.myOutput(animals, "animalsList");
        //List<Animal> animalList11 =SerializableManager.myInput("animalsList");
        assertEquals(animalList, SerializableManager.myInput("animalsList"));
    }

    @Test
    public void TestEmpty() throws IOException, ClassNotFoundException {
        SerializableManager.serializable(Collections.emptyList(), "test");
        assertEquals(Collections.emptyList(), SerializableManager.deserializer("test"));
    }

    @Test
    public void myTestEmpty() throws IOException, ClassNotFoundException {
        SerializableManager.myOutput(Collections.emptyList(), "test");
        assertEquals(Collections.emptyList(), SerializableManager.myInput("test"));
    }

    @Test
    public void TestTwoSerializable() throws IOException, ClassNotFoundException {
        List<Animal> animals1 = new ArrayList<Animal>();
        animals1.add(new Animal("волк", Type.MAMMAL, 7, Arrays.asList(new Food("rabbit", 2))));
        List<Animal> animals2 = new ArrayList<Animal>();
        animals2.add(new Animal("лиса", Type.MAMMAL, 5, Arrays.asList(new Food("rabbit", 1))));
        SerializableManager.serializable(animals1, "animalsList1");
        SerializableManager.serializable(animals2, "animalsList2");

        assertEquals(animalList, SerializableManager.deserializer("animalsList1"));
        assertEquals(animalList2, SerializableManager.deserializer("animalsList2"));
        assertNotEquals(SerializableManager.deserializer("animalsList1"), SerializableManager.deserializer("animalsList2"));
    }

    @Test
    public void serializableError() {
        try{
            SerializableManager.deserializer("Not");
            fail();
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            fail();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}