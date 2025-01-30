public class FruitVegFood extends SingleFood {
    private String subType;

    public FruitVegFood(int calories, String subType, String description) {
        this.calories = calories;
        this.subType = subType;
        this.description = description;
    }

    public String getType() {
        return "FRUITVEG"; 
    }
    public String getCategory() {
        return subType.equals("FRUIT") ? "Fruit" : "Vegetable"; 
    }
    public String getDetails() {
        return subType + " " + description + " (" + calories + " calories)"; 
    }
}
