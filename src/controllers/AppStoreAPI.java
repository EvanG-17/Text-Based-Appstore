package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.*;
import utils.ISerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.random;
import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI implements ISerializer{     //ISerializer implements the save and load methods

    public List<App> apps = new ArrayList<>();

    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)

    //---------------------
    // Method to simulate ratings (using the RatingUtility).
    // This will be called from the Driver (see skeleton code)
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED method as you start working through this class
    //---------------------

    public boolean addApp(App app){
        apps.add(app);
        return true;
    }

    public String listAllApps(){
        if(apps.isEmpty()){
            return "No apps";
        }else{
            String allApps = "";
            for(int i = 0; i< apps.size(); i++){                          //array counting up
                allApps = allApps + apps.get(i).toString() + "\n";          //adds all toStrings into one variable and returns that variable
            }
            return allApps;
        }
    }


    public String listSummaryOfAllApps(){
        if(apps.isEmpty()){
            return "No apps";
        }else{
            String summaryOfAllApps = "";
            for(int i = 0; i< apps.size(); i++){
                summaryOfAllApps += apps.get(i).appSummary();           //method exact same except this time it is with appSummary
            }
            return summaryOfAllApps;
        }
    }

    public String listAllGameApps(){
        if(apps.isEmpty()){
            return "No apps";
        }else{
            String allGameApps = "";
            for(int i = 0; i< apps.size(); i++) {
                if (apps instanceof GameApp) {                        // checks array for GameApps's ONLY.
                    allGameApps += apps.get(i).toString();            //every time there is a game app it adds the string together then returns as a big string
                }
            }
            return allGameApps;
        }
    }

    public String listAllEducationApps(){
        if(apps.isEmpty()){
            return "No apps";
        }else{
            String allEducationApps = "";
            for(int i = 0; i< apps.size(); i++) {
                if (apps instanceof EducationApp) {
                    allEducationApps += apps.get(i).toString();  //same as above method
                }
            }
            return allEducationApps;
        }
    }

    public String listAllProductivityApps(){
        if(apps.isEmpty()){
            return "No apps";
        }else{
            String allProductivityApps = "";
            for(int i = 0; i< apps.size(); i++) {
                if (apps instanceof ProductivityApp) {
                    allProductivityApps += apps.get(i).toString();  //same as above method
                }
            }
            return allProductivityApps;
        }
    }


        public String listAllAppsByName(String name){
            String appName = "";
                for(int i = 0; i < apps.size(); i++) {
                    if(apps.get(i).getAppName().equals(name)) {                   // checks array for an app with the name you are looking for
                        appName += apps.get(i).toString();                         // returns apps with the name you have specified
                    }
                }
                return appName;
        }


    public String listAllAppsAboveOrEqualAGivenStarRating(int starRating) {
        String appName = "";

        if (Utilities.validRange(starRating, 1, 5)) { //error handling.

            for (int i = 0; i < apps.size(); i++) {                                            //for loop going through apps
                for (int j = 0; j < apps.get(i).getRatings().size(); j++) {                    // for loop for every rating in an app
                    if (apps.get(i).getRatings().get(j).getNumberOfStars() >= starRating) {    //
                        appName += apps.get(i).toString();
                    }
                }
            }
        }
        return appName;
    }


    public String listAllRecommendedApps(){
        if(apps.isEmpty()){
            return "No apps";
        }else{
            String allRecommendedApps = "";
            for(int i = 0; i< apps.size(); i++) {
                if (apps.get(i).isRecommendedApp()) {
                    allRecommendedApps += apps.get(i).toString();  //same as above method
                }
            }
            return allRecommendedApps;
        }
    }

//    public int listAnInt(){
//        int counter = 0;
//        for(int i = 0; i < apps.size(); i++) {     //blueprint for creating listing methods that return an int.
//               counter++;
//        }
//        return counter;
//    }

    public String listAllAppsByChosenDeveloper(Developer developer){
        String appName = "";

        for(int i = 0; i < apps.size(); i++) {
            if(apps.get(i).getAppName().equals(developer)) {                          // checks array for an app by chosen developer user specified
                appName += apps.get(i).toString();                                    // every time there is a app it adds the string
                                                                                      // together then returns as a big string
            }
        }
        return appName;
    }


    public int numberOfAppsByChosenDeveloper(Developer developer){
        int counter = 0;
        for(int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getDeveloper().equals(developer)) //getting the developer of each app from an array and seeing if it matches the one the user asked for
            counter++;                         //if it does match it makes the counter increase by one
        }
        return counter;
    }


    public App deleteAppByIndex(int index){
        apps.remove(index);                 //removes selected index from array
        return apps.get(index);

    }

    Random random = new Random(); //initialising random object
    public App randomApp(){
        return apps.get(random.nextInt(apps.size()));  // returns random index from apps arraylist
    }


    public void simulateRatings(){
        for(App app : apps) {
            app.addRating(generateRandomRating());
        }
    }


    public boolean isValidAppName(String validAppName){
        return(Utilities.validRange(Integer.parseInt(validAppName),0,30)); //converts amount of char in a string to a number
    }

    public App getAppByName(String appName){

        for(int i = 0;i < apps.size(); i++){                       //searching the whole app array
            if(apps.get(i).getAppName().equals(appName)){          //if an app in the array and equals user input, return
                return apps.get(i);
            }
        }
        return null;
    }

    public App getAppByIndex(int index){

        for(int i =0; i < apps.size(); i++){
            if(apps.get(i).equals(index)){          //if apps in the array equal the index, return the apps
                return apps.get(i);
            }
        }
        return null;
    }

    public boolean isValidIndex(int validIndex){
       return (validIndex >= 0) && (validIndex >= apps.size());
    }

        public int numberOfApps(){
        int counter = 0;
        for(int i = 0; i < apps.size(); i++) {     //counts up 1 for each app
               counter++;
        }
        return counter;                             //returns what is counted
    }

    private void swapApps(ArrayList<App> apps, int appA , int appB){

        App first = apps.get(appA);                     //from notes
        App second = apps.get(appB);

        apps.set(appA,second);
        apps.set(appB,first);

    }

    public void sortAppByNameAscending(){  //sort by first name alphabetically
        for (int i = apps.size() -1; i >= 0; i--)                //from notes
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapApps((ArrayList<App>) apps, i, highestIndex);
        }

    }

















    //---------------------
    // Persistence methods
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED CODE block as you start working through this class
    //---------------------

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    public String fileName(){
        return "apps.xml";
    }

}