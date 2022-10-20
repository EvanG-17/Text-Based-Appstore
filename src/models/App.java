package models;

import utils.RatingUtility;
import utils.Utilities;
import java.util.ArrayList;

//Fields
public abstract class App {
    private Developer developer;
    private String appName = "No app name";
    private double appSize = 0;
    private double appVersion = 1.0;
    private double appCost = 0;
    private ArrayList <Rating> ratings;

    //Constructor
    public App(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        this.developer = developer;
        this.appName = appName;
        this.appSize = appSize;
        this.appVersion = appVersion;
        this.appCost = appCost;


        this.ratings = new ArrayList<>();

    }

    //this method is created in gameApp, educationApp and productivityApp hence why it is abstract as it is not completed here.
    public abstract boolean isRecommendedApp();





    public App setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }



    //Getters. Generated from above fields
    public Developer getDeveloper() {
        return developer;
    }

    public String getAppName() {
        return appName;
    }

    //setting a local variable then checking the users input, if the input is correct it updates the local variable with the users input
    //else it updates the local variable with a default value of 0, then it returns the local variable to the constructor
    public double getAppSize() {
        double localAppSize;
        if(Utilities.validRange(appSize,1,1000))
            localAppSize = appSize;
        else localAppSize = 0;
        return localAppSize;
    }




    //setting a local variable then checking users input, if correct update the local variable with the users input
    //else update the local variable with 1.0, then it returns the local variable to the constructor
    public double getAppVersion() {
        double localAppVersion;
        if(Utilities.greaterThanOrEqualTo(appVersion,1.0))
            localAppVersion = appVersion;
        else localAppVersion = 1.0;
        return localAppVersion;

    }




    public double getAppCost() {
        if (Utilities.greaterThanOrEqualTo(appCost, 0.0))
            return this.appCost;

        return 0;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }



    //Setters. These were generated based on my inputted fields above
    public void setDeveloper(Developer developer) {
        this.developer = developer;

    }

    public void setAppName(String appName) {
        this.appName = appName;

    }

    public void setAppSize(double appSize) {
        if (Utilities.validRange(appSize, 1, 1000)) {
            this.appSize = appSize;

        }

    }

    public void  setAppVersion(double appVersion) {
        if (appVersion >= 1.0) {
            this.appVersion = appVersion;
        }

    }



    public void setAppCost(double appCost) {
        if (Utilities.greaterThanOrEqualTo(appCost,0)) {
            this.appCost = appCost;
        }

    }
    //Method that adds a rating to the array list of rating,

    public boolean addRating(Rating rating){
             ratings.add(rating);
             return true;

    }
    //Getting array list of ratings and converting it to a readable format
    public String listRating()
    {
        for (int i = 0;i< ratings.size();i++){
            return ratings.toString();
        }
        return "No Ratings Added";
    }


    public double calculateRating(){
    double average = 0;
     for (int i = 0;i < ratings.size();i++){ //going through arrayList
         average = average + ratings.get(i).getNumberOfStars(); // adds all the number of stars into one variable
     }
     return average / ratings.size();
    }

    // creating A app summary string
    public String appSummary(){
        return "level " + getAppName() +"(V" + getAppVersion() + ")" + getDeveloper().toString() + "€" + getAppCost()
                +"Rating: "+ calculateRating() ;
    }



    @Override
    public String toString() {
        return "App{" +
                "developer=" + developer +
                ", appName='" + appName + '\'' +
                 appSize +  "MB" +
                "(Version " + appVersion +
                "Cost: " + appCost +
                ", ratings=" + ratings +
                '}';
    }
}
