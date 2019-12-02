/* Adeena Ahmed
    CSC 220-03
    Project 3: Jungle Management System
*/
import java.util.*;

public class Life{
	
	protected int id;
	protected String name;
	protected int age;
	protected int lifespan;
 	public Life( ){ //default constructor
		this.id= 0;
		this.name= "none";
		this.age= 0;
		this.lifespan= 0;
	}

	public void setId( int id){ //setter for id
		this.id=id;
	}
	
	public int getId( ){ //getter for id
		return id;
	}

	public void setName( String name){ //setter for name
		this.name=name;
	}
	
	public String  getName( ){ //getter for name
		return name;
	}

	public void setAge( int age){ //setter for age
		this.age=age;
	}
	
	public int getAge( ){ //getter for age
		return age;
	}

	public void setLifespan( int lifespan){ //setter for lifespan
		this.lifespan=lifespan;
	}
	
	public int getLifespan( ){ //getter for lifespan
		return lifespan;
	}


	public Life( int id, String name,int age,  int lifespan){ //parameterized constructor
		this.id= id;
		this.name= name;
		this.age= age;
		this.lifespan= lifespan;
	}

	public void increaseAge(){ //to increase age
		if(age<lifespan)
			age++;
		

	}

}