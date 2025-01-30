public class User {
    private String username;
    private LinkedList<Food> meals;

    public User(String username) {
        this.username = username;
        this.meals = new LinkedList<>();
    }

    public void addMeal(Food food) { meals.add(food); }
    public LinkedList<Food> getMeals() { return meals; }

    public int calculateTotalCalories() {
        int total = 0;
        Node<Food> current = meals.getHead();
        while (current != null) {
            total += current.data.getCalories();
            current = current.next;
        }
        return total;
    }

    public String getUsername() {
        return username;
    }
}