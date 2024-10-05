import java.util.Scanner;
public class UI {
    private final RecipeManager recipeManager = new RecipeManager();
    private final Scanner scan = new Scanner(System.in);
    
    public void run() {
        boolean running = true;
        while (running) {
            displayOpeningMenu();
            int choice = scan.nextInt();
            scan.nextLine();
            
            switch (choice){
                case 1 -> recipeManager.addRecipe(scan);
                case 2 -> selectAndManageRecipe();
                case 3 -> saveRecipes();
                case 4 -> loadRecipes();
                case 5 -> {
                    running = false;
                    System.out.println("Goodbye!");}
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayOpeningMenu() {
        System.out.println("Recipe Book Menu:");
        System.out.println("1. Add Recipe");
        System.out.println("2. Select a Recipe");
        System.out.println("3. Save Recipe Book to File");
        System.out.println("4. Load Recipes Book from File");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void selectAndManageRecipe() {
        Recipe selectedRecipe = recipeManager.selectRecipe(scan);

        if (selectedRecipe != null) {
            displayRecipeManagementMenu(selectedRecipe);
        } else if (!recipeManager.hasRecipes()) {
            System.out.println("No recipes available.");
        }
    }

    private void displayRecipeManagementMenu(Recipe recipe) {
        System.out.println("\nManaging Recipe: " + recipe.getName());
        System.out.println("1. View Recipe");
        System.out.println("2. Edit Recipe");
        System.out.println("3. Delete Recipe");
        System.out.println("4. Go Back to Main Menu");
        System.out.print("Choose an option: ");

        int choice = scan.nextInt();
        scan.nextLine();
        switch (choice) {
            case 1 -> // View recipe details
                recipeManager.displayRecipe(recipe);
            case 2 -> // Edit recipe
                recipeManager.editRecipe(recipe, scan);
            case 3 -> {
                // Delete recipe
                recipeManager.deleteRecipe(recipe);
                System.out.println("Recipe deleted.");
            }
            case 4 -> // Go back to main menu
                System.out.println("Returning to main menu...");
            default -> System.out.println("Invalid choice. Returning to main menu...");
        }
    }

    private void saveRecipes() {
        System.out.print("Enter filename to save recipes: ");
        String filename = scan.nextLine();
        recipeManager.saveRecipesToFile(filename);
    }

    private void loadRecipes() {
        System.out.print("Enter filename to load recipes: ");
        String filename = scan.nextLine();
        recipeManager.loadRecipesFromFile(filename);
    }
}
