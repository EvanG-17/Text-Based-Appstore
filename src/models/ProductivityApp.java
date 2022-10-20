package models;

public class ProductivityApp extends App{


    //constructor

    public ProductivityApp (Developer developer,String appName,double appSize,double appVersion,double appCost) {
        super(developer,appName,appSize,appVersion,appCost);

    }


    //@override overrides a METHOD in the PARENT class
    //This method returns a boolean indicating if the app is recommended or not based on the the price being greater than .99 and the
    @Override
    public boolean isRecommendedApp(){
        if ((appCost() >= 1.99) && (calculateRating() > 3.0)){
            return true;
        }
        return false;
    }

    private double appCost() {
        return 0;
    }


    //@override overrides a METHOD in the PARENT class
    //toString from app
    @Override
    public String toString() {
        return super.toString();
    }
}
