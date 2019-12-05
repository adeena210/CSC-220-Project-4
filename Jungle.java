import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Jungle{
	
	HashMap<String, Float> properties = new HashMap<>();
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
		 Scanner sc = null;
		 status = "stable";
		 lives = new ArrayList<Life>();
		try{
			File input = new File("input.txt");
			sc = new Scanner(input);
		
		 	try{
                sc.next();
                sc.next();
				properties.put("capacity", sc.nextFloat());
				sc.next();
				sc.next();
				properties.put("herbivoreEatingProbability",sc.nextFloat());
				sc.next();
				sc.next();
				properties.put("carnivoreEatingProbability",sc.nextFloat());
				sc.next();
				sc.next();
				properties.put("herbivoreStarvationMax",sc.nextFloat());
				sc.next();
				sc.next();
				properties.put("carnivoreStarvationMax",sc.nextFloat());
				sc.next();
				sc.next();
				properties.put("plantBirthingInverval",sc.nextFloat());
				sc.next();
				sc.next();
				properties.put("herbivoreBirthingInverval",sc.nextFloat());
				sc.next();
				sc.next();
				properties.put("carnivoreBirthingInverval",sc.nextFloat());
				
				
				while(sc.hasNextLine()){
					String name = sc.next();
					String type = sc.next();
					int count = sc.nextInt();
					int lifespan = sc.nextInt();
					if(type.equals("herbivore")){
						for(int i = 0; i < count; i++){
						Life l = new Herbivore(createId(), name, 1, lifespan);
						lives.add(l);
						}
					}
					else 
						if(type.equals("carnivore")){
						for(int i = 0; i < count; i++){
						Life l = new Carnivore(createId(), name, 1, lifespan);
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
					if (lives.size()==properties.get("capacity"))
						 status="unstable";

					
			}}
			catch (InputMismatchException excpt){
				System.out.println("Invalid Input");
			}
		}
		catch (FileNotFoundException excpt) {
			System.out.println("File not found");
		}
	

		finally{
			if(sc!=null)
				sc.close();
		}

		for (String key: properties.keySet()){
            
            String value = properties.get(key).toString();  
            System.out.println(key + "...." + value);  
} 
    System.out.println("size "+lives.size());

	

		
		
		dayNumber=0;
		deathLog="";
		eatingLog="";
		plantCount=0;
		herbivoreCount=0;
		carnivoreCount=0;
		System.out.print("Status:"+status);
		
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
	   if(l instanceof Herbivore) 
  		herbivoreCount++;
		else
			carnivoreCount++;

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
				int probability=rnd.nextInt(10)+1;
				if(probability<=(properties.get("herbivoreEatingProbability")*10) && plantCount!=0){ 
					Life l = ((Herbivore)lives.get(i)).eat(lives, plantCount);
        			if(l!=null){
         		 		plantCount--;
					eatingLog=eatingLog+lives.get(i).getId()+" ate "+l.getId()+", ";
					deathLog=deathLog+l.getId()+"(food), ";	
          				
          				if(lives.indexOf(l)<i)
          					i--;
					}
					lives.remove(l);
      			}

				else{
					((Herbivore)lives.get(i)).setDaysSinceEaten(((Herbivore)lives.get(i)).getDaysSinceEaten()+1);
					if (((Herbivore)lives.get(i)).getDaysSinceEaten()==properties.get("herbivoreStarvationMax")){
						deathLog=deathLog+lives.get(i).getId()+"(starvation), ";
          				herbivoreCount--;
				 		lives.remove(i);
          				 i--;
					}
				}
			}
		}
					  
		for (int i=0; i<lives.size(); i++){ 
			if(lives.get(i) instanceof Carnivore){
				Random rnd = new Random();
				int probability=rnd.nextInt(10)+1;
				if(probability<=(properties.get("carnivoreEatingProbability")*10) && herbivoreCount!=0){ 
					Life l = ((Carnivore)lives.get(i)).eat(lives, herbivoreCount);
        			if(l!=null){
         		 		herbivoreCount--;
					eatingLog=eatingLog+lives.get(i).getId()+" ate "+l.getId()+", ";
					deathLog=deathLog+l.getId()+"(food), ";	
          				
          				if(lives.indexOf(l)<i)
          					i--;
					}
					lives.remove(l);
      			}

				else{
					((Carnivore)lives.get(i)).setDaysSinceEaten(((Carnivore)lives.get(i)).getDaysSinceEaten()+1);
					if (((Carnivore)lives.get(i)).getDaysSinceEaten()==properties.get("carnivoreStarvationMax")){
						deathLog=deathLog+lives.get(i).getId()+"(starvation), ";
          				carnivoreCount--;
				 		lives.remove(i);
          				 i--;
					}
				}
			}
		}
					  
                   for (int i=0;i<lives.size();i++){ // birth
			   if(lives.get(i) instanceof Herbivore){
				   if (lives.get(i).getAge()%properties.get("herbivoreBirthingInverval")==0){
					   Life l = new Herbivore(createId(), lives.get(i).getName(), 1, lives.get(i).getLifespan());
					   lives.add(l);
				   }
			   }
			   else
				if(lives.get(i) instanceof Carnivore){
				   if (lives.get(i).getAge()%properties.get("carnivoreBirthingInverval")==0){
					   Life l = new Carnivore(createId(), lives.get(i).getName(), 1, lives.get(i).getLifespan());
					   lives.add(l);
				   }
			   }
			   	else
				  	if (lives.get(i).getAge()%properties.get("plantBirthingInverval")==0){
					   Life l = new Plant(createId(), lives.get(i).getName(), 1, lives.get(i).getLifespan());
					   lives.add(l);
				   }
			   }		
		
		for (int i=0; i<lives.size(); i++){ //aging
			lives.get(i).increaseAge();
			if (lives.get(i).getAge()==lives.get(i).getLifespan()){
				if (lives.get(i) instanceof Herbivore)
					herbivoreCount--;
				else
					if (lives.get(i) instanceof Carnivore)
					carnivoreCount--;
						else
							plantCount--;
		
			deathLog = deathLog + lives.get(i).getId() + "(overage), ";
			lives.remove(i);
           	if(i>0)
           		i--; 
				 
			}
		}

		if(lives.size()==0||lives.size()==properties.get("capacity"))
			status="unstable";

		}

	public void oneDayStat(){ //prints out day's events
		FileOutputStream output=null;
		try{
			output=new FileOutputStream("output.txt",true);
	   
	 	output.write(("\nDay: "+dayNumber+" Capacity: "+capacity).getBytes());
		if (status.equals("stable")){	
	 	output.write(("\nEating today: "+ eatingLog).getBytes());
	 	output.write(("\nDeath: "+ deathLog).getBytes());
	 	output.write(("\nStatus: "+status).getBytes());
	 	output.write(("\nRemaining lives: ").getBytes());
    
		for (int i=0; i<lives.size(); i++) 
		{
			if (lives.get(i) instanceof Plant)
				output.write(("\n"+((Plant)lives.get(i)).plantDescription()).getBytes());
			else
				if (lives.get(i) instanceof Herbivore)
				output.write(("\n"+((Herbivore)lives.get(i)).herbivoreDescription()).getBytes());
				else
					output.write(("\n"+((Carnivore)lives.get(i)).carnivoreDescription()).getBytes());

		}
		}
		else
			output.write("Unstable!!!".getBytes());
	}
		
		catch( IOException ex)
		{
			System.out.print("File not found");
		}
}
}
