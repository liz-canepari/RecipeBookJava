public class Ingredient {

    private final String name;
    private final double quantity;
    private final String unit;

    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
    
    // Getters
    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
    
}
