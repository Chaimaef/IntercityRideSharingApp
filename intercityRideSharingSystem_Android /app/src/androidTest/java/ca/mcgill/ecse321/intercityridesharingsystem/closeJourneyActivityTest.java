package ca.mcgill.ecse321.intercityridesharingsystem;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.*;

public class closeJourneyActivityTest {
    final String drivernameT ="b";
    String t1;
    String t2;
    String t3;
    String t4;
    String t5;
    String t6;
    Boolean t7;

    private String error = null;

    public class journeyT {
        private String time ;
        private String stop ;
        private String price ;
        private String vehicle ;
        private String seating ;
        private String driver;
        private Boolean active;


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
        public Boolean getActive(){
            return this.active;
        }
        public void setInfos(String time,String stop,String price,String vehicle, String seating,String driver, Boolean active){
            this.time=time;
            this.stop=stop;
            this.price=price;
            this.vehicle=vehicle;
            this.seating=seating;
            this.driver=driver;
            this.active=active;

        }


    }
    public void createJ(String a,String b,String c,String d,String e,String f,Boolean g){
        journeyT j = new journeyT();
        j.setInfos(a,b,c,d,e,f,g);
        t1= j.getTime();
        t2= j.getStop();
        t3= j.getPrice();
        t4= j.getVehicle();
        t5= j.getSeating();
        t6= j.getDriver();
        t7 = j.getActive();

    }

    public void closeJ(String a,String b,String c,String d,String e,String f, Boolean g){
        journeyT j = new journeyT();
        j.setInfos(a,b,c,d,e,f,g);
        t7= j.getActive();

    }

    @Rule
    public ActivityTestRule< closeJourneyActivity> mActivityRule =
            new ActivityTestRule<>(closeJourneyActivity.class);
    private  closeJourneyActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        createJ("12-Dec-2018-12:12:12","Montreal","20","SUV","2","sk",true);
    }


    @Test
    public void onCreate() {
        closeJ("12-Dec-2018-12:12:12","Montreal","20","SUV","2","sk",false);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}