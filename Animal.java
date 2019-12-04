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

	public Life eat(ArrayList<Life> lives, int foodCount){ //method to eat, return index of plant eaten
		Random rnd= new Random();
		if(foodCount!=0){	
    		int count=0;
    		int foodNum=rnd.nextInt(foodCount)+1; //randomly selects a plant
   
    	for(int i =0; i<lives.size();i++){
    		Life l=lives.get(i);
    		if(l instanceof Animal==false){
    			count++;
    		}
    		if(count==foodNum){
    			daysSinceEaten=0;
    			return l;
    			}
    		}
    	}
			
    	return null;
		
	}


}

			
