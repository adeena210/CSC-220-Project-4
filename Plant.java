/* Adeena Ahmed
    CSC 220-03
    Project 3: Jungle Management System
*/
import java.util.*;

public class Plant extends Life{
	
	public Plant( int id, String name,  int age,  int lifespan){ //parameterized constructor to support superclass
		super(id,  name, age,  lifespan);
		
	}

	public void plantDescription(){ //print description
		System.out.println(id+": "+name+", plant, "+age+", "+lifespan);
	}
}