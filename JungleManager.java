/* Adeena Ahmed, Lalima Bhola, Faith Christian
    CSC 220-03
    Project 4: Jungle Management System w/ added features
*/
import java.util.*;
public class JungleManager{
	public static void printOptions(Jungle j){
	   Scanner scnr = new Scanner(System.in);
	
	   System.out.println("MENU: ");
	   System.out.println("1: Exit");
	   System.out.println("2: Add Herbivore");   //need this
	   System.out.println("3: Add Carnivore");   //need this
	   System.out.println("4: Add plant");
	   System.out.println("5: Show Current Status");
	   System.out.println("6: Run X day");
	   System.out.println("7: Run till unstable");
      
       System.out.print("Enter your choice: ");
       int option = scnr.nextInt();
       
       switch (option) {
            case 1://to exit
            System.out.println("Exited");
            break;
        
	    case 2: //Add herbivore
            System.out.println("Enter herbivore name: ");
           String name = scnr.next();
            System.out.println("Enter herbivore lifespan: ");
          int lifespan = scnr.nextInt();
            System.out.println("Enter herbivore count: ");
             int count = scnr.nextInt();
            for(int i=0;i<count;i++){
                int id=j.createId();
                Life n= new Herbivore(id, name, 1, lifespan);
                j.addLife(n);
        	}
             if(j.getStatus().equals("unstable"))
                 System.out.println("WARNING! Jungle is unstable");
             printOptions(j);
             break;
	    
	    
	    
	    case 3: //Add Carnivore
            System.out.println("Enter carnivore name: ");
           name = scnr.next();
            System.out.println("Enter carnivore lifespan: ");
          lifespan = scnr.nextInt();
            System.out.println("Enter carnivore count: ");
             count = scnr.nextInt();
            for(int i=0;i<count;i++){
                int id=j.createId();
                Life n= new Carnivore(id, name, 1, lifespan);
                j.addLife(n);
        	}
             if(j.getStatus().equals("unstable"))
                 System.out.println("WARNING! Jungle is unstable");
             printOptions(j);
             break;
	    
	    
	    
//             
//             
                    
            case 4: //Add plant
            System.out.println("Enter plant name: ");
            name = scnr.next();
            System.out.println("Enter plant lifespan: ");
            lifespan = scnr.nextInt();
            System.out.println("Enter plant count: ");
            count = scnr.nextInt();
            for(int i = 0; i<count; i++){
                int id = j.createId();
                Life l = new Plant(id, name, 1, lifespan);
                j.addLife(l);

        	}
            if(j.getStatus().equals("unstable"))
                System.out.println("WARNING! Jungle is unstable");
            printOptions(j);
            break;
                    
           case 5: //Show current status
           j.oneDayStat();
             printOptions(j);
            break;
                    
            case 6: //Run X day
            System.out.print("Enter number of days: ");
            int num = scnr.nextInt();
            for(int i=0;i<num;i++)
              j.runADay();
            j.oneDayStat();
            printOptions(j);
            break;
            
            case 7: //Run until unstable
            while(j.getStatus().equals("stable"))
            	j.runADay();
            j.oneDayStat();
            printOptions(j);
            break;
         }


	
    }

    public static void main(String[] args) {
	   Jungle j= new Jungle();
       
	   printOptions(j);
    }	

}
