interface Logistics {
    void send();
}

class Road implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by road logic");
    }
}

class Air implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by air logic");
    }
}

// This is the Factory
class LogisticsFactory {
    // NOTE: it returns an interface not object of a class
    public static Logistics getLogistics(String mode) {
        if (mode == "road") {
            return new Road();
        }
        return new Air();
    }
}

// This is Client code
class LogisticsService{
    public void send(String mode){
        // Client code does not know object of which class is being returned (Air or Road), that is taken care of by the Factory "LogisticsFactory"
        // This also means this code is following SRP as LogisticsService does not have the responsibility of "which type of object to create", only that it has to send/ship the product.
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.send();
    }
}

class x3_FactoryPattern{
    public static void main(String[] args) {
        System.err.println("Hello");
    }
}

