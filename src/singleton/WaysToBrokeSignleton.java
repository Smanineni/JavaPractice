package singleton;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WaysToBrokeSignleton {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, CloneNotSupportedException {
        Singleton originalSingletonInstance = Singleton.getInstance();
        Singleton duplicateSingletonInstance = Singleton.getInstance();
        System.out.println("Hash code for originalSingletonInstance is: " + originalSingletonInstance.hashCode());
        System.out.println("Hash code for duplicateSingletonInstance is: " + duplicateSingletonInstance.hashCode());

        //Refelection
        Class<?> singletonClass = Class.forName("singleton.Singleton");
        Constructor<Singleton> constructor = (Constructor<Singleton>) singletonClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton brokenSingletonInstanceUsingReflection = constructor.newInstance();
        System.out.println("Hash code for originalSingletonInstance is: " + originalSingletonInstance.hashCode());
        System.out.println("Hash code for brokenSingletonInstanceUsingReflection is: " + brokenSingletonInstanceUsingReflection.hashCode());

        // Serialization
        System.out.println("----Beaking using Serialization-----");

        // serialization process
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\srinivasulu.manineni\\Documents\\Srini\\PersonalWorkspace\\serializationFile"));
        objectOutputStream.writeObject(originalSingletonInstance);
        objectOutputStream.close();

        //Deserilation process
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\srinivasulu.manineni\\Documents\\Srini\\PersonalWorkspace\\serializationFile"));
        Singleton brokenSingletonUsingSerialization = (Singleton) objectInputStream.readObject();
        System.out.println("Hash code for originalSingletonInstance is: " + originalSingletonInstance.hashCode());
        System.out.println("Hash code for brokenSingletonUsingSerialization is: " + brokenSingletonUsingSerialization.hashCode());

        //Cloneable

        System.out.println("----Beaking using cloning-----");

        Singleton brokenSingletonUsingCLoning = (Singleton)originalSingletonInstance.clone();

        System.out.println("Hash code for originalSingletonInstance is: " + originalSingletonInstance.hashCode());
        System.out.println("Hash code for brokenSingletonUsingCLoning is: " + brokenSingletonUsingCLoning.hashCode());
    }
}
