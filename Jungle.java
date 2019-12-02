/* Adeena Ahmed
    CSC 220-03
    Project 3: Jungle Management System
*/
import java.util.*;

public class Jungle{
	private int capacity;
	private String status;
	private ArrayList<Life> lives;
	private int dayNumber;
	private String deathLog; 
	private String eatingLog;
	private int animalCount;
	private int plantCount;

	public Jungle(){ //default constructor
		capacity=0;
		status= "unstable";
		lives= new ArrayList<Life>();
		dayNumber=0;
		deathLog="";
		eatingLog="";
		plantCount=0;
		animalCount=0;
	}

	public void setCapacity(int capacity){  //setter for capacity
    	this.capacity=capacity;
	}
	public String getStatus(){ //getter for status
    	return status;
	}

	public int createId(){ //to create id
		return lives.size()+1;
	}

	public void addLife(Life l){ // to add life
  	if(l instanceof Plant)	
  		plantCount++;
  	else
  		animalCount++;

	lives.add(l);
	if(lives.size()>=capacity){
		status="unstable";
		
		}
	else
		status="stable";
	}
  

	public void runADay(){ // to run a day
		deathLog="";
		eatingLog="";
		dayNumber++;
		for (int i=0; i<lives.size(); i++){ //aging
			lives.get(i).increaseAge();
			if (lives.get(i).getAge()==lives.get(i).getLifespan()){
				if (lives.get(i) instanceof Plant)
					plantCount--;
				else
					animalCount--;
		
			deathLog = deathLog + lives.get(i).getId() + "(overage), ";
			lives.remove(i);
           	if(i>0)
           		i--; 
				 
			}

			if (lives.get(i) instanceof Animal){ //eating
				Random rnd = new Random();
				int probability=rnd.nextInt(2);
				if(probability==1 && plantCount!=0){ 
					int j = ((Animal)lives.get(i)).eat(lives, plantCount);
        			if(j!=-1){
         		 		plantCount--;
						eatingLog=eatingLog+lives.get(i).getId()+" ate "+lives.get(j).getId()+", ";
						deathLog=deathLog+lives.get(j).getId()+"(food), ";	
          				lives.remove(j);
          				if(j<i)
          					i--;
					}
      			}

				else{
					((Animal)lives.get(i)).setDaysSinceEaten(((Animal)lives.get(i)).getDaysSinceEaten()+1);
					if (((Animal)lives.get(i)).getDaysSinceEaten()==3){
						deathLog=deathLog+lives.get(i).getId()+"(starvation), ";
          				animalCount--;
				 		lives.remove(i);
          				 i--;
					}
				}
			}

		}
    

		if(animalCount==0 || plantCount==0)
			status="unstable";


		}

	public void oneDayStat(){ //prints out day's events
	    System.out.println();
	    System.out.println();
	 	System.out.println("Day: "+dayNumber+" Capacity: "+capacity);
	 	System.out.println("Eating today: "+ eatingLog);
	 	System.out.println("Death: "+ deathLog);
	 	System.out.println("Status: "+status);
	 	System.out.println();
	 	System.out.println("Remaining lives: ");
    
		for (int i=0; i<lives.size(); i++) 
		{
			if (lives.get(i) instanceof Plant)
				((Plant)lives.get(i)).plantDescription();
			else
				((Animal)lives.get(i)).animalDescription();

		}
	}
}












