package ca.mcgill.ecse321.intercityridesharingsystem_android_passenger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.*;

public class searchJourneyActivityTest {

    final String drivernameT ="b";
    String t1;
    String t2;
    String t3;
    String t4;
    String t5;
    String t6;
    String IdNumbers = "";
    String IdNumbersFullJourneyList = "";

    List<journeyT> lista = new ArrayList<journeyT>(3);

    private String error = null;

    public class journeyT {
        private String time ;
        private String stop ;
        private String price ;
        private String vehicle ;
        private String seating ;
        private String driver;
        private String id;


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
        public String getId(){
            return this.id;
        }
        public void setInfos(String time,String stop,String price,String vehicle, String seating,String driver,String id){
            this.time=time;
            this.stop=stop;
            this.price=price;
            this.vehicle=vehicle;
            this.seating=seating;
            this.driver=driver;
            this.id=id;

        }


    }

    public void createJ(String a,String b,String c,String d,String e,String f,String g){
        journeyT j = new journeyT();
        j.setInfos(a,b,c,d,e,f,g);
//        t1= j.getTime();
//        t2= j.getStop();
//        t3= j.getPrice();
//        t4= j.getVehicle();
//        t5= j.getSeating();
//        t6= j.getDriver();

    }


    @Before
    public void setUp() throws Exception {
        journeyT j1 = new journeyT();
        j1.setInfos("12-Dec-2018-12:12:12","Montreal_Toronto","20","SUV","2","sk","001");
        journeyT j2 = new journeyT();
        j2.setInfos("12-Dec-2018-12:12:12","Montreal_Ottawa","20","SUV","2","sk","002");
        journeyT j3 = new journeyT();
        j3.setInfos("12-Dec-2018-12:12:12","Montreal_Toronto","20","SUV","2","sun","003");
        journeyT j4 = new journeyT();
        j4.setInfos("12-Dec-2018-12:12:12","Montreal_QuebecCity","20","SUV","2","sun","004");


        lista.add(j1);
        lista.add(j2);
        lista.add(j3);
        lista.add(j4);

        for(journeyT j : lista){
            String temp1=j.getStop();
            String stop = "Montreal_Toronto";
            if (temp1 == stop){
                IdNumbers += j.getId() ;
            }
        }

        for(journeyT j : lista){

            IdNumbersFullJourneyList += j.getId() ;

        }
    }



    @Test
    public void onCreate() {


        String checkName = "001003";
        assertEquals(IdNumbers, checkName);

        String fullJourneyList = "001002003004";
        assertEquals(IdNumbersFullJourneyList, fullJourneyList);
    }

    @After
    public void tearDown() throws Exception {
    }
}