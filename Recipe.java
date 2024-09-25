import java.util.ArrayList;
public class Recipe {
    private final String name;
    private final ArrayList<Ingredient> ingredients;
    private final String instructions;

    public Recipe(String name, ArrayList<Ingredient> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    
    public void displayRecipe() {
        System.out.println("Recipe: " + name);
        System.out.println("Ingredients:");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getQuantity() + " " + ingredient.getUnit() + " " + ingredient.getName());
        }
        System.out.println("Instructions: " + instructions);
    }
}
