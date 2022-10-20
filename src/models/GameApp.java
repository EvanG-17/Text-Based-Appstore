package models;

public class GameApp extends App{

    //fields
    private boolean isMultiplayer = false;


    public GameApp (Developer developer,String appName,double appSize,double appVersion,double appCost,boolean isMultiplayer) {
        super(developer, appName, appSize, appVersion, appCost);

        //updating the constructor
        this.isMultiplayer=isMultiplayer;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public GameApp setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
        return this;
    }

    //This method returns a boolean indicating if the app is recommended or not based on rating and multiplayer status.
    //@override overrides a METHOD in the PARENT class
    @Override
    public boolean isRecommendedApp(){
        if (isMultiplayer==true && calculateRating()>=4.0){
            return true;
        }
        return false;
    }

    //super is calling appSummary from App class
    ////@override overrides a METHOD in the PARENT class
    @Override
    public String toString() {
        return "GameApp{" +
                "multiplayer " + isMultiplayer +
                "} " + super.toString();
    }
}