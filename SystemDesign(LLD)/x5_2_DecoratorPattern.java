interface Pizza {
    String getDescription();   
    double getCost();
}

// Concrete Component, this would be the base and other things can be added on top of it
class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
}

// Decorator Abstract Class
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza; // HAS-A relationship
    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

// Concrete Decorators : Notice this is NOT a "cheesePizza" class, it is a standalone "ExtraCheese", a thing of its own
class ExtraCheese extends PizzaDecorator {
    public ExtraCheese (Pizza pizza) {
        super(pizza); // passes "pizza" to "PizzaDecorator"
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }
    
    @Override
    public double getCost() {
        return pizza.getCost() + 40.0;
    }
}

// Another concrete decorator
class Olives extends PizzaDecorator {
    public Olives (Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30.0;
    }
}

public class x5_2_DecoratorPattern {
    public static void main(String[] args) {
        //Extracheese Pizza
        Pizza pizza = new ExtraCheese(new MargheritaPizza());
        System.out.println(pizza.getCost());
        
        //Olive Pizza
        Pizza pizza2 = new Olives(new MargheritaPizza());
        System.out.println(pizza2.getCost());

        //Extracheese and margherita pizza
        Pizza pizza3 = new Olives(new ExtraCheese(new MargheritaPizza())); // You can have as many decorators as you want but innermost should be a concrete component (here it is "MargheritaPizza()").
        System.out.println(pizza3.getCost());

        return;
    }
}
