/* Adeena Ahmed, Lalima Bhola, Faith Christian
    CSC 220-03
    Project 4: Jungle Management System w/ added features
*/
import java.util.*;

public class Carnivore extends Animal{

    public Carnivore ( int id, String name, int age,  int lifespan){ //parameterized constructor to support superclass
	   super(id, name,  age, lifespan);
		
	}
  
    public Life eat(ArrayList<Life> lives, int herbivoreCount){ 
        Random rnd= new Random();
		if(herbivoreCount!=0){	
            int count=0;
            int herbivoreNum=rnd.nextInt(herbivoreCount)+1;
            for(int i =0; i<lives.size();i++){
    		  Life l=lives.get(i);
    		  if(l instanceof Herbivore){
    			count++;
    		  }
    		  if(count==herbivoreNum){
    			daysSinceEaten=0;
    			return l;
    			}
    		}
    	}
			
    	return null;
		
	}
  
		
	public String print(){ 
		String line=id+": "+name+", carnivore, "+age+", "+daysSinceEaten+", "+lifespan;
		return line;
	}
  }
