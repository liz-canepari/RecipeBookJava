import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Ingredient milk = new Ingredient("Milk", 1.5, "cup");
        Ingredient strawberrySyrup = new Ingredient("Strawberry Syrup", 2, "Tbsp");

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(milk);
        ingredients.add(strawberrySyrup);

        Recipe strawberryMilk = new Recipe("Strawberry Milk", ingredients, "Combine milk and syrup");

        strawberryMilk.displayRecipe();
    }
}