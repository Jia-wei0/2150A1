//-----------------------------------------
// NAME		: Jiawei Fan 
// STUDENT NUMBER	: 7909503
// COURSE		: COMP 2150
// INSTRUCTOR	: Heather Matheson
// ASSIGNMENT	: assignment 1
// QUESTION	: question 1      
// 
// REMARKS: Meal tracking system for recording user diets and nutritional information
//
//
//-----------------------------------------

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

    //------------------------------------------------------
    // processLine
    //
    // PURPOSE: Process single input line
    // PARAMETERS:
    //   line: the input line
    //------------------------------------------------------
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

    //------------------------------------------------------
    // handleNewProfile
    //
    // PURPOSE: Handle user profile creation
    // PARAMETERS:
    //   username: create username
    // OUTPUT:
    //   "NEW USER ADDED" if successful
    //   "DUPLICATE USER NOT ADDED" if username exists
    //------------------------------------------------------
    private static void handleNewProfile(String username) {
        if (userLib.find(username) == null) {
            userLib.add(new User(username));
            System.out.println("NEW USER ADDED");
        } else {
            System.out.println("DUPLICATE USER NOT ADDED");
        }
    }

    //------------------------------------------------------
    // handleNewFood
    //
    // PURPOSE: Handle food profile creation
    // PARAMETERS:
    //   line:  full command line start with "NEWFOOD"
    //------------------------------------------------------
    private static void handleNewFood(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length < 2) return;

        if (tokens[1].equals("SINGLE")) {
            handleNewSingleFood(tokens);
        } else if (tokens[1].equals("COMPOSITE")) {
            handleNewCompositeFood(tokens);
        }
    }

    //------------------------------------------------------
    // handleNewSingleFood
    //
    // PURPOSE: Process single food item creation
    // PARAMETERS:
    //   tokens: tokenized command parts:
    //      [0]NEWFOOD [1]SINGLE [2]TYPE [3]CALORIES
    // OUTPUT:
    //      "NEW FOOD ADDED" 
    //      "FOOD UPDATED"
    //------------------------------------------------------
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

    //------------------------------------------------------
    // handleNewCompositeFood
    //
    // PURPOSE: Process composite food item creation
    // PARAMETERS:
    //  tokens: tokenized command parts:
    //      [0]NEWFOOD [1]COMPOSITE [2]NUM_COMPONENTS [3]DESCRIPTION
    // OUTPUT:
    //  "NEW FOOD ADDED" if successful
    //  "FOOD UPDATED" if updating existing
    //  "FAILURE TO ADD FOOD" if components missing
    //------------------------------------------------------
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

    //------------------------------------------------------
    // handleEat
    //
    // PURPOSE: Record eat food event
    // PARAMETERS:
    //   line: full command line starting with "EAT"
    // OUTPUT:
    //   "MEAL RECORDED" if successful
    //   "USER NOT FOUND" or "FOOD NOT FOUND" on failure
    //------------------------------------------------------
    private static void handleEat(String line) {
        String[] parts = line.split("\\s+", 3);
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

    //------------------------------------------------------
    // printServings
    //
    // PURPOSE: Print food category counts for a user
    // PARAMETERS:
    //   username: target user
    // OUTPUT:
    //   Category counts or "USER NOT FOUND"
    //------------------------------------------------------
    private static void printServings(String username) {
        User user = userLib.find(username);
        if (user == null) {
            System.out.println("USER NOT FOUND");
            return;
        }
    
        int[] counts = new int[5]; // 0:F&V, 1:Dairy, 2:Meat, 3:Grain, 4:Other
        user.calculateServings(counts);
    
        System.out.println("Serving history for " + username + ":");
        System.out.println("Fruit & Vegetable: " + counts[0]);
        System.out.println("Dairy: " + counts[1]);
        System.out.println("Meat: " + counts[2]);
        System.out.println("Grain: " + counts[3]);
        System.out.println("Other: " + counts[4]);
    }

    //------------------------------------------------------
    // printMeals
    //
    // PURPOSE: Print detailed meal history for a user
    // PARAMETERS:
    //   username: target user
    // OUTPUT:
    //   Meal history or "USER NOT FOUND"
    //------------------------------------------------------
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