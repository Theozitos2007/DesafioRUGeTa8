import java.util.ArrayList;
import java.util.Objects;

public class FoodRU {
    //* food can be an object: string param to type the food. value of if its salad main or side.
    String name = null; // name of food ex carne assada, salada cozida
    int type = 0; //
    // 0 = undefined
    // 1 = main dish
    // 2 = side dish
    // 3 = salad

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodRU foodRU = (FoodRU) o;
        return type == foodRU.type && Objects.equals(name, foodRU.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
    // 3 = salad

    FoodRU(String name, int type){
        this.name = name;
        this.type = type;
    }

}
