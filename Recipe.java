import java.util.ArrayList;
public class Recipe {
    private final String name;
    private final ArrayList<Ingredient> ingredients;
    private String instructions;

    public Recipe(String name, ArrayList<Ingredient> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public String getInstructions() {
        return instructions;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
