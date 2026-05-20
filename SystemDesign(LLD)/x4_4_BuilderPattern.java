
import java.util.List;

class BurgerMeal {
    // Required : these things are must in a BurgerMeal
    /*
    Note these are "final". Why: 
        These are required fields. By making them final:       
            - They must be initialized exactly once
            - They can’t be accidentally changed later
        
        So a BurgerMeal is always in a valid state after construction.
    */
    private final String bunType;
    private final String patty;

    // Optional : these things are NOT must in a BurgerMeal
    private boolean hasCheese;
    private List<String> toppings;
    private String side;
    private String drink;

    // Private constructor
    // Builder Pattern: insteadof taking params like "bunType", "patty", etc. as parameters in the constructor from the user, we use a builder class called "BurgerBuilder"
    private BurgerMeal(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }

    // This is a static inner class
    // I could have this class outside too (no need of static then) but that is an anti-pattern, if it's the builder for "BurgerMeal", it should be inside it.
    public static class BurgerBuilder {
        // These all parameters are exactly the same as that of outer class

        // Required : these things are must in a BurgerMeal
        private final String bunType;
        private final String patty;

        // Optional : these things are NOT must in a BurgerMeal
        private boolean hasCheese;
        private List<String> toppings;
        private String side;
        private String drink;

        // In the constructor, take the "Required" things only
        public BurgerBuilder(String bunType, String patty){
            this.bunType = bunType;
            this.patty = patty;
        }

        // Have setter methods for "Optional" things
        public BurgerBuilder withCheese (boolean hasCheese){
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerBuilder withToppings (List<String> toppings){
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder withSide (String side){
            this.side = side;
            return this;
        }

        public BurgerBuilder withDrink (String drink){
            this.drink = drink;
            return this;
        }

        public BurgerMeal build(){
            return new BurgerMeal(this);
        }
    }

    @Override
    public String toString() {      
        return "BurgerMeal{" +
            "bunType='" + bunType + '\'' +
            ", patty='" + patty + '\'' +
            ", hasCheese=" + hasCheese +
            ", toppings=" + toppings +
            ", side='" + side + '\'' +
            ", drink='" + drink + '\'' +
            '}';
    }
}

class x4_4_BuilderPattern {
    public static void main(String[] args) {
        BurgerMeal burgerMeal = (new BurgerMeal.BurgerBuilder("wheat_bun", "veg_patty")).build(); 
        //OR you can write as "new BurgerMeal.BurgerBuilder("wheat_bun", "veg_patty").build()"
        System.out.println(burgerMeal.toString());
        
        BurgerMeal burgerMealwithCheeseWithSide = (new BurgerMeal.BurgerBuilder("wheat_bun", "veg_patty").withCheese(true).withSide("fries")).build();
        System.out.println(burgerMealwithCheeseWithSide.toString());
    }
}
