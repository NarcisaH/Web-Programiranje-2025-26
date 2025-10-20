// Class representing a generic Animal
class Animal {
    String name;

    // Constructor for Animal class
    public Animal(String name) {
        this.name = name;
    }

    // Method that can be overridden by subclasses
    public void sound() {
        System.out.println(name + " makes a sound.");
    }
}

// Dog class extends Animal and overrides sound() method
class Dog extends Animal {
    public Dog(String name) {
        super(name);  // Call the superclass constructor
    }

    // Override the sound() method
    @Override
    public void sound() {
        System.out.println(name + " barks.");
    }
}

// Main class
public class OOPDemo {
    public static void main(String[] args) {
        // Create Animal and Dog objects
        Animal genericAnimal = new Animal("Generic Animal");
        Dog dog = new Dog("Rex");

        // Call the sound() method on both objects
        genericAnimal.sound();  // Output: Generic Animal makes a sound.
        dog.sound();            // Output: Rex barks.
    }
}
