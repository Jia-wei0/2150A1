public class DairyFood extends SingleFood {
    double protein;
    double fat;
    private String animal;

    public DairyFood(int calories, double protein, double fat, String animal, String description) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.animal = animal;
        this.description = description;
    }

    public String getType() { return "DAIRY"; }
    public String getCategory() { return "Dairy"; }
    public String getDetails() { return description + " (" + animal + ", " + calories + " calories)"; }
}