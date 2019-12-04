import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Jungle{
	
	HashMap<String, Float> properties= new HashMap<>();
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
			sc = new Scanner(input);
		
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
		catch (FileNotFoundException excpt) {
			System.out.println("File not found");
		}
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
					if (((Herbivore)lives.get(i)).getDaysSinceEaten()==properties.get("herbivoreStarvationMax"){
						deathLog=deathLog+lives.get(i).getId()+"(starvation), ";
          				herbivoreCount--;
				 		lives.remove(i);
          				 i--;
					}
				}
			}
					  
		for (i=0; i<lives.size(); i++){ 
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
					if (((Carnivore)lives.get(i)).getDaysSinceEaten()==properties.get("carnivoreStarvationMax"){
						deathLog=deathLog+lives.get(i).getId()+"(starvation), ";
          				carnivoreCount--;
				 		lives.remove(i);
          				 i--;
					}
				}
			}
					  
                   for (i=0;i<lives.size();i++){ // birth
			   if(lives.get(i) instanceof Herbivore){
				   if (lives.get(i).getAge()%properties.get("herbivoreBirthingInverval")==0){
					   Life l = Herbivore(createId(), lives.get(i).getName(), 1, life.get(i).getLifespan());
					   lives.add(l);
				   }
			   }
			   else
				if(lives.get(i) instanceof Carnivore){
				   if (lives.get(i).getAge()%properties.get("carnivoreBirthingInverval")==0){
					   Life l = Carnivore(createId(), lives.get(i).getName(), 1, life.get(i).getLifespan());
					   lives.add(l);
				   }
			   }
			   	else
				  	if (lives.get(i).getAge()%properties.get("plantBirthingInverval")==0){
					   Life l = Plant(createId(), lives.get(i).getName(), 1, life.get(i).getLifespan());
					   lives.add(l);
				   }
			   }		
		
		for (i=0; i<lives.size(); i++){ //aging
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

		if(lives.size()==0||lives.size()==properties.get("capacity"))
			status="unstable";

		}

	public void oneDayStat(){ //prints out day's events
		FileOutputStream output=null;
		try{
			output=new FileOutputStream("output.txt",true);
	   
	 	output.write(("Day: "+dayNumber+" Capacity: "+capacity).getBytes());
		if (status.equals("unstable")){	
	 	output.write(("Eating today: "+ eatingLog).getBytes());
	 	output.write(("Death: "+ deathLog).getBytes());
	 	output.write(("Status: "+status).getBytes());
	 	output.write(("Remaining lives: ").getBytes());
    
		for (int i=0; i<lives.size(); i++) 
		{
			if (lives.get(i) instanceof Plant)
				output.write((Plant)lives.get(i)).plantDescription().getBytes());
			else
				if (lives.get(i) instanceof Herbivore)
				output.write((Herbivore)lives.get(i)).herbivoreDescription().getBytes());
				else
					output.write((Carnivore)lives.get(i)).carnivoreDescription().getBytes());

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
