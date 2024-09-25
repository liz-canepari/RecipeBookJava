import java.util.Scanner;
public class UI {
    private final RecipeManager recipeManager = new RecipeManager();
    private final Scanner scanner = new Scanner(System.in);
    
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice){
                case 1 -> recipeManager.addRecipe(scanner);
                case 2 -> recipeManager.displayRecipe();
                case 3 -> recipeManager.editRecipe();
                case 4 -> recipeManager.deleteRecipe();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Recipe Book Menu:");
        System.out.println("1. Add Recipe");
        System.out.println("2. Display Recipe");
        System.out.println("3. Edit Recipe");
        System.out.println("4. Delete Recipe");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}
