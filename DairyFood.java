// CLASS: DairyFood
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: Dairy food item
// 
//-----------------------------------------
public class DairyFood extends SingleFood {
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