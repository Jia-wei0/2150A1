import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static FoodLibrary foodLib = new FoodLibrary();
    private static UserLibrary userLib = new UserLibrary();
    private static Scanner fileScanner;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <input-file>");
            return;
        }

        try {
            fileScanner = new Scanner(new File(args[0]));
            while (fileScanner.hasNextLine()) {
                processLine(fileScanner.nextLine().trim());
            }
            System.out.println("DONE");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (fileScanner != null) fileScanner.close();
        }
    }

    private static void processLine(String line) {
        if (line.startsWith("#")) {
            System.out.println(line);
            return;
        }

        String[] tokens = line.split("\\s+");
        if (tokens.length == 0) return;

        switch (tokens[0]) {
            case "NEWPROFILE": handleNewProfile(tokens[1]); break;
            case "NEWFOOD": handleNewFood(line); break;
            case "EAT": handleEat(line); break;
            case "PRINTCALORIES": printCalories(tokens[1]); break;
            case "PRINTSERVINGS": printServings(tokens[1]); break;
            case "PRINTMEALS": printMeals(tokens[1]); break;
            case "QUIT": System.exit(0);
        }
    }

    private static void handleNewProfile(String username) {
        if (userLib.find(username) == null) {
            userLib.add(new User(username));
            System.out.println("NEW USER ADDED");
        } else {
            System.out.println("DUPLICATE USER NOT ADDED");
        }
    }

    private static void handleNewFood(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length < 2) return;

        if (tokens[1].equals("SINGLE")) {
            handleNewSingleFood(tokens);
        } else if (tokens[1].equals("COMPOSITE")) {
            handleNewCompositeFood(tokens);
        }
    }

    private static void handleNewSingleFood(String[] tokens) {
        String type = tokens[2];
        int calories = Integer.parseInt(tokens[3]);
        String description;

        try {
            switch (type) {
                case "FRUITVEG":
                    String subType = tokens[4];
                    description = String.join(" ", Arrays.copyOfRange(tokens, 5, tokens.length));
                    Food existingFV = foodLib.find(description);
                    if (existingFV != null) {
                        existingFV.calories = calories;
                        System.out.println("FOOD UPDATED");
                    } else {
                        foodLib.add(new FruitVegFood(calories, subType, description));
                        System.out.println("NEW FOOD ADDED");
                    }
                    break;
                case "DAIRY":
                    double protein = Double.parseDouble(tokens[4]);
                    double fat = Double.parseDouble(tokens[5]);
                    String animal = tokens[6];
                    description = String.join(" ", Arrays.copyOfRange(tokens, 7, tokens.length));
                    Food existingDairy = foodLib.find(description);
                    if (existingDairy != null) {
                        ((DairyFood) existingDairy).calories = calories;
                        ((DairyFood) existingDairy).protein = protein;
                        ((DairyFood) existingDairy).fat = fat;
                        System.out.println("FOOD UPDATED");
                    } else {
                        foodLib.add(new DairyFood(calories, protein, fat, animal, description));
                        System.out.println("NEW FOOD ADDED");
                    }
                    break;
                case "MEAT":
                    double meatProtein = Double.parseDouble(tokens[4]);
                    double meatFat = Double.parseDouble(tokens[5]);
                    String meatAnimal = tokens[6];
                    description = String.join(" ", Arrays.copyOfRange(tokens, 7, tokens.length));
                    Food existingMeat = foodLib.find(description);
                    if (existingMeat != null) {
                        ((MeatFood) existingMeat).calories = calories;
                        ((MeatFood) existingMeat).protein = meatProtein;
                        ((MeatFood) existingMeat).fat = meatFat;
                        System.out.println("FOOD UPDATED");
                    } else {
                        foodLib.add(new MeatFood(calories, meatProtein, meatFat, meatAnimal, description));
                        System.out.println("NEW FOOD ADDED");
                    }
                    break;
                case "GRAIN":
                    double grainProtein = Double.parseDouble(tokens[4]);
                    double grainFat = Double.parseDouble(tokens[5]);
                    String plant = tokens[6];
                    description = String.join(" ", Arrays.copyOfRange(tokens, 7, tokens.length));
                    Food existingGrain = foodLib.find(description);
                    if (existingGrain != null) {
                        ((GrainFood) existingGrain).calories = calories;
                        ((GrainFood) existingGrain).protein = grainProtein;
                        ((GrainFood) existingGrain).fat = grainFat;
                        System.out.println("FOOD UPDATED");
                    } else {
                        foodLib.add(new GrainFood(calories, grainProtein, grainFat, plant, description));
                        System.out.println("NEW FOOD ADDED");
                    }
                    break;
                case "OTHER":
                    double otherProtein = Double.parseDouble(tokens[4]);
                    double otherFat = Double.parseDouble(tokens[5]);
                    double sugar = Double.parseDouble(tokens[6]);
                    description = String.join(" ", Arrays.copyOfRange(tokens, 7, tokens.length));
                    Food existingOther = foodLib.find(description);
                    if (existingOther != null) {
                        ((OtherFood) existingOther).calories = calories;
                        ((OtherFood) existingOther).protein = otherProtein;
                        ((OtherFood) existingOther).fat = otherFat;
                        ((OtherFood) existingOther).sugar = sugar;
                        System.out.println("FOOD UPDATED");
                    } else {
                        foodLib.add(new OtherFood(calories, otherProtein, otherFat, sugar, description));
                        System.out.println("NEW FOOD ADDED");
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("FAILURE TO ADD FOOD");
        }
    }

    private static void handleNewCompositeFood(String[] tokens) {
        int numComponents = Integer.parseInt(tokens[2]);
        String description = tokens[3];
        LinkedList<Food> components = new LinkedList<>();

        try {
            for (int i = 0; i < numComponents; i++) {
                if (!fileScanner.hasNextLine()) {
                    System.out.println("FAILURE TO ADD FOOD");
                    return;
                }
                String componentLine = fileScanner.nextLine().trim();
                Food component = foodLib.find(componentLine);
                if (component == null) {
                    System.out.println("FAILURE TO ADD FOOD");
                    return;
                }
                components.add(component);
            }

            Food existing = foodLib.find(description);
            if (existing != null) {
                if (existing instanceof CompositeFood) {
                    ((CompositeFood) existing).components = components;
                    ((CompositeFood) existing).calories = ((CompositeFood) existing).calculateCalories();
                    System.out.println("FOOD UPDATED");
                } else {
                    System.out.println("FAILURE TO ADD FOOD");
                }
            } else {
                foodLib.add(new CompositeFood(description, components));
                System.out.println("NEW FOOD ADDED");
            }
        } catch (Exception e) {
            System.out.println("FAILURE TO ADD FOOD");
        }
    }

    // Main.java
private static void handleEat(String line) {
    String[] parts = line.split("\\s+", 3); // 分割为3部分
    if (parts.length < 3) {
        System.out.println("INVALID COMMAND");
        return;
    }
    String username = parts[1];
    String foodDesc = parts[2];
    
    User user = userLib.find(username);
    Food food = foodLib.find(foodDesc);
    
    if (user == null) {
        System.out.println("USER NOT FOUND");
        return;
    }
    if (food == null) {
        System.out.println("FOOD NOT FOUND");
        return;
    }
    
    user.addMeal(food);
    System.out.println("MEAL RECORDED");
}

    private static void printCalories(String username) {
        User user = userLib.find(username);
        if (user == null) {
            System.out.println("USER NOT FOUND");
            return;
        }
        System.out.println("TOTAL CALORIES = " + user.calculateTotalCalories());
    }

    private static void printServings(String username) {
        User user = userLib.find(username);
        if (user == null) {
            System.out.println("USER NOT FOUND");
            return;
        }

        int fruitVeg = 0;
        int dairy = 0;
        int meat = 0;
        int grain = 0;
        int other = 0;

        Node<Food> current = user.getMeals().getHead();
        while (current != null) {
            String category = current.data.getCategory();
            switch (category) {
                case "Fruit":
                case "Vegetable": fruitVeg++; break;
                case "Dairy": dairy++; break;
                case "Meat": meat++; break;
                case "Grain": grain++; break;
                case "Other": other++; break;
            }
            current = current.next;
        }

        System.out.println("Serving history for " + username + ":");
        System.out.println("Fruit & Vegetable: " + fruitVeg);
        System.out.println("Dairy: " + dairy);
        System.out.println("Meat: " + meat);
        System.out.println("Grain: " + grain);
        System.out.println("Other: " + other);
    }

    private static void printMeals(String username) {
        User user = userLib.find(username);
        if (user == null) {
            System.out.println("USER NOT FOUND");
            return;
        }

        System.out.println("Meal history for " + username + ":");
        Node<Food> current = user.getMeals().getHead();
        while (current != null) {
            Food food = current.data;
            if (food instanceof CompositeFood) {
                System.out.println(food.getDescription() + ":");
                CompositeFood cf = (CompositeFood) food;
                Node<Food> compNode = cf.components.getHead();
                while (compNode != null) {
                    System.out.println("  " + compNode.data.getType() + " " + compNode.data.getDetails());
                    compNode = compNode.next;
                }
            } else {
                System.out.println(food.getType() + " " + food.getDetails());
            }
            current = current.next;
        }
    }
}