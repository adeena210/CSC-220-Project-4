/* Adeena Ahmed
    CSC 220-03
    Project 3: Jungle Management System
*/
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Jungle{
	
	HashMap<String, float> properties= new HashMap<String, float>():
	private int capacity;
	private String status;
	private ArrayList<Life> lives;
	private int dayNumber;
	private String deathLog; 
	private String eatingLog;
	private int herbivoreCount;
	private int carnivoreCount;
	private int plantCount;

	public Jungle(){ //default constructor
		try{
			File input = new File(filename);
			sc= new Scanner(input);
			
			
		
		 	try{
				properties.put("capacity", sc.nextInt());
				properties.put("herbivoreEatingProbability",sc.nextInt());
				properties.put("carnivoreEatingProbability",sc.nextInt());
				properties.put("herbivoreStarvationMax",sc.nextInt());
				properties.put("carnivoreStarvationMax",sc.nextInt());
				properties.put("plantBirthingInverval",sc.nextInt());
				properties.put("herbivoreBirthingInverval",sc.nextInt());
				properties.put("carnivoreBirthingInverval",sc.nextInt());
				
				
				while(sc.hasNextLine()){
					String name= sc.next();
					String type=sc.next();
					int count= sc.nextInt();
					int lifespan= sc.nextInt();
					if(type.equals("herbivore")){
						for(int i=0;i<count;i++){
						Life l= new Herbivore(createId(), name, 1, lifespan);
						lives.add(l);
						}
					}
					else 
						if(type.equals("carnivore")){
						for(int i=0;i<count;i++){
						Life l= new Carnivore(createId(), name, 1, lifespan);
						lives.add(l);
						}
					}
					else
						if(type.equals("plant")){
						for(int i=0;i<count;i++){
						Life l= new Plant(createId(), name, 1, lifespan);
						lives.add(l);
						}
							
						}
						
					
				
			}
			catch (Exception excpt){
				System.out.println("Invalid Input");
		    
			}
		}
		
		}
	
		catch (FileNotFoundException excpt) {
			System.out.println("File not found");
		}
			

		finally{
			if(sc!=null)
				sc.close();
		}


		
		status= "unstable";
		lives= new ArrayList<Life>();
		dayNumber=0;
		deathLog="";
		eatingLog="";
		plantCount=0;
		herbivoreCount=0;
		carnivoreCount=0;
		
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
		
		for (int i=0; i<lives.size(); i++){ 
			if(lives.get(i) instanceof Herbivore){
				Random rnd = new Random();
				int probability=rnd.nextInt(properties.get("herbivoreEatingProbability"));
				if(probability<=(properties.get("herbivoreEatingProbability")*10) && plantCount!=0){ 
					Life l = ((Herbivore)lives.get(i)).eat(lives, plantCount);
        			if(l!=null){
         		 		plantCount--;
					eatingLog=eatingLog+lives.get(i).getId()+" ate "+lives.get(j).getId()+", ";
					deathLog=deathLog+lives.get(j).getId()+"(food), ";	
          				lives.remove(l);
          				if(j<i)
          					i--;
					}
					lives.remove(l);
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












