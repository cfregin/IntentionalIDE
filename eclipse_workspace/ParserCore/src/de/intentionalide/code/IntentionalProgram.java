package de.intentionalide.code;

import java.util.Collection;
import java.util.HashMap;

public class IntentionalProgram {
	public IntentionalProgram(){
		
	}
	private HashMap<String, IntentionalClass> classes = new HashMap<String, IntentionalClass>();
	
	public void addClass(IntentionalClass theClass){
		classes.put(theClass.getClassName(), theClass);
		
	}
	public Collection<IntentionalClass> getClasses(){
		return classes.values();
	}
}
