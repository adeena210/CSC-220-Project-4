/* Adeena Ahmed, Lalima Bhola, Faith Christian
    CSC 220-03
    Project 4: Jungle Management System w/ added features
*/
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Jungle{
	
	HashMap<String, Float> properties= new HashMap<>();
	private String status;
	private ArrayList<Life> lives;
	private int dayNumber;
	private String deathLog; 
	private String eatingLog;
	private String birthLog;
	private int herbivoreCount;
	private int carnivoreCount;
	private int plantCount;

	public Jungle(){ //default constructor
		Scanner sc=null;
		status= "stable";
		lives= new ArrayList<Life>();
		plantCount=0;
		herbivoreCount=0;
		carnivoreCount=0;
		dayNumber=0;
		deathLog="";
		eatingLog="";
		birthLog="";
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
					String name= sc.next();
					String type=sc.next();
					int count= sc.nextInt();
					int lifespan= sc.nextInt();
					if(type.equals("herbivore")){
						for(int i=0;i<count;i++){
							Life l= new Herbivore(createId(), name, 1, lifespan);
							lives.add(l);
							herbivoreCount++;
						}
					}
					else 
						if(type.equals("carnivore")){
							for(int i=0;i<count;i++){
								Life l= new Carnivore(createId(), name, 1, lifespan);
								lives.add(l);
								carnivoreCount++;
						 }
					    }
						else
							if(type.equals("plant")){
								for(int i=0;i<count;i++){
									Life l= new Plant(createId(), name, 1, lifespan);
									lives.add(l);
									plantCount++;
								}			
							}

					if (lives.size()==properties.get("capacity").intValue())
						 status="unstable";

					
					}
		 	}

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
		if(lives.size()>=properties.get("capacity").intValue())
			status="unstable";
		else
			status="stable";
	}
  

	public void runADay(){ // to run a day
		deathLog="";
		eatingLog="";
		birthLog="";
		dayNumber++;
		
		for (int i=0; i<lives.size(); i++){ 
			if(lives.get(i) instanceof Herbivore){
				Random rnd = new Random();
				int probability=rnd.nextInt(10)+1;
				if(probability<=((int)(properties.get("herbivoreEatingProbability")*10)) && plantCount!=0){ 
					Life l = lives.get(i).eat(lives, plantCount);
        			if(l!=null){
         		 		plantCount--;
						eatingLog=eatingLog+lives.get(i).getId()+" ate "+l.getId()+", ";
						deathLog=deathLog+l.getId()+"(food), ";	
          				
          				if(lives.indexOf(l)<i)
          					i--;
          				lives.remove(l);
					}
					
      			}

				else{
					
					lives.get(i).setDaysSinceEaten(lives.get(i).getDaysSinceEaten()+1);

					if (lives.get(i).getDaysSinceEaten()==properties.get("herbivoreStarvationMax").intValue()){
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
				if(probability<=((int)(properties.get("carnivoreEatingProbability")*10)) && herbivoreCount!=0){ 
					Life l = lives.get(i).eat(lives, herbivoreCount);
        			if(l!=null){
         		 		herbivoreCount--;
						eatingLog=eatingLog+lives.get(i).getId()+" ate "+l.getId()+", ";
						deathLog=deathLog+l.getId()+"(food), ";	

          				if(lives.indexOf(l)<i)
          					i--;
          				lives.remove(l);
					}
					
      			}

			else{
				
				lives.get(i).setDaysSinceEaten(lives.get(i).getDaysSinceEaten()+1);
				if (lives.get(i).getDaysSinceEaten()==properties.get("carnivoreStarvationMax").intValue()){
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
				if (lives.get(i).getAge()!=0&&lives.get(i).getAge()%properties.get("herbivoreBirthingInverval").intValue()==0){
					Life l = new Herbivore(createId(), lives.get(i).getName(), 0, lives.get(i).getLifespan());
					lives.add(l);
					birthLog=birthLog+lives.get(i).getId()+" gave birth to "+l.getId()+", ";

				   }
			   }
			   
			else
				if(lives.get(i) instanceof Carnivore){
				   if (lives.get(i).getAge()!=0&&lives.get(i).getAge()%properties.get("carnivoreBirthingInverval").intValue()==0){
					   Life l = new Carnivore(createId(), lives.get(i).getName(), 0, lives.get(i).getLifespan());
					   lives.add(l);
					   birthLog=birthLog+lives.get(i).getId()+" gave birth to "+l.getId()+", ";
				   }
			    }
			   	else
				  	if (lives.get(i).getAge()!=0&&lives.get(i).getAge()%properties.get("plantBirthingInverval").intValue()==0){
					   Life l = new Plant(createId(), lives.get(i).getName(), 0, lives.get(i).getLifespan());
					   lives.add(l);
					   birthLog=birthLog+lives.get(i).getId()+" gave birth to "+l.getId()+", ";
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

		if(lives.size()==0||lives.size()==properties.get("capacity").intValue())
			status="unstable";

		}

	public void oneDayStat(){ //prints out day's events
		FileOutputStream output=null;
		try{
			output=new FileOutputStream("output.txt",true);
	   
	   		output.write(("\nDay: "+dayNumber+" Capacity: "+properties.get("capacity").intValue()).getBytes());
			if (status.equals("stable")){	
	 			output.write(("\nEating today: "+ eatingLog).getBytes());
	 			output.write(("\nBirth today: "+ birthLog).getBytes());
	 			output.write(("\nDeath: "+ deathLog).getBytes());
	 			output.write(("\nStatus: "+status).getBytes());
	 			output.write(("\nRemaining lives: ").getBytes());
    
				for (int i=0; i<lives.size(); i++) {
					if (lives.get(i) instanceof Plant)
						output.write(("\n"+lives.get(i).print()).getBytes());
					else
						if (lives.get(i) instanceof Herbivore)
							output.write(("\n"+lives.get(i).print()).getBytes());
						else
							output.write(("\n"+lives.get(i).print()).getBytes());

				}
			}
		
			else
				output.write("  Unstable!!!".getBytes());
		}

		catch (FileNotFoundException ex) 
		{
			System.out.println("File not found");
		} 
		
		catch( IOException ex)
		{
			System.out.print("IO Exception");
		}

		
}
}
