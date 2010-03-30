package de.intentionalide.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="XmlMethod")
public class XmlMethod {
	public XmlMethod(){
		
	}

	private String name;
	private Boolean staticMethod;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStaticMethod() {
		return staticMethod;
	}
	public void setStaticMethod(Boolean staticMethod) {
		this.staticMethod = staticMethod;
	}

}
