package models;

import utils.Utilities;

public class    EducationApp extends App{

    //field
    private int level ;

    //constructor

    public EducationApp (Developer developer,String appName,double appSize,double appVersion,double appCost,int level) {
        super(developer,appName,appSize,appVersion,appCost);
        this.level = level;
    }



    //This method returns a boolean indicating if the app is recommended or not. @override overrides a METHOD in the PARENT class
    @Override

    //this method checks if it is a recommendedApp based on if its cost is greater than .99
    public boolean isRecommendedApp(){
        if (appCost()>0.99 && calculateRating()>=3.5 && level>=3){
            return true;
        }
        return false;
    }

    private double appCost() {
        return 0;
    }

    //setting a local variable then checking users input, if correct update the local variable with the users input
    //else update the local variable with 1.0, then it returns the local variable to the constructor
    public int getLevel() {
       int localGetLevel;
       if(Utilities.validRange(level,1,10))
           localGetLevel = level;
       else localGetLevel = 0;
       return localGetLevel;

    }

    public int setLevel(int level) {
        if (Utilities.validRange(level, 1, 10))
            this.level = level;
        return level;
    }

    public String appSummary() {
        return super.appSummary() + "level " + getLevel();
    }

    //@override overrides a METHOD in the PARENT class
    //toString from app, with the addition of field (level)
    @Override
    public String toString() {
        return super.toString()+level;
    }
}
