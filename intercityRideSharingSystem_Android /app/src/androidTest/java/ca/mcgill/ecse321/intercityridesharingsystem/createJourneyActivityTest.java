package ca.mcgill.ecse321.intercityridesharingsystem;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class createJourneyActivityTest {

        final String drivernameT ="b";
    String t1;
    String t2;
    String t3;
    String t4;
    String t5;
    String t6;

    private String error = null;

    public class journeyT {
        private String time ;
        private String stop ;
        private String price ;
        private String vehicle ;
        private String seating ;
        private String driver;
        final String drivernameT ="b";


        public String getTime(){
            return this.time;
        }
        public String getStop(){
            return this.stop;
        }
        public String getPrice(){
            return this.price;
        }
        public String getVehicle(){
            return this.vehicle;
        }
        public String getSeating(){
            return this.seating;
        }
        public String getDriver(){
            return this.driver;
        }
        public void setInfos(String time,String stop,String price,String vehicle, String seating,String driver){
            this.time=time;
            this.stop=stop;
            this.price=price;
            this.vehicle=vehicle;
            this.seating=seating;
            this.driver=driver;

        }


    }

    public void createJ(String a,String b,String c,String d,String e,String f){
        journeyT j = new journeyT();
        j.setInfos(a,b,c,d,e,f);
        t1= j.getTime();
        t2= j.getStop();
        t3= j.getPrice();
        t4= j.getVehicle();
        t5= j.getSeating();
        t6= j.getDriver();

    }

    @Rule
    public ActivityTestRule<createJourneyActivity> mActivityRule =
            new ActivityTestRule<>(createJourneyActivity.class);
    private createJourneyActivity mActivity = null;


    @Before
    public void setUp() throws Exception {

        mActivity = mActivityRule.getActivity();
    }


    @Test
    public void onCreate() {
        createJ("12-Dec-2018-12:12:12","Montreal","20","SUV","2","sk");
        //onView(withId(R.id.buttonAdvertise)).perform(click());
        assertEquals(t1, "12-Dec-2018-12:12:12");
        assertEquals(t2, "Montreal");
        assertEquals(t3, "20");
        assertEquals(t4, "SUV");
        assertEquals(t5, "2");
        assertEquals(t6, "sk");

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;

    }
}