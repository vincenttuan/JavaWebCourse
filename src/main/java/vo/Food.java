package vo;

import java.io.Serializable;

public class Food implements Serializable{
    private String title;
    private String amount;

    public Food(String title, String amount) {
        this.title = title;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Food{" + "title=" + title + ", amount=" + amount + '}';
    }
    
    
}
