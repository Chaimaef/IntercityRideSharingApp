/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse321.intercityRideSharing.model;
import java.sql.Date;
import java.util.*;

// line 27 "../../../../model.ump"
public class Journey
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Journey Attributes
  private Date date;
  private int price;
  private boolean flag;
  private String destination;
  private String departure;

  //Journey Associations
  private List<Stop> stops;
  private List<Passenger> passengers;
  private List<Driver> drivers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Journey(Date aDate, int aPrice, boolean aFlag, String aDestination, String aDeparture)
  {
    date = aDate;
    price = aPrice;
    flag = aFlag;
    destination = aDestination;
    departure = aDeparture;
    stops = new ArrayList<Stop>();
    passengers = new ArrayList<Passenger>();
    drivers = new ArrayList<Driver>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setFlag(boolean aFlag)
  {
    boolean wasSet = false;
    flag = aFlag;
    wasSet = true;
    return wasSet;
  }

  public boolean setDestination(String aDestination)
  {
    boolean wasSet = false;
    destination = aDestination;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeparture(String aDeparture)
  {
    boolean wasSet = false;
    departure = aDeparture;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public int getPrice()
  {
    return price;
  }

  public boolean getFlag()
  {
    return flag;
  }

  public String getDestination()
  {
    return destination;
  }

  public String getDeparture()
  {
    return departure;
  }

  public Stop getStop(int index)
  {
    Stop aStop = stops.get(index);
    return aStop;
  }

  public List<Stop> getStops()
  {
    List<Stop> newStops = Collections.unmodifiableList(stops);
    return newStops;
  }

  public int numberOfStops()
  {
    int number = stops.size();
    return number;
  }

  public boolean hasStops()
  {
    boolean has = stops.size() > 0;
    return has;
  }

  public int indexOfStop(Stop aStop)
  {
    int index = stops.indexOf(aStop);
    return index;
  }

  public Passenger getPassenger(int index)
  {
    Passenger aPassenger = passengers.get(index);
    return aPassenger;
  }

  public List<Passenger> getPassengers()
  {
    List<Passenger> newPassengers = Collections.unmodifiableList(passengers);
    return newPassengers;
  }

  public int numberOfPassengers()
  {
    int number = passengers.size();
    return number;
  }

  public boolean hasPassengers()
  {
    boolean has = passengers.size() > 0;
    return has;
  }

  public int indexOfPassenger(Passenger aPassenger)
  {
    int index = passengers.indexOf(aPassenger);
    return index;
  }

  public Driver getDriver(int index)
  {
    Driver aDriver = drivers.get(index);
    return aDriver;
  }

  public List<Driver> getDrivers()
  {
    List<Driver> newDrivers = Collections.unmodifiableList(drivers);
    return newDrivers;
  }

  public int numberOfDrivers()
  {
    int number = drivers.size();
    return number;
  }

  public boolean hasDrivers()
  {
    boolean has = drivers.size() > 0;
    return has;
  }

  public int indexOfDriver(Driver aDriver)
  {
    int index = drivers.indexOf(aDriver);
    return index;
  }

  public static int minimumNumberOfStops()
  {
    return 0;
  }

  public boolean addStop(Stop aStop)
  {
    boolean wasAdded = false;
    if (stops.contains(aStop)) { return false; }
    stops.add(aStop);
    if (aStop.indexOfJourney(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aStop.addJourney(this);
      if (!wasAdded)
      {
        stops.remove(aStop);
      }
    }
    return wasAdded;
  }

  public boolean removeStop(Stop aStop)
  {
    boolean wasRemoved = false;
    if (!stops.contains(aStop))
    {
      return wasRemoved;
    }

    int oldIndex = stops.indexOf(aStop);
    stops.remove(oldIndex);
    if (aStop.indexOfJourney(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aStop.removeJourney(this);
      if (!wasRemoved)
      {
        stops.add(oldIndex,aStop);
      }
    }
    return wasRemoved;
  }

  public boolean addStopAt(Stop aStop, int index)
  {  
    boolean wasAdded = false;
    if(addStop(aStop))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStops()) { index = numberOfStops() - 1; }
      stops.remove(aStop);
      stops.add(index, aStop);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStopAt(Stop aStop, int index)
  {
    boolean wasAdded = false;
    if(stops.contains(aStop))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStops()) { index = numberOfStops() - 1; }
      stops.remove(aStop);
      stops.add(index, aStop);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStopAt(aStop, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPassengers()
  {
    return 0;
  }

  public boolean addPassenger(Passenger aPassenger)
  {
    boolean wasAdded = false;
    if (passengers.contains(aPassenger)) { return false; }
    passengers.add(aPassenger);
    if (aPassenger.indexOfJourney(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPassenger.addJourney(this);
      if (!wasAdded)
      {
        passengers.remove(aPassenger);
      }
    }
    return wasAdded;
  }

  public boolean removePassenger(Passenger aPassenger)
  {
    boolean wasRemoved = false;
    if (!passengers.contains(aPassenger))
    {
      return wasRemoved;
    }

    int oldIndex = passengers.indexOf(aPassenger);
    passengers.remove(oldIndex);
    if (aPassenger.indexOfJourney(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPassenger.removeJourney(this);
      if (!wasRemoved)
      {
        passengers.add(oldIndex,aPassenger);
      }
    }
    return wasRemoved;
  }

  public boolean addPassengerAt(Passenger aPassenger, int index)
  {  
    boolean wasAdded = false;
    if(addPassenger(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePassengerAt(Passenger aPassenger, int index)
  {
    boolean wasAdded = false;
    if(passengers.contains(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPassengerAt(aPassenger, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfDrivers()
  {
    return 0;
  }

  public boolean addDriver(Driver aDriver)
  {
    boolean wasAdded = false;
    if (drivers.contains(aDriver)) { return false; }
    drivers.add(aDriver);
    if (aDriver.indexOfJourney(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aDriver.addJourney(this);
      if (!wasAdded)
      {
        drivers.remove(aDriver);
      }
    }
    return wasAdded;
  }

  public boolean removeDriver(Driver aDriver)
  {
    boolean wasRemoved = false;
    if (!drivers.contains(aDriver))
    {
      return wasRemoved;
    }

    int oldIndex = drivers.indexOf(aDriver);
    drivers.remove(oldIndex);
    if (aDriver.indexOfJourney(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aDriver.removeJourney(this);
      if (!wasRemoved)
      {
        drivers.add(oldIndex,aDriver);
      }
    }
    return wasRemoved;
  }

  public boolean addDriverAt(Driver aDriver, int index)
  {  
    boolean wasAdded = false;
    if(addDriver(aDriver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDrivers()) { index = numberOfDrivers() - 1; }
      drivers.remove(aDriver);
      drivers.add(index, aDriver);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDriverAt(Driver aDriver, int index)
  {
    boolean wasAdded = false;
    if(drivers.contains(aDriver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDrivers()) { index = numberOfDrivers() - 1; }
      drivers.remove(aDriver);
      drivers.add(index, aDriver);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDriverAt(aDriver, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Stop> copyOfStops = new ArrayList<Stop>(stops);
    stops.clear();
    for(Stop aStop : copyOfStops)
    {
      aStop.removeJourney(this);
    }
    ArrayList<Passenger> copyOfPassengers = new ArrayList<Passenger>(passengers);
    passengers.clear();
    for(Passenger aPassenger : copyOfPassengers)
    {
      aPassenger.removeJourney(this);
    }
    ArrayList<Driver> copyOfDrivers = new ArrayList<Driver>(drivers);
    drivers.clear();
    for(Driver aDriver : copyOfDrivers)
    {
      aDriver.removeJourney(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "," +
            "flag" + ":" + getFlag()+ "," +
            "destination" + ":" + getDestination()+ "," +
            "departure" + ":" + getDeparture()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}