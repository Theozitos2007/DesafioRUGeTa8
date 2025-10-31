import java.util.Objects;

public class MealRU {
    //meal can be an object. point to one salad one main one side.
    FoodRU main = null;
    FoodRU side = null;
    FoodRU salad = null;

    MealRU( FoodRU main, FoodRU side, FoodRU salad){
        this.main = main;
        this.side = side;
        this.salad = salad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealRU mealRU = (MealRU) o;
        return Objects.equals(main, mealRU.main) && Objects.equals(side, mealRU.side) && Objects.equals(salad, mealRU.salad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(main, side, salad);
    }
}
