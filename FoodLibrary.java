public class FoodLibrary {
    private LinkedList<Food> foods = new LinkedList<>();

    public void add(Food food) { foods.add(food); }
    public Food find(String description) { return foods.find(description); }
}