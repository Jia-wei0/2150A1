public class GrainFood extends SingleFood {
    double protein;
    double fat;
    private String plant;

    public GrainFood(int calories, double protein, double fat, String plant, String description) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.plant = plant;
        this.description = description;
    }

    public String getType() { return "GRAIN"; }
    public String getCategory() { return "Grain"; }
    public String getDetails() { return description + " (" + plant + ", " + calories + " calories)"; }
}