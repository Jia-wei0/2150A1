public class User {
    private String username;
    private LinkedList<Food> meals;

    public User(String username) {
        this.username = username;
        this.meals = new LinkedList<>();
    }

    public void addMeal(Food food) {
        meals.add(food); 
    }

    public LinkedList<Food> getMeals() {
        return meals;
    }

    public int calculateTotalCalories() {
        int total = 0;
        Node<Food> current = meals.getHead();
        while (current != null) {
            total += current.data.getCalories();
            current = current.next;
        }
        return total;
    }

    public void calculateServings(int[] counts) {
        Node<Food> current = meals.getHead();
        while (current != null) {
            countFood(current.data, counts);
            current = current.next;
        }
    }

    private void countFood(Food food, int[] counts) {
        if (food instanceof CompositeFood) {
            CompositeFood composite = (CompositeFood) food;
            Node<Food> compNode = composite.getComponents().getHead();
            while (compNode != null) {
                countFood(compNode.data, counts); // 递归处理组件
                compNode = compNode.next;
            }
        } else {
            switch (food.getCategory()) {
                case "Fruit & Vegetable": counts[0]++; break;
                case "Dairy": counts[1]++; break;
                case "Meat": counts[2]++; break;
                case "Grain": counts[3]++; break;
                case "Other": counts[4]++; break;
            }
        }
    }

    public String getUsername() {
        return username;
    }
}