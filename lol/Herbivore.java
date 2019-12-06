/* Adeena Ahmed, Lalima Bhola, Faith Christian
    CSC 220-03
    Project 4: Jungle Management System w/ added features
*/
import java.util.*;

public class Herbivore extends Animal{

    public Herbivore( int id, String name, int age,  int lifespan){ //parameterized constructor to support superclass
		super(id, name,  age, lifespan);
		
	}
  
    public Life eat(ArrayList<Life> lives, int plantCount){ 
        Random rnd= new Random();
		if(plantCount!=0){	
    		int count=0;
    		int plantNum=rnd.nextInt(plantCount)+1; 
   
    	   for(int i =0; i<lives.size();i++){
    		  Life l=lives.get(i);
    		  if(l instanceof Plant){
    			count++;
    		
    		  if(count==plantNum){
    			daysSinceEaten=0;
    			return l;
    			}
            }
    		}
    	}
			
    	return null;
		
	}

	
	public String print(){ 
		String line=id+": "+name+", herbivore, "+age+", "+daysSinceEaten+", "+lifespan;
		return line;
	}

  
  }
