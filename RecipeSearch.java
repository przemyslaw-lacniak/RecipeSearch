
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;

public class RecipeSearch {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        ArrayList<String> allRecipes = new ArrayList<>();
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        List<String> ingredients;

        System.out.println("File to read: ");
        String file = scan.nextLine();

        try ( Scanner scanner = new Scanner(Paths.get(file))) {

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if (row.isEmpty()) {
                    continue;
                }
                allRecipes.add(row);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (String recipe : allRecipes) {
            if (isInteger(recipe)) {
                indices.add(allRecipes.indexOf(recipe));
            }
        }

        for (int i = 0; i < indices.size() - 1; i++) {
            int time = Integer.valueOf(allRecipes.get(indices.get(i)));
            String name = allRecipes.get(indices.get(i) - 1);

            recipes.add(new Recipe(name, time, allRecipes.subList(indices.get(i) + 1, indices.get(i + 1) - 1)));
        }

        int j = indices.size() - 1;
        int time = Integer.valueOf(allRecipes.get(indices.get(j)));
        String name = allRecipes.get(indices.get(j) - 1);
        recipes.add(new Recipe(name, time, allRecipes.subList(indices.get(j) + 1, allRecipes.size())));

        //user interface starting
        System.out.println("Commands:");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        System.out.println("find name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time");
        System.out.println("find ingredient - searches recipes by ingredient");
        System.out.println("");

        while (true) {
            System.out.print("Enter command: ");
            String command = scan.nextLine();
            System.out.println("");
            //if command is stop, break the loop and end the program
            if (command.equals("stop")) {
                break;
            }
            //if command is list, list all the recipes
            if (command.equals("list")) {
                System.out.println("Recipes:");
                for (Recipe recipe : recipes) {
                    System.out.println(recipe);
                }
                System.out.println("");
            }
            //if command is find name, list all the recipes names that contain that word
            if (command.equals("find name")) {
                System.out.print("Searched word: ");
                String searchedWord = scan.nextLine();
                System.out.println("Recipes:");
                for (Recipe recipe : recipes) {
                    if (recipe.getName().contains(searchedWord)) {
                        System.out.println(recipe);
                    }
                }
            }
            
            if (command.equals("find cooking time")) {
                System.out.println("Max cooking time: ");
                int maxTime = Integer.valueOf(scan.nextLine());
                System.out.println("Recipes:");
                for (Recipe recipe: recipes) {
                    if (recipe.getCookingTime() <= maxTime) {
                        System.out.println(recipe);
                    }
                }
            }
            
            if (command.equals("find ingredient")) {
                System.out.println("Ingredient: ");
                String ingredient = scan.nextLine();
                for (Recipe recipe: recipes) {
                        if (recipe.getIngredients().contains(ingredient)) {
                            System.out.println(recipe);
                        }
                    }
                }
            }

        }

    

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
