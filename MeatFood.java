// CLASS: MeatFood
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: meat food item
// 
//-----------------------------------------
public class MeatFood extends SingleFood {
    private String animal;

    public MeatFood(int calories, double protein, double fat, String animal, String description) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.animal = animal;
        this.description = description;
    }

    public String getType() { return "MEAT"; }
    public String getCategory() { return "Meat"; }
    public String getDetails() { return description + " (" + animal + ", " + calories + " calories)"; }
}