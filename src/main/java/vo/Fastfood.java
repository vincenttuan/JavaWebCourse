package vo;

import java.util.ArrayList;
import java.util.List;

public class Fastfood {
    private boolean firsttime;
    private List<Food> foods = new ArrayList<>();
    
    public boolean isFirsttime() {
        return firsttime;
    }

    public void setFirsttime(boolean firsttime) {
        this.firsttime = firsttime;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void addFood(Food food) {
        foods.add(food);
    }
    
}
