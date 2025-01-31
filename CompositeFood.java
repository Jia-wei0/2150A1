// CLASS: SingleFood
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: Composite food items
// 
//-----------------------------------------
public class CompositeFood extends Food {
    LinkedList<Food> components;
    
    //------------------------------------------------------
    // CompositeFood
    //
    // PURPOSE:    Initialize composite food
    // PARAMETERS:
    //     description: Food description
    //     components: List of component foods
    //------------------------------------------------------
    public CompositeFood(String description, LinkedList<Food> components) {
        this.description = description;
        this.components = components;
        this.calories = calculateCalories();
    }

    public int calculateCalories() {
        int total = 0;
        Node<Food> current = components.getHead();
        while (current != null) {
            total += current.data.getCalories();
            current = current.next;
        }
        return total;
    }

    public String getType() { 
        return "COMPOSITE"; 
    }

    public String getCategory() { 
        return "Composite"; 
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        Node<Food> current = components.getHead();
        while (current != null) {
            sb.append(current.data.getType()).append(": ").append(current.data.getDetails()).append("\n");
            current = current.next;
        }
        return sb.toString().trim();
    }

    public LinkedList<Food> getComponents() {
        return components;
    }
}