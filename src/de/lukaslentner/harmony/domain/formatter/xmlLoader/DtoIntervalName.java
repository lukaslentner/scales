package de.lukaslentner.harmony.domain.formatter.xmlLoader;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class DtoIntervalName {
  
  @XmlAttribute(name = "value")
  public Long value;
  
  @XmlAttribute(name = "commonName")
  public String commonName;
  
  @XmlElement(name = "furtherName")
  public List<DtoFurtherName> furtherNames;
  
}
