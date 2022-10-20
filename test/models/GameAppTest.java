package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;                      //this test was refactored from EducationAppTest

public class GameAppTest {

    private GameApp gaAppBelowBoundary, gaAppOnBoundary, gaAppAboveBoundary, gaAppInvalidData;
    private Developer developerLego = new Developer("Lego", "www.lego.com");
    private Developer developerSphero = new Developer("Sphero", "www.sphero.com");



    @BeforeEach
    void setUp() {
        gaAppBelowBoundary = new GameApp(developerLego, "WeDo", 1, 1.0, 0, true);
        gaAppOnBoundary = new GameApp(developerLego, "Spike", 1000, 2.0, 1.99, true);
        gaAppAboveBoundary = new GameApp(developerLego, "EV3", 1001, 3.5, 2.99, false);
        gaAppInvalidData = new GameApp(developerLego, "", -1, 0, -1.00,false);
    }

    @AfterEach
    void tearDown() {
        gaAppBelowBoundary = gaAppOnBoundary = gaAppAboveBoundary = gaAppInvalidData = null;
        developerLego = developerSphero = null;
    }

    @Nested
    class Getters {

        @Test
        void getDeveloper() {
            assertEquals(developerLego, gaAppBelowBoundary.getDeveloper());
            assertEquals(developerLego, gaAppOnBoundary.getDeveloper());
            assertEquals(developerLego, gaAppAboveBoundary.getDeveloper());
            assertEquals(developerLego, gaAppInvalidData.getDeveloper());
        }

        @Test
        void getAppName() {
            assertEquals("WeDo", gaAppBelowBoundary.getAppName());
            assertEquals("Spike", gaAppOnBoundary.getAppName());
            assertEquals("EV3", gaAppAboveBoundary.getAppName());
            assertEquals("", gaAppInvalidData.getAppName());
        }

        @Test
        void getAppSize() {
            assertEquals(1, gaAppBelowBoundary.getAppSize());
            assertEquals(1000, gaAppOnBoundary.getAppSize());
            assertEquals(0, gaAppAboveBoundary.getAppSize());
            assertEquals(0, gaAppInvalidData.getAppSize());
        }

        @Test
        void getAppVersion() {
            assertEquals(1.0, gaAppBelowBoundary.getAppVersion());
            assertEquals(2.0, gaAppOnBoundary.getAppVersion());
            assertEquals(3.5, gaAppAboveBoundary.getAppVersion());
            assertEquals(1.0, gaAppInvalidData.getAppVersion());
        }

        @Test
        void getAppCost() {
            assertEquals(0, gaAppBelowBoundary.getAppCost());
            assertEquals(1.99, gaAppOnBoundary.getAppCost());
            assertEquals(2.99, gaAppAboveBoundary.getAppCost());
            assertEquals(0, gaAppInvalidData.getAppCost());
        }

        @Test
        void isMultiplayer() {
            assertEquals(true, gaAppBelowBoundary.isMultiplayer());
            assertEquals(true, gaAppOnBoundary.isMultiplayer());
            assertEquals(false, gaAppAboveBoundary.isMultiplayer());
            assertEquals(false, gaAppInvalidData.isMultiplayer());
        }

    }

    @Nested
    class Setters {

        @Test
        void setDeveloper() {
            //no validation in models
            assertEquals(developerLego, gaAppBelowBoundary.getDeveloper());
            gaAppBelowBoundary.setDeveloper(developerSphero);
            assertEquals(developerSphero, gaAppBelowBoundary.getDeveloper());
        }

        @Test
        void setAppName() {
            //no validation in models
            assertEquals("WeDo", gaAppBelowBoundary.getAppName());
            gaAppBelowBoundary.setAppName("Mindstorms");
            assertEquals("Mindstorms", gaAppBelowBoundary.getAppName());
        }

        @Test
        void setAppSize() {
            //Validation: appSize(1-1000)
            assertEquals(1, gaAppBelowBoundary.getAppSize());

            gaAppBelowBoundary.setAppSize(1000);
            assertEquals(1000, gaAppBelowBoundary.getAppSize()); //update

            gaAppBelowBoundary.setAppSize(1001);
            assertEquals(1000, gaAppBelowBoundary.getAppSize()); //no update

            gaAppBelowBoundary.setAppSize(2);
            assertEquals(2, gaAppBelowBoundary.getAppSize()); //update

            gaAppBelowBoundary.setAppSize(0);
            assertEquals(2, gaAppBelowBoundary.getAppSize()); //no update
        }

        @Test
        void setAppVersion() {
            //Validation: appVersion(>=1.0)
            assertEquals(1.0, gaAppBelowBoundary.getAppVersion());

            gaAppBelowBoundary.setAppVersion(2.0);
            assertEquals(2.0, gaAppBelowBoundary.getAppVersion()); //update

            gaAppBelowBoundary.setAppVersion(0.0);
            assertEquals(2.0, gaAppBelowBoundary.getAppVersion()); //no update

            gaAppBelowBoundary.setAppVersion(1.0);
            assertEquals(1.0, gaAppBelowBoundary.getAppVersion()); //update
        }

