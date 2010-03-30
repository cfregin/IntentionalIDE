package de.intentionalide.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="XmlClass")
@XmlRootElement(name="XmlClass")
public class XmlClass {
	public XmlClass(){
		
	}
	private String name;
	private List<XmlMethod> methods = new LinkedList<XmlMethod>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<XmlMethod> getMethods() {
		return methods;
	}
	public void setMethods(List<XmlMethod> methods) {
		this.methods = methods;
	}
}
