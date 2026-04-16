package lab3;

import java.util.ArrayList;
import java.util.List;

abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    @Override void makeSound() { System.out.println("Woof!"); }
}

class Labrador extends Dog {
    @Override void makeSound() { System.out.println("Labrador Woof!"); }
}

class Cat extends Animal {
    @Override void makeSound() { System.out.println("Meow!"); }
}

class AnimalShelter {
    List<Dog> dogs = new ArrayList<>();
    List<Animal> otherAnimals = new ArrayList<>();

    public void addAnimals(Dog dog) {
        dogs.add(dog);
    }

    public void addOtherAnimal(Animal animal) {
        otherAnimals.add(animal);
    }

    public void printAnimalSounds() {
        System.out.println("Звуки собак:");
        for (Dog dog : dogs) dog.makeSound();

        System.out.println("Звуки інших тварин:");
        for (Animal animal : otherAnimals) animal.makeSound();
    }
}

public class task8 {
    public static void main(String[] args) {
        new task8().run();
    }

    public void run() {
        System.out.println("\nЗавдання 8: Animal Shelter");

        AnimalShelter shelter = new AnimalShelter();

        shelter.addAnimals(new Dog());
        shelter.addAnimals(new Labrador());
        shelter.addOtherAnimal(new Cat());

        shelter.printAnimalSounds();
    }
}