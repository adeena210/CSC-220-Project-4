/* Adeena Ahmed, Lalima Bhola, Faith Christian
    CSC 220-03
    Project 4: Jungle Management System w/ added features
*/
import java.util.*;

public abstract class Animal extends Life{
	protected int daysSinceEaten;

	public Animal( int id, String name, int age,  int lifespan){ //parameterized constructor to support superclass
		super(id, name,  age, lifespan);
		daysSinceEaten=0;
	}

	public void setDaysSinceEaten( int daysSinceEaten){ //setter for daysSinceEaten
		this.daysSinceEaten=daysSinceEaten;
	}
	
	public int getDaysSinceEaten( ){ //getter for daysSinceEaten
		return daysSinceEaten;
	}

	public abstract Life eat(ArrayList<Life> lives, int foodCount);

	public abstract String print();

}