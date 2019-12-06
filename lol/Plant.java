/* Adeena Ahmed, Lalima Bhola, Faith Christian
    CSC 220-03
    Project 4: Jungle Management System w/ added features
*/
import java.util.*;

public class Plant extends Life{
	
	public Plant( int id, String name,  int age,  int lifespan){ //parameterized constructor to support superclass
		super(id,  name, age,  lifespan);
		
	}

	public String print(){ 
		String line= id+": "+name+", plant, "+age+", "+lifespan;
		return line;
	}
	
	public Life eat(ArrayList<Life> lives, int foodCount){ 
		return null;
	}

  	public void setDaysSinceEaten( int daysSinceEaten){ 
  
	}
	
	public int getDaysSinceEaten( ){ 
    	return -1;
	}

}
