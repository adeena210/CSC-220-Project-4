import java.util.*;

public class Plant extends Life{
	
	public Plant( int id, String name,  int age,  int lifespan){ //parameterized constructor to support superclass
		super(id,  name, age,  lifespan);
		
	}

	public void plantDescription(){ //print description
		System.out.println(id+": "+name+", plant, "+age+", "+lifespan);
	}
	
	public Life eat(ArrayList<Life> lives, int foodCount){
		return null;
	}
}