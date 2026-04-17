
import java.util.*;

class BurgerMeal {
    // Required

    private final String bunType;
    private final String patty;

    // Optional
    private final boolean hasCheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;
    // Private constructor

    private BurgerMeal(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }

}

class x4_BuilderPattern {

    public static void main(String[] args) {
        System.err.println("Hello");
    }
}
