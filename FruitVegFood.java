// CLASS: FruitVegFood
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: Fruit and vegetable food item
// 
//-----------------------------------------
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
        return "Fruit & Vegetable";
    }

    public String getDetails() {
        return subType + " " + description + " (" + calories + " calories)"; 
    }
}
