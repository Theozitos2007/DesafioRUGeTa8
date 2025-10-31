import java.sql.Array;
import java.util.ArrayList;

public class SharedVariablesRU {

    // im going to make an arraylist to store food.
    // this is needed to check if it already exists in here

    static ArrayList<FoodRU> foodList = new ArrayList<>();

    // arraylist for meals as well
    static ArrayList<MealRU> mealList = new ArrayList<>();

    // so that i can skip on changing the food type twice, or changing how the method outputs information
    static int dishType = 0;

    //bizzare idea: 2d array. 7 rows 2 collumns. index of row is day of week. index of column is morning or evening. inside contains the index of the meal in the meal list
    // y 1=morning 2=evening.
    // -1 should mean it has not been set by the user yet
    static int[][] weekMenu = { {-1,-1},
            {-1,-1},
            {-1,-1},
            {-1,-1},
            {-1,-1},
            {-1,-1},
            {-1,-1}};

    static boolean globalQuitTime = false;
    static boolean localQuitTime = false;
}
