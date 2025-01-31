// CLASS: OtherFood
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: other food item
// 
//-----------------------------------------
public class OtherFood extends SingleFood {
    double sugar;

    public OtherFood(int calories, double protein, double fat, double sugar, String description) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.description = description;
    }

    public String getType() { return "OTHER"; }
    public String getCategory() { return "Other"; }
    public String getDetails() { return description + " (" + calories + " calories)"; }
}