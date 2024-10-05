import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class RecipeManager {
    
    private final ArrayList<Recipe> recipeBook = new ArrayList<>();

    public void addRecipe(Scanner scan) {

        System.out.print("Enter the name of the recipe: ");
        String name = scan.nextLine();


        ArrayList<Ingredient> ingredients = new ArrayList<>();

        boolean addingIngredients = true;

        while (addingIngredients) {

            System.out.println("Enter Ingredient Name (or type 'done' to finish): ");
            String ingredientName = scan.nextLine();

            if (ingredientName.equalsIgnoreCase("done")) {
                break;
            } else {

            }

            System.out.println("Enter Quantity: ");
            double quantity = scan.nextDouble();
            scan.nextLine();

            System.out.println("Enter Unit: ");
            String unit = scan.nextLine();

            ingredients.add(new Ingredient(ingredientName, quantity, unit));
        }

        System.out.println("Enter Recipe Instructions: ");
        String instructions = scan.nextLine();

        Recipe newRecipe = new Recipe(name, ingredients, instructions);
        recipeBook.add(newRecipe);

        System.out.println("Recipe Added: " + name);
    }

    public Recipe selectRecipe(Scanner scan){

        if (recipeBook.isEmpty()){

            System.out.println("No recipes available");
            return null;
        }

        System.out.println("\nRecipes: ");

        for (int i = 0; i < recipeBook.size(); i++){
            System.out.println((i + 1) + ". " + recipeBook.get(i).getName());
        }

        System.out.println("Select Recipe: ");
        int recipeIndex = scan.nextInt() - 1;
        scan.nextLine();

        if (recipeIndex >= 0 && recipeIndex < recipeBook.size()){
            return recipeBook.get(recipeIndex);
        } else {
            System.out.println("Invalid choice");
            return null;
        }

    }

    public void displayRecipe(Recipe recipe) {
        System.out.println("\nRecipe: " + recipe.getName());
        System.out.println("Ingredients: ");
        for (Ingredient ingredient : recipe.getIngredients()) {
            System.out.println("- " + ingredient.getQuantity() + " " + ingredient.getUnit() + " " + ingredient.getName());
        }

        System.out.println("Instructions: ");
        System.out.println(recipe.getInstructions());
        System.out.println("\n");
    
    }
    
    public void deleteRecipe(Recipe recipe) {
        recipeBook.remove(recipe);
    }

    public void editRecipe(Recipe recipe, Scanner scan) {
        System.out.println("Editing Recipe: " + recipe.getName());

        boolean editing = true;
        while (editing) {
            System.out.println("\nCurrent Ingredients: ");
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                Ingredient ingredient = recipe.getIngredients().get(i);
                System.out.println((i + 1) + ". " + ingredient.getQuantity() + " " + ingredient.getUnit() + " " + ingredient.getName());
            }

            System.out.println("\n1. Edit ingredient");
            System.out.println("2. Add ingredient");
            System.out.println("3. Delete ingredient");
            System.out.println("4. Done editing");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt() - 1; // subtract 1 to account for 0-based indexing();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the number of the ingredient to edit: ");
                    int ingredientIndex = scan.nextInt() - 1;
                    scan.nextLine();
                    
                    if (ingredientIndex > 0 && ingredientIndex < recipe.getIngredients().size()) {
                        Ingredient ingredient = recipe.getIngredients().get(ingredientIndex - 1);
                        System.out.print("Enter new quantity for " + ingredient.getName() + ": ");
                        double newQuantity = scan.nextDouble();
                        scan.nextLine();
                        ingredient.setQuantity(newQuantity);
                        System.out.println("Quantity updated.");
                    } else {
                        System.out.println("Invalid selection.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter new ingredient name: ");
                    String newIngredientName = scan.nextLine();
                    System.out.print("Enter quantity: ");
                    double newQuantity = scan.nextDouble();
                    scan.nextLine(); 
                    System.out.print("Enter unit: ");
                    String newUnit = scan.nextLine();
                    recipe.addIngredient(new Ingredient(newIngredientName, newQuantity, newUnit));
                    System.out.println("Ingredient added.");
                }
                case 3 -> {
                    System.out.print("Enter the number of the ingredient to remove: ");
                    int removeIndex = scan.nextInt() - 1;
                    scan.nextLine(); // consume newline
                    if (removeIndex >= 0 && removeIndex < recipe.getIngredients().size()) {
                        recipe.getIngredients().remove(removeIndex);
                        System.out.println("Ingredient removed.");
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    
                }
                case 4 -> // done editing
                    editing = false;
                default -> System.out.println("Invalid choice");
            }
        
        }
    
    }

    public boolean hasRecipes() {
        return !recipeBook.isEmpty();
    }
    
    public void saveRecipesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Recipe recipe : recipeBook) {
                writer.write(recipe.getName() + "\n");
                for (Ingredient ingredient : recipe.getIngredients()) {
                    writer.write(ingredient.getName() + "," + ingredient.getQuantity() + "," + ingredient.getUnit() + "\n");
                }
                writer.write(recipe.getInstructions() + "\n\n"); // Separate recipes by a blank line
            }
            System.out.println("Recipes saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving recipes: " + e.getMessage());
        }
    }

    public void loadRecipesFromFile(String filename) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        String currentRecipeName = null;
        ArrayList<Ingredient> currentIngredients = new ArrayList<>();
        String currentInstructions = null;

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) { // New recipe block
                if (currentRecipeName != null) {
                    recipeBook.add(new Recipe(currentRecipeName, currentIngredients, currentInstructions));
                }
                currentRecipeName = null;
                currentIngredients = new ArrayList<>();
                currentInstructions = null;
            } else if (currentRecipeName == null) { // First line of recipe
                currentRecipeName = line;
            } else if (currentInstructions == null) { // After ingredients
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String ingredientName = parts[0];
                    double quantity = Double.parseDouble(parts[1]);
                    String unit = parts[2];
                    currentIngredients.add(new Ingredient(ingredientName, quantity, unit));
                }
            } else {
                currentInstructions = line; // Recipe instructions
            }
        }

        // Add the last recipe if there's no empty line at the end
        if (currentRecipeName != null) {
            recipeBook.add(new Recipe(currentRecipeName, currentIngredients, currentInstructions));
        }

        System.out.println("Recipes loaded from " + filename);
    } catch (IOException e) {
        System.err.println("Error loading recipes: " + e.getMessage());
    }
}
}
