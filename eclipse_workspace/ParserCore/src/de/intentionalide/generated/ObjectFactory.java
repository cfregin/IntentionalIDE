//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.03.30 at 05:06:55 PM MESZ 
//


package de.intentionalide.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import de.intentionalide.xml.XmlClass;
import de.intentionalide.xml.XmlMethod;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.intentionalide.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _XmlClass_QNAME = new QName("", "XmlClass");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.intentionalide.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link XmlClass }
     * 
     */
    public XmlClass createXmlClass() {
        return new XmlClass();
    }

    /**
     * Create an instance of {@link XmlMethod }
     * 
     */
    public XmlMethod createXmlMethod() {
        return new XmlMethod();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XmlClass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "XmlClass")
    public JAXBElement<XmlClass> createXmlClass(XmlClass value) {
        return new JAXBElement<XmlClass>(_XmlClass_QNAME, XmlClass.class, null, value);
    }

}