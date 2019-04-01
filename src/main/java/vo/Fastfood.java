package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fastfood implements Serializable {
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

    @Override
    public String toString() {
        return "Fastfood{" + "firsttime=" + firsttime + ", foods=" + foods + '}';
    }
    
}
