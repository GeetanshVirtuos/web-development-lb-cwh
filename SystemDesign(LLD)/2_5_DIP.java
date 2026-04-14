// From ChatGPT. NOT MEANT TO BE EXECUTED, JUST READ IT

// Without DIP (Bad Example)
// This is a low-level module
class Keyboard {
    void type() {
        System.out.println("Typing with keyboard...");
    }
}

// This is a high-level module
class Computer {
    private Keyboard keyboard = new Keyboard(); // tightly coupled

    void start() {
        keyboard.type();
    }
}

// 🚨 Problem:
// Computer is tightly coupled to Keyboard
// If you want to use a BluetoothKeyboard, you must modify Computer

// ✅ With DIP (Good Example)
// Step 1: Create an abstraction (interface)
interface InputDevice {
    void type();
}

// Step 2: Low-level modules implement the interface
class Keyboard implements InputDevice {
    public void type() {
        System.out.println("Typing with keyboard...");
    }
}

class BluetoothKeyboard implements InputDevice {
    public void type() {
        System.out.println("Typing with Bluetooth keyboard...");
    }
}

// Step 3: High-level module depends on abstraction
class Computer {
    private InputDevice inputDevice;

    // Dependency injected via constructor
    public Computer(InputDevice inputDevice) {
        this.inputDevice = inputDevice;
    }

    void start() {
        inputDevice.type();
    }
}

// Step 4: Use it
public class Main {
    public static void main(String[] args) {
        InputDevice device = new Keyboard(); // or new BluetoothKeyboard()
        Computer computer = new Computer(device);
        computer.start();
    }
}

// 🎯 Key Benefits
// 🔄 Easy to switch implementations (Keyboard → BluetoothKeyboard) (See Main class)
// 🧪 Better testing (you can pass mock objects)
// 🧩 Loose coupling
// 📈 Scalable and maintainable code