        @Test
        void setAppCost() {
            //Validation: appCost(>=0)
            assertEquals(0.0, gaAppBelowBoundary.getAppCost());

            gaAppBelowBoundary.setAppCost(1.0);
            assertEquals(1.0, gaAppBelowBoundary.getAppCost());

            gaAppBelowBoundary.setAppCost(-1);
            assertEquals(1.0, gaAppBelowBoundary.getAppCost());

            gaAppBelowBoundary.setAppCost(0.0);
            assertEquals(0.0, gaAppBelowBoundary.getAppCost());
        }

        @Test
        void setLevel() {

            assertEquals(true, gaAppBelowBoundary.isMultiplayer());

            gaAppBelowBoundary.setMultiplayer(true);
            assertEquals(true, gaAppBelowBoundary.isMultiplayer());

            gaAppBelowBoundary.setMultiplayer(false);
            assertEquals(false, gaAppBelowBoundary.isMultiplayer());

            gaAppBelowBoundary.setMultiplayer(true);
            assertEquals(true, gaAppBelowBoundary.isMultiplayer());
        }

    }

    @Nested
    class ObjectStateMethods {

        @Test
        void appSummaryReturnsCorrectString() {
            GameApp gaApp = setupGameAppWithRating(3, 4);
            String stringContents = gaApp.appSummary();

            assertTrue(stringContents.contains("multiplayer " + gaApp.isMultiplayer()));
            assertTrue(stringContents.contains(gaApp.getAppName() + "(V" + gaApp.getAppVersion()));
            assertTrue(stringContents.contains(gaApp.getDeveloper().toString()));
            assertTrue(stringContents.contains("€" + gaApp.getAppCost()));
            assertTrue(stringContents.contains("Rating: " + gaApp.calculateRating()));
        }

        @Test
        void toStringReturnsCorrectString() {
            GameApp gaApp = setupGameAppWithRating(3, 4);
            String stringContents = gaApp.toString();

            assertTrue(stringContents.contains(gaAppOnBoundary.getAppName()));
            assertTrue(stringContents.contains("(Version " + gaAppOnBoundary.getAppVersion()));
            assertTrue(stringContents.contains(gaAppOnBoundary.getDeveloper().toString()));
            assertTrue(stringContents.contains(gaAppOnBoundary.getAppSize() + "MB"));
            assertTrue(stringContents.contains("Cost: " + gaAppOnBoundary.getAppCost()));
            assertTrue(stringContents.contains("Level: " + gaAppOnBoundary.isMultiplayer()));
            assertTrue(stringContents.contains("Ratings (" + gaAppOnBoundary.calculateRating()));

            //contains list of ratings too
            assertTrue(stringContents.contains("John Doe"));
            assertTrue(stringContents.contains("Very Good"));
            assertTrue(stringContents.contains("Jane Doe"));
            assertTrue(stringContents.contains("Excellent"));
        }

    }

    @Nested
    class RecommendedApp {

        @Test
        void appIsNotRecommendedWhenInAppCostIs99c() {
            //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
            GameApp gaApp = setupGameAppWithRating(4, 4);

            //now setting appCost to 0.99 so app should not be recommended now
            gaApp.setAppCost(0.99);
            assertFalse(gaApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenRatingIsLessThan3AndAHalf() {
            //setting all conditions to true with ratings of 3 and 3 (i.e. 3.0)
            GameApp gaApp = setupGameAppWithRating(3, 3);
            //verifying recommended app returns false (rating not high enough
            assertFalse(gaApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenNoRatingsExist() {
            //setting all conditions to true with no ratings
            GameApp gaApp = new GameApp(developerLego, "WeDo", 1,
                    1.0, 1.00, true);

            assertFalse(gaApp.isRecommendedApp());
        }

        @Test
        void appIsRecommendedWhenAllOfTheConditionsAreTrue() {

            GameApp gaApp = setupGameAppWithRating(4,4);


            assertTrue(gaApp.isRecommendedApp());
        }

    }

    GameApp setupGameAppWithRating(int rating1, int rating2) {

        GameApp gaApp = new GameApp(developerLego, "WeDo", 1,
                1.0, 1.00,true);
        gaApp.addRating(new Rating(rating1, "John Doe", "Very Good"));
        gaApp.addRating(new Rating(rating2, "Jane Doe", "Excellent"));


        assertEquals(2, gaApp.getRatings().size());
        assertEquals(1.0, gaApp.getAppCost(), 0.01);
        assertEquals(((rating1 + rating2) / 2.0), gaApp.calculateRating(), 0.01);


        return gaApp;
    }

}