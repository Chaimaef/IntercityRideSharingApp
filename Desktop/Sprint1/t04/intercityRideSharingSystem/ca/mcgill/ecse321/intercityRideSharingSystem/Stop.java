/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse321.intercityRideSharingSystem;
import java.util.*;

// line 23 "../../../../model.ump"
public class Stop
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stop Associations
  private List<Journey> journeies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Stop()
  {
    journeies = new ArrayList<Journey>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Journey getJourney(int index)
  {
    Journey aJourney = journeies.get(index);
    return aJourney;
  }

  public List<Journey> getJourneies()
  {
    List<Journey> newJourneies = Collections.unmodifiableList(journeies);
    return newJourneies;
  }

  public int numberOfJourneies()
  {
    int number = journeies.size();
    return number;
  }

  public boolean hasJourneies()
  {
    boolean has = journeies.size() > 0;
    return has;
  }

  public int indexOfJourney(Journey aJourney)
  {
    int index = journeies.indexOf(aJourney);
    return index;
  }

  public static int minimumNumberOfJourneies()
  {
    return 0;
  }

  public boolean addJourney(Journey aJourney)
  {
    boolean wasAdded = false;
    if (journeies.contains(aJourney)) { return false; }
    journeies.add(aJourney);
    if (aJourney.indexOfStop(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aJourney.addStop(this);
      if (!wasAdded)
      {
        journeies.remove(aJourney);
      }
    }
    return wasAdded;
  }

  public boolean removeJourney(Journey aJourney)
  {
    boolean wasRemoved = false;
    if (!journeies.contains(aJourney))
    {
      return wasRemoved;
    }

    int oldIndex = journeies.indexOf(aJourney);
    journeies.remove(oldIndex);
    if (aJourney.indexOfStop(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aJourney.removeStop(this);
      if (!wasRemoved)
      {
        journeies.add(oldIndex,aJourney);
      }
    }
    return wasRemoved;
  }

  public boolean addJourneyAt(Journey aJourney, int index)
  {  
    boolean wasAdded = false;
    if(addJourney(aJourney))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJourneies()) { index = numberOfJourneies() - 1; }
      journeies.remove(aJourney);
      journeies.add(index, aJourney);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJourneyAt(Journey aJourney, int index)
  {
    boolean wasAdded = false;
    if(journeies.contains(aJourney))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJourneies()) { index = numberOfJourneies() - 1; }
      journeies.remove(aJourney);
      journeies.add(index, aJourney);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJourneyAt(aJourney, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Journey> copyOfJourneies = new ArrayList<Journey>(journeies);
    journeies.clear();
    for(Journey aJourney : copyOfJourneies)
    {
      aJourney.removeStop(this);
    }
  }

}