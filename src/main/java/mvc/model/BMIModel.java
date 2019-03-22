package mvc.model;

public class BMIModel {
    private double height;
    private double weight;
    private double bmi;
    
    public BMIModel(double height, double weight) {
        this.height = height;
        this.weight = weight;
        bmi = weight/Math.pow(height/100, 2);
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getBmi() {
        return bmi;
    }
    
    
    
}
