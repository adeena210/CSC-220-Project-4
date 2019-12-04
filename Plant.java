import java.util.*;

public class Plant extends Life{
	
	public Plant( int id, String name,  int age,  int lifespan){ //parameterized constructor to support superclass
		super(id,  name, age,  lifespan);
		
	}

	public String plantDescription(){ //print description
		String line= id+": "+name+", plant, "+age+", "+lifespan;
		return line;
	}
	
	public Life eat(ArrayList<Life> lives, int foodCount){
		return null;
	}
}
