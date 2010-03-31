package de.intentionalide.code;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IntentionalClass extends IntentionalElement {
	private String className;
	public IntentionalClass(String className){
		this.className = className;
	}
	List<IntentionalImport> imports = new LinkedList<IntentionalImport>();
	
	List<IntentionalMethod> methods = new LinkedList<IntentionalMethod>();
	List<IntentionalProperty> properties = new LinkedList<IntentionalProperty>();
	
	List<IntentionalClass> privateClasses = new LinkedList<IntentionalClass>();
	
	
	public void addMethod(IntentionalMethod theMethod){
		methods.add(theMethod);
	}
	public void addImport(IntentionalImport intentionalImport){
		imports.add(intentionalImport);
	}
	public List<IntentionalImport> getImports(){
		return imports;
	}
	public Collection<IntentionalMethod> getMethods(){
		return methods;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassName() {
		return className;
	}
	public List<IntentionalClass> getPrivateClasses() {
		return privateClasses;
	}
	public void addPrivateClass(IntentionalClass privateClass) {
		privateClasses.add(privateClass);
	}
}
