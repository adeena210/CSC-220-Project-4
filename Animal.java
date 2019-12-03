/* Adeena Ahmed
    CSC 220-03
    Project 3: Jungle Management System
*/
import java.util.*;

public class Animal extends Life{
	private int daysSinceEaten;

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

	public int eat(ArrayList<Life> lives, int plantCount){ //method to eat, return index of plant eaten
		Random rnd= new Random();
		if(plantCount!=0){	
    		int count=0;
    		int plantNum=rnd.nextInt(plantCount)+1; //randomly selects a plant
   
    	for(int i =0; i<lives.size();i++){
    		Life l=lives.get(i);
    		if(l instanceof Plant){
    			count++;
    		}
    		if(count==plantNum){
    			daysSinceEaten=0;
    			return i;
    			}
    		}
    	}
			
    	return -1;
		
	}

	public void animalDescription(){ //print description
		System.out.println(id+": "+name+", animal, "+age+", "+lifespan);
	}


}

			
