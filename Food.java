// CLASS: Food
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: Class for all food items 
//
//-----------------------------------------

public abstract class Food {
    protected String description;
    protected int calories;
    
    public String getDescription() {
        return description; 
    }

    public int getCalories() {
        return calories; 
    }
    
    public abstract String getType();
    public abstract String getCategory();
    public abstract String getDetails();
}
