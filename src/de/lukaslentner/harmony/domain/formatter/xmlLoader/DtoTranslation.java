package de.lukaslentner.harmony.domain.formatter.xmlLoader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class DtoTranslation {
  
  @XmlAttribute(name = "languageCode")
  public DtoLanguageCode languageCode;
  
  @XmlElement(name = "intervalName")
  public List<DtoIntervalName> intervalNames;
  
  @XmlTransient
  public Map<Long, DtoIntervalName> intervalNamesMap;
  
  public void postProcess() {
    this.intervalNamesMap = this.intervalNames.stream().collect(Collectors.toMap(o -> o.value, o -> o));
  }
  
}